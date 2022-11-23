package com.example.fiszkonator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Quiz extends AppCompatActivity {

    Bundle extrasBundle;
    String indexStr;
    String level;
    String categoryName;
    Boolean random;
    Boolean training;
    Boolean trainErrors;
    Boolean learnSwitch;
    String option;
    Integer index = 0;
    Integer correctAnswersCounts = 0;
    Cursor questions;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    Boolean answerAlreadyChecked;
    String questionId;
    String ifLearn;
    List<String> finishedQuestions = new ArrayList<String>();



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

//        Get extra values from settings
        if (savedInstanceState == null) {
            extrasBundle = getIntent().getExtras();
            if (extrasBundle == null) {
                indexStr = null;
                level = null;
                categoryName = null;
                random = null;
                training = null;
                trainErrors = null;
                learnSwitch = null;
                option = null;
            } else {
                indexStr = extrasBundle.getString("numbersOfQuestions");
                level = extrasBundle.getString("level");
                categoryName = extrasBundle.getString("name");
                random = extrasBundle.getBoolean("random");
                training = extrasBundle.getBoolean("training");
                learnSwitch = extrasBundle.getBoolean("learnSwitch");
                option = extrasBundle.getString("option");
            }
        } else {
            indexStr = (String) savedInstanceState.getSerializable("numbersOfQuestions");
            level = (String) savedInstanceState.getSerializable("level");
            categoryName = (String) savedInstanceState.getSerializable("name");
            random = (Boolean) savedInstanceState.getSerializable("random");
            training = (Boolean) savedInstanceState.getSerializable("training");
            trainErrors = (Boolean) savedInstanceState.getSerializable("trainErrors");
            learnSwitch = (Boolean) savedInstanceState.getSerializable("learnSwitch");
            option = (String) savedInstanceState.getSerializable("option");
        }

//        Get questions and show the first one
        questions = this.getQuestions(categoryName, indexStr, level);
        questions.moveToFirst();
        setQuestion();
        setQuestionNumber();

        final Button learnButton = findViewById(R.id.change_learn_button);
        learnButton.setVisibility(View.GONE);

//        If set training the button won't show
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

//    Show question, answers and set stats
    public void setQuestion() {
        answerAlreadyChecked = false;
        questionId = questions.getString(0);
        String trescPytania = questions.getString(1);
        TextView questionTextView = (TextView) findViewById(R.id.question);
        questionTextView.setText(trescPytania);

        final Button learnButton = findViewById(R.id.change_learn_button);
        learnButton.setVisibility(View.GONE);

        RadioGroup answersGroup = (RadioGroup) findViewById(R.id.answers);
        answersGroup.removeAllViews();
        answersGroup.clearCheck();
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
        setStats(questions.getString(8),questions.getString(9));
        ifLearn = questions.getString(10);
    }

//    Set stats wrong/correct number of answers
    public void setStats(String wrong, String correct){
        TextView wrongTextView = (TextView) findViewById(R.id.wrong_stats_number);
        TextView correctTextView = (TextView) findViewById(R.id.correct_stats_number);
        wrongTextView.setText(wrong);
        correctTextView.setText(correct);
    }

//    If this is last question go to result if not show next question
    public void nextQuestionButton(View view) {
        checkAnswer(false);
        if (index == Integer.parseInt(indexStr)) {
            db.close();
            Intent intent = new Intent(getApplicationContext(), Result.class);
            intent.putExtra("result", correctAnswersCounts + "/" + indexStr);
            intent.putExtra("numbersOfQuestions", indexStr);
            for (int item = 0; item < Integer.valueOf(indexStr); item++) {
                intent.putExtra("IdQuestion" +item, finishedQuestions.get(item*2));
                intent.putExtra("AnsQuestion" +item, finishedQuestions.get(item*2+1));
            }
            startActivity(intent);
        } else {
            questions.moveToNext();
            setQuestionNumber();
            setQuestion();
        }
    }

//    Set question index count
    public void setQuestionNumber() {
        index++;
        TextView countingTextView = (TextView) findViewById(R.id.question_number);
        countingTextView.setText(index + "/" + indexStr);
    }

//    Get question numbers depend of settings
    public Cursor getQuestions(String nazwaOpcji, String limit, String level) {
        Cursor c = null;
        String optionQuery = "";
        if (option.equals("Kategoria")){
            optionQuery = "JOIN Kategoria k ON k.IdKategorii=f.IdKategorii where k.NazwaKategorii = \"" + nazwaOpcji;
        }else{
            optionQuery = "JOIN Zestaw z ON z.idZestawu=f.idZestawu where z.NazwaZestawu = \"" + nazwaOpcji;
        }

        try {
            if (random) {
                if (level.equals("ALL")){
                    c = db.rawQuery("SELECT f.IdFiszki, f.Polski, f.Komentarz, f.Uzyciepolski, f.Obcy, f.UzycieObcy, s.IloscBlednychOdp, s.IloscPrawidlowychOdp, s.PoziomNauczenia FROM Fiszka f JOIN Statystyki s ON s.IdFiszki=p.IdFiszki" + optionQuery + "\" AND s.PoziomNauczenia=0 ORDER BY RANDOM() LIMIT " + limit, null);
                }else{
                    if(learnSwitch) {
                        c = db.rawQuery("SELECT p.IdPytania, p.TrescPytania, p.OdpA, p.OdpB, p.OdpC, p.OdpD, p.OdpE, p.PrawidlowaOdp, s.IloscBlednychOdp, s.IloscPrawidlowychOdp, s.CzyNauczone FROM Pytanie p JOIN Statystyki s ON s.IdPytania=p.IdPytania JOIN Przedmiot przed ON p.IdPrzedmiotu=przed.IdPrzedmiotu where przed.NazwaPrzedmiotu= \"" + nazwaOpcji + "\" AND p.Rok = " + level + " AND s.CzyNauczone=0 ORDER BY RANDOM() LIMIT " + limit, null);
                    } else{
                        c = db.rawQuery("SELECT p.IdPytania, p.TrescPytania, p.OdpA, p.OdpB, p.OdpC, p.OdpD, p.OdpE, p.PrawidlowaOdp, s.IloscBlednychOdp, s.IloscPrawidlowychOdp, s.CzyNauczone FROM Pytanie p JOIN Statystyki s ON s.IdPytania=p.IdPytania JOIN Przedmiot przed ON p.IdPrzedmiotu=przed.IdPrzedmiotu where przed.NazwaPrzedmiotu= \"" + nazwaOpcji + "\" AND p.Rok = " + level + " ORDER BY RANDOM() LIMIT " + limit, null);
                    }
                }

            } else if (trainErrors) {
                if (level.equals("ALL")){
                    if(learnSwitch) {
                        c = db.rawQuery("SELECT p.IdPytania, p.TrescPytania, p.OdpA, p.OdpB, p.OdpC, p.OdpD, p.OdpE, p.PrawidlowaOdp, s.IloscBlednychOdp, s.IloscPrawidlowychOdp, s.CzyNauczone, s.IloscBlednychOdp/(s.IloscBlednychOdp + s.IloscPrawidlowychOdp) AS stats FROM Pytanie p JOIN Statystyki s ON s.IdPytania=p.IdPytania JOIN Przedmiot przed ON przed.IdPrzedmiotu=p.IdPRzedmiotu WHERE przed.NazwaPrzedmiotu= \"" + nazwaOpcji + "\" AND s.CzyNauczone=0 ORDER BY stats DESC, s.IloscBlednychOdp DESC LIMIT " + limit, null);
                    } else {
                        c = db.rawQuery("SELECT p.IdPytania, p.TrescPytania, p.OdpA, p.OdpB, p.OdpC, p.OdpD, p.OdpE, p.PrawidlowaOdp, s.IloscBlednychOdp, s.IloscPrawidlowychOdp, s.CzyNauczone, s.IloscBlednychOdp/(s.IloscBlednychOdp + s.IloscPrawidlowychOdp) AS stats FROM Pytanie p JOIN Statystyki s ON s.IdPytania=p.IdPytania JOIN Przedmiot przed ON przed.IdPrzedmiotu=p.IdPRzedmiotu WHERE przed.NazwaPrzedmiotu= \"" + nazwaOpcji + "\" ORDER BY stats DESC, s.IloscBlednychOdp DESC LIMIT " + limit, null);
                    }
                } else {
                    if(learnSwitch) {
                        c = db.rawQuery("SELECT p.IdPytania, p.TrescPytania, p.OdpA, p.OdpB, p.OdpC, p.OdpD, p.OdpE, p.PrawidlowaOdp, s.IloscBlednychOdp, s.IloscPrawidlowychOdp, s.CzyNauczone, s.IloscBlednychOdp/(s.IloscBlednychOdp + s.IloscPrawidlowychOdp) AS stats FROM Pytanie p JOIN Statystyki s ON s.IdPytania=p.IdPytania JOIN Przedmiot przed ON przed.IdPrzedmiotu=p.IdPRzedmiotu WHERE przed.NazwaPrzedmiotu= \"" + nazwaOpcji + "\" AND p.Rok = \"" + level + "\" AND s.CzyNauczone=0 ORDER BY stats DESC, s.IloscBlednychOdp DESC LIMIT " + limit , null);
                    }else {
                        c = db.rawQuery("SELECT p.IdPytania, p.TrescPytania, p.OdpA, p.OdpB, p.OdpC, p.OdpD, p.OdpE, p.PrawidlowaOdp, s.IloscBlednychOdp, s.IloscPrawidlowychOdp, s.CzyNauczone, s.IloscBlednychOdp/(s.IloscBlednychOdp + s.IloscPrawidlowychOdp) AS stats FROM Pytanie p JOIN Statystyki s ON s.IdPytania=p.IdPytania JOIN Przedmiot przed ON przed.IdPrzedmiotu=p.IdPRzedmiotu WHERE przed.NazwaPrzedmiotu= \"" + nazwaOpcji + "\" AND p.Rok = \"" + level + "\" ORDER BY stats DESC, s.IloscBlednychOdp DESC LIMIT " + limit, null);
                    }
                }
            }else{
                if (level.equals("ALL")){
                    if(learnSwitch) {
                        c = db.rawQuery("SELECT p.IdPytania, p.TrescPytania, p.OdpA, p.OdpB, p.OdpC, p.OdpD, p.OdpE, p.PrawidlowaOdp, s.IloscBlednychOdp, s.IloscPrawidlowychOdp, s.CzyNauczone FROM Pytanie p JOIN Statystyki s ON s.IdPytania=p.IdPytania JOIN Przedmiot przed ON p.IdPrzedmiotu=przed.IdPrzedmiotu where przed.NazwaPrzedmiotu= \"" + nazwaOpcji + "\" AND s.CzyNauczone=0 LIMIT " + limit, null);
                    }else{
                        c = db.rawQuery("SELECT p.IdPytania, p.TrescPytania, p.OdpA, p.OdpB, p.OdpC, p.OdpD, p.OdpE, p.PrawidlowaOdp, s.IloscBlednychOdp, s.IloscPrawidlowychOdp, s.CzyNauczone FROM Pytanie p JOIN Statystyki s ON s.IdPytania=p.IdPytania JOIN Przedmiot przed ON p.IdPrzedmiotu=przed.IdPrzedmiotu where przed.NazwaPrzedmiotu= \"" + nazwaOpcji + "\" LIMIT " + limit, null);
                    }
                } else {
                    if(learnSwitch) {
                        c = db.rawQuery("SELECT p.IdPytania, p.TrescPytania, p.OdpA, p.OdpB, p.OdpC, p.OdpD, p.OdpE, p.PrawidlowaOdp, s.IloscBlednychOdp, s.IloscPrawidlowychOdp, s.CzyNauczone FROM Pytanie p JOIN Statystyki s ON s.IdPytania=p.IdPytania JOIN Przedmiot przed ON p.IdPrzedmiotu=przed.IdPrzedmiotu where przed.NazwaPrzedmiotu= \"" + nazwaOpcji + "\" AND p.Rok = " + level + " AND s.CzyNauczone=0 LIMIT " + limit, null);
                    }else{
                        c = db.rawQuery("SELECT p.IdPytania, p.TrescPytania, p.OdpA, p.OdpB, p.OdpC, p.OdpD, p.OdpE, p.PrawidlowaOdp, s.IloscBlednychOdp, s.IloscPrawidlowychOdp, s.CzyNauczone FROM Pytanie p JOIN Statystyki s ON s.IdPytania=p.IdPytania JOIN Przedmiot przed ON p.IdPrzedmiotu=przed.IdPrzedmiotu where przed.NazwaPrzedmiotu= \"" + nazwaOpcji + "\" AND p.Rok = " + level + " LIMIT " + limit, null);
                    }

                }
            }
            if (c == null) return null;
        } catch (Exception e) {
            Log.e("tle99", e.getMessage());
        }
        return c;
    }

//    Upgrade number of wrong answers in database
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

    //    Upgrade number of correct answers in database
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

//    Set question as learn
    public void setLearn(){
        ContentValues values = new ContentValues();
        values.put("CzyNauczone",1);
        db.update("Statystyki", values, "IdPytania = ?", new String[]{questionId});
    }

//    Set question as unlearn
    public void unsetLearn(){
        ContentValues values = new ContentValues();
        values.put("CzyNauczone",0);
        db.update("Statystyki", values, "IdPytania = ?", new String[]{questionId});
    }


//    Chek if the answear is correct and if is it traing show correct answers
    public void checkAnswer(Boolean highlight) {
        if(!finishedQuestions.contains(questionId)){
            finishedQuestions.add(questionId);
        }
        String correctAnswer;
        correctAnswer = questions.getString(7);
        RadioGroup answersGroup = (RadioGroup) findViewById(R.id.answers);
        int checkedRadioButtonId = answersGroup.getCheckedRadioButtonId();
        if(!answerAlreadyChecked && finishedQuestions.contains(questionId)){
            if (answersGroup.getCheckedRadioButtonId() == -1)
            {
                finishedQuestions.add("");
            }
            else
            {
                finishedQuestions.add(String.valueOf(checkedRadioButtonId));
            }
        }
        if (Integer.parseInt(correctAnswer) == checkedRadioButtonId && !highlight && !answerAlreadyChecked) {
            correctAnswersCounts++;
            if(!training){
                upgradeCorrect();
            }
            answerAlreadyChecked = true;
        }
        if (Integer.parseInt(correctAnswer) != checkedRadioButtonId && !highlight && !answerAlreadyChecked && !training) {
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
            final Button learnButton = findViewById(R.id.change_learn_button);
            learnButton.setVisibility(View.VISIBLE);
            if (ifLearn.equals("0")){
                learnButton.setText("Zaznacz jako nauczone");
            }else{
                learnButton.setText("Odznacz jako nauczone");
            }
            learnButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    if (ifLearn.equals("0")){
                        setLearn();
                        learnButton.setText("Odznacz jako nauczone");
                        ifLearn="1";

                    }else{
                        unsetLearn();
                        learnButton.setText("Zaznacz jako nauczone");
                        ifLearn="0";
                    }
                }
            });
        }
    }

// click two times back to exit
    boolean isPressed=false;
    public void onBackPressed() {
        if (isPressed){
            finishAffinity();
            System.exit(0);
        }else {
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
