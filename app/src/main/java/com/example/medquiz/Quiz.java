package com.example.medquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.io.IOException;

public class Quiz extends AppCompatActivity {

    Bundle extrasBundle;
    String indexStr;
    String categoryName;
    Boolean random;
    Integer index = 0;
    Integer correctAnswersCounts = 0;
    Cursor questions;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        dbHelper = new DatabaseHelper(getApplicationContext());
        try {
            dbHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (savedInstanceState == null) {
            extrasBundle = getIntent().getExtras();
            if (extrasBundle == null) {
                indexStr = null;
            } else {
                indexStr = extrasBundle.getString("numbersOfQuestions");
                categoryName = extrasBundle.getString("name");
                random = extrasBundle.getBoolean("random");
            }
        } else {
            indexStr = (String) savedInstanceState.getSerializable("numbersOfQuestions");
            categoryName = (String) savedInstanceState.getSerializable("name");
            random = (Boolean) savedInstanceState.getSerializable("random");
        }

        questions = this.getQuestions(categoryName, indexStr);
        questions.moveToFirst();
        setQuestion();
        setQuestionNumber();
    }

    public void setQuestion() {
        String trescPytania = questions.getString(2);
        TextView questionTextView = (TextView) findViewById(R.id.question);
        questionTextView.setText(trescPytania);

        RadioGroup answersGroup = (RadioGroup) findViewById(R.id.answers);
        answersGroup.removeAllViews();
        String answer;

        for (int item = 1; item <= 5; item++) {
            answer = questions.getString(item + 2);
            if (answer != null) {
                RadioButton answerRadioButton = new RadioButton(getApplicationContext());
                answerRadioButton.setId(item);
                answerRadioButton.setText(answer);
                answerRadioButton.setTextSize(20);
                answerRadioButton.setPadding(20,10,20,10);
                answersGroup.addView(answerRadioButton);
            }
        }
    }

    public void nextQuestionButton(View view) {
        checkAnswer();
        if (index == Integer.parseInt(indexStr)) {
            db.close();
            Intent intent = new Intent(getApplicationContext(), Result.class);
            intent.putExtra("result", correctAnswersCounts + "/" + indexStr);
            startActivity(intent);
        } else {
            questions.moveToNext();
            setQuestionNumber();
            setQuestion();
        }

    }

    public void setQuestionNumber() {
        index++;
        TextView countingTextView = (TextView) findViewById(R.id.question_number);
        countingTextView.setText(index + "/" + indexStr);
    }

    public Cursor getQuestions(String idPrzedmiotu, String limit) {
        db = dbHelper.getWritableDatabase();
        Cursor c = null;

        try {
            if (random){
                c = db.rawQuery("SELECT * FROM Pytanie JOIN Przedmiot ON Pytanie.IdPrzedmiotu=Przedmiot.IdPrzedmiotu where Przedmiot.NazwaPrzedmiotu= \"" + idPrzedmiotu + "\" ORDER BY RANDOM() LIMIT " + limit, null);
            }else {
                c = db.rawQuery("SELECT * FROM Pytanie JOIN Przedmiot ON Pytanie.IdPrzedmiotu=Przedmiot.IdPrzedmiotu where Przedmiot.NazwaPrzedmiotu= \"" + idPrzedmiotu + "\" LIMIT " + limit, null);
            }
            if (c == null) return null;
        } catch (Exception e) {
            Log.e("tle99", e.getMessage());
        }
        return c;
    }

    public void checkAnswer() {
        String correctAnswer;
        correctAnswer = questions.getString(8);
        RadioGroup answersGroup = (RadioGroup) findViewById(R.id.answers);
        int checkedRadioButtonId = answersGroup.getCheckedRadioButtonId();
        if (Integer.parseInt(correctAnswer) == checkedRadioButtonId) {
            correctAnswersCounts++;
        }
    }
}