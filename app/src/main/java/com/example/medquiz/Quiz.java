package com.example.medquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.io.IOException;

public class Quiz extends AppCompatActivity {

    Bundle extrasBundle;
    String indexStr;
    String year;
    String categoryName;
    Boolean random;
    Boolean training;
    Boolean trainErrors;
    Integer index = 0;
    Integer correctAnswersCounts = 0;
    Cursor questions;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    Boolean answerAlreadyChecked;
    String questionId;


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
        db = dbHelper.getWritableDatabase();

        if (savedInstanceState == null) {
            extrasBundle = getIntent().getExtras();
            if (extrasBundle == null) {
                indexStr = null;
            } else {
                indexStr = extrasBundle.getString("numbersOfQuestions");
                year = extrasBundle.getString("year");
                categoryName = extrasBundle.getString("name");
                random = extrasBundle.getBoolean("random");
                training = extrasBundle.getBoolean("training");
                trainErrors = extrasBundle.getBoolean("trainErrors");
            }
        } else {
            indexStr = (String) savedInstanceState.getSerializable("numbersOfQuestions");
            year = (String) savedInstanceState.getSerializable("year");
            categoryName = (String) savedInstanceState.getSerializable("name");
            random = (Boolean) savedInstanceState.getSerializable("random");
            training = (Boolean) savedInstanceState.getSerializable("training");
            trainErrors = (Boolean) savedInstanceState.getSerializable("trainErrors");
        }

        questions = this.getQuestions(categoryName, indexStr, year);
        questions.moveToFirst();
        setQuestion();
        setQuestionNumber();

        final Button checkButton= findViewById(R.id.check_question);
        if (training){
            checkButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    checkAnswer(true);
                }
            });
        }else{
            ViewGroup layout = (ViewGroup) checkButton.getParent();
            if(null!=layout) //for safety only  as you are doing onClick
                layout.removeView(checkButton);

        }

    }

    public void setQuestion() {
        answerAlreadyChecked = false;
        questionId = questions.getString(0);
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
                answerRadioButton.setPadding(20, 10, 20, 10);
                answersGroup.addView(answerRadioButton);
            }
        }
    }

    public void setStats(String wrong, String correct){
        TextView wrongTextView = (TextView) findViewById(R.id.wrong_stats_number);
        TextView correctTextView = (TextView) findViewById(R.id.correct_stats_number);
        wrongTextView.setText(wrong);
        correctTextView.setText(correct);
    }

    public void nextQuestionButton(View view) {
        checkAnswer(false);
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

    public Cursor getQuestions(String idPrzedmiotu, String limit, String year) {
        Cursor c = null;

        try {
            if (random) {
                if (year.equals("ALL")){
                    c = db.rawQuery("SELECT * FROM Pytanie p JOIN Statystyki s ON s.IdPytania=p.IdPytania JOIN Przedmiot ON Pytanie.IdPrzedmiotu=Przedmiot.IdPrzedmiotu where Przedmiot.NazwaPrzedmiotu= \"" + idPrzedmiotu + "\" ORDER BY RANDOM() LIMIT " + limit, null);
                }else{
                    c = db.rawQuery("SELECT * FROM Pytanie p JOIN Statystyki s ON s.IdPytania=p.IdPytania JOIN Przedmiot ON Pytanie.IdPrzedmiotu=Przedmiot.IdPrzedmiotu where Przedmiot.NazwaPrzedmiotu= \"" + idPrzedmiotu + "\" AND Rok = " + year + " ORDER BY RANDOM() LIMIT " + limit, null);
                }

            } else if (trainErrors) {
                if (year.equals("ALL")){
                    c = db.rawQuery("SELECT *, s.IloscBlednychOdp/(s.IloscBlednychOdp + s.IloscPrawidlowychOdp) AS stats FROM Pytanie p JOIN Statystyki s ON s.IdPytania=p.IdPytania JOIN Przedmiot przed ON przed.IdPrzedmiotu=p.IdPRzedmiotu WHERE przed.NazwaPrzedmiotu= \"" + idPrzedmiotu + "\" ORDER BY stats DESC, s.IloscBlednychOdp DESC LIMIT " + limit, null);
                } else {
                    c = db.rawQuery("SELECT *, s.IloscBlednychOdp/(s.IloscBlednychOdp + s.IloscPrawidlowychOdp) AS stats FROM Pytanie p JOIN Statystyki s ON s.IdPytania=p.IdPytania JOIN Przedmiot przed ON przed.IdPrzedmiotu=p.IdPRzedmiotu WHERE przed.NazwaPrzedmiotu= \"" + idPrzedmiotu + "\" AND p.Rok = \"" + year + "\" ORDER BY stats DESC, s.IloscBlednychOdp DESC LIMIT " + limit , null);
                }
            }else{
                if (year.equals("ALL")){
                    c = db.rawQuery("SELECT * FROM Pytanie JOIN Przedmiot ON Pytanie.IdPrzedmiotu=Przedmiot.IdPrzedmiotu where Przedmiot.NazwaPrzedmiotu= \"" + idPrzedmiotu + "\" LIMIT " + limit, null);
                } else {
                    c = db.rawQuery("SELECT * FROM Pytanie JOIN Przedmiot ON Pytanie.IdPrzedmiotu=Przedmiot.IdPrzedmiotu where Przedmiot.NazwaPrzedmiotu= \"" + idPrzedmiotu + "\" AND Rok = " + year + " LIMIT " + limit, null);
                }
            }
            if (c == null) return null;
        } catch (Exception e) {
            Log.e("tle99", e.getMessage());
        }
        return c;
    }

    public void upgradeError(){
        Cursor cursor =db.rawQuery("SELECT IloscBlednychOdp FROM Statystyki WHERE IdPytania = " + questionId, null);
        int cardnum=0;
        cursor.moveToFirst();
        if( cursor != null )
        {
            cardnum = cursor.getInt(0);
            cardnum++;
        }
        ContentValues values = new ContentValues();
        values.put("IloscBlednychOdp",cardnum);
        db.update("Statystyki", values, "IdPytania = ?", new String[]{questionId});
    }

    public void upgradeCorrect(){
        Cursor cursor =db.rawQuery("SELECT IloscPrawidlowychOdp FROM Statystyki WHERE IdPytania = " + questionId, null);
        int cardnum=0;
        cursor.moveToFirst();
        if( cursor != null )
        {
            cardnum = cursor.getInt(0);
            cardnum++;
        }
        ContentValues values = new ContentValues();
        values.put("IloscPrawidlowychOdp",cardnum);
        db.update("Statystyki", values, "IdPytania = ?", new String[]{questionId});
    }

    public void checkAnswer(Boolean highlight) {
        String correctAnswer;
        correctAnswer = questions.getString(8);
        RadioGroup answersGroup = (RadioGroup) findViewById(R.id.answers);
        int checkedRadioButtonId = answersGroup.getCheckedRadioButtonId();
        if (Integer.parseInt(correctAnswer) == checkedRadioButtonId && !highlight && !answerAlreadyChecked) {
            correctAnswersCounts++;
            upgradeCorrect();
            answerAlreadyChecked = true;
        }
        if (Integer.parseInt(correctAnswer) != checkedRadioButtonId && !highlight && !answerAlreadyChecked) {
            upgradeError();
        }
        if (highlight) {
            if (Integer.parseInt(correctAnswer) == checkedRadioButtonId && !answerAlreadyChecked) {
                correctAnswersCounts++;
            }
            answerAlreadyChecked = true;
            for (int item = 1; item <= 5; item++) {
                RadioButton answerRadioButton = (RadioButton) findViewById(item);
                if (answerRadioButton != null) {
                    if(item == Integer.parseInt(correctAnswer)) {
                         answerRadioButton.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.correct_answer));
                    }else{
                        answerRadioButton.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.wrong_answer));
                    }
                }
            }
        }
    }
}
