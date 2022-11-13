package com.example.medquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FinishedQuiz extends AppCompatActivity {

    Bundle extrasBundle;
    String result;
    List<String> finishedQuestions = new ArrayList<String>();
    String indexStr;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    Cursor questions;
    String questionId;
    Integer index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finished_quiz);

        dbHelper = new DatabaseHelper(getApplicationContext());
        try {
            dbHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        db = dbHelper.getWritableDatabase();
        if (savedInstanceState == null) {
            extrasBundle = getIntent().getExtras();
            if (extrasBundle == null) {
                result = null;
                indexStr = null;
            } else {
                result = extrasBundle.getString("result");
                indexStr = extrasBundle.getString("numbersOfQuestions");
                for (int item = 0; item < Integer.valueOf(indexStr); item++) {
                    finishedQuestions.add(extrasBundle.getString("IdQuestion" + item));
                    finishedQuestions.add(extrasBundle.getString("AnsQuestion" + item));
                }
            }
        } else {
            result = (String) savedInstanceState.getSerializable("result");
            indexStr = (String) savedInstanceState.getSerializable("numbersOfQuestions");
        }
        questions = this.getQuestions();
        questions.moveToFirst();
        setQuestion();
        setQuestionNumber();
    }

    //    If this is last question go to result if not show next question
    public void nextQuestionButton(View view) {
        if (index == Integer.parseInt(indexStr)) {
            db.close();
            Intent intent = new Intent(getApplicationContext(), Result.class);
            intent.putExtra("result", result);
            intent.putExtra("numbersOfQuestions", indexStr);
            for (int item = 0; item < Integer.valueOf(indexStr); item++) {
                intent.putExtra("IdQuestion" + item, finishedQuestions.get(item * 2));
                intent.putExtra("AnsQuestion" + item, finishedQuestions.get(item * 2 + 1));
            }
            startActivity(intent);
        } else {
            questions.moveToNext();
            setQuestionNumber();
            setQuestion();
        }

    }

    public Cursor getQuestions() {
        Cursor c = null;
        String questionsIds = "(";
        for (int item = 0; item < Integer.valueOf(indexStr); item++) {
            if (item > 0) {
                questionsIds = questionsIds + ",";
            }
            questionsIds = questionsIds + finishedQuestions.get(item * 2);
        }
        questionsIds = questionsIds + ")";
        try {
            c = db.rawQuery("SELECT p.IdPytania, p.TrescPytania, p.OdpA, p.OdpB, p.OdpC, p.OdpD, p.OdpE, p.PrawidlowaOdp, s.IloscBlednychOdp, s.IloscPrawidlowychOdp, s.CzyNauczone FROM Pytanie p JOIN Statystyki s ON s.IdPytania=p.IdPytania where p.IdPytania in " + questionsIds, null);
            if (c == null) return null;
        } catch (Exception e) {
            Log.e("tle99", e.getMessage());
        }
        return c;
    }

    //    Show question, answers and set stats
    public void setQuestion() {
        questionId = questions.getString(0);
        String trescPytania = questions.getString(1);
        TextView questionTextView = (TextView) findViewById(R.id.question);
        questionTextView.setText(trescPytania);

        RadioGroup answersGroup = (RadioGroup) findViewById(R.id.answers);
        answersGroup.removeAllViews();
        String answer;

        for (int item = 1; item <= 5; item++) {
            answer = questions.getString(item + 1);
            if (answer != null) {
                RadioButton answerRadioButton = new RadioButton(getApplicationContext());
                answerRadioButton.setId(item);
                answerRadioButton.setText(answer);
                answerRadioButton.setTextSize(20);
                answerRadioButton.setPadding(20, 10, 20, 10);
                answersGroup.addView(answerRadioButton);
            }
        }
        setStats(questions.getString(8), questions.getString(9));
        setAnswers();
    }

    //    Set question index count
    public void setQuestionNumber() {
        index++;
        TextView countingTextView = (TextView) findViewById(R.id.question_number);
        countingTextView.setText(index + "/" + indexStr);
    }

    //    Set stats wrong/correct number of answers
    public void setStats(String wrong, String correct) {
        TextView wrongTextView = (TextView) findViewById(R.id.wrong_stats_number);
        TextView correctTextView = (TextView) findViewById(R.id.correct_stats_number);
        wrongTextView.setText(wrong);
        correctTextView.setText(correct);
    }

    public void setAnswers() {
        String correctAnswer;
        correctAnswer = questions.getString(7);
        String checkedAnswer;
        checkedAnswer = finishedQuestions.get(finishedQuestions.indexOf(questionId) + 1);
        RadioButton answerRadioButton = (RadioButton) findViewById(Integer.parseInt(correctAnswer));
        answerRadioButton.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.correct_answer));
        if (!checkedAnswer.equals(correctAnswer)) {
            if (!checkedAnswer.equals("")) {
                RadioButton wrongRadioButton = (RadioButton) findViewById(Integer.parseInt(checkedAnswer));
                wrongRadioButton.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.wrong_answer));
            } else {
                RadioGroup answersGroup = (RadioGroup) findViewById(R.id.answers);
                RadioButton noAnswerRadioButton = new RadioButton(getApplicationContext());
                int id = 99;
                noAnswerRadioButton.setId(id);
                noAnswerRadioButton.setText("NIE WYBRANO ODPOWIEDZI");
                noAnswerRadioButton.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.wrong_answer));
                noAnswerRadioButton.setTextSize(20);
                noAnswerRadioButton.setPadding(20, 10, 20, 10);
                answersGroup.addView(noAnswerRadioButton);
            }
        }

    }

    // click two times back to exit
    boolean isPressed = false;

    public void onBackPressed() {
        if (isPressed) {
            finishAffinity();
            System.exit(0);
        } else {
            isPressed = true;
            Toast.makeText(getApplicationContext(), "Press again to exit", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    isPressed = false;
                }
            }, 2000);
        }
    }
}