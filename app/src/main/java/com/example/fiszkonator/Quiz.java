package com.example.fiszkonator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Quiz extends AppCompatActivity {

    Bundle extrasBundle;
    String indexStr;
    String difficultyLevel;
    String learnLevel;
    String categoryName;
    Boolean random;
    Boolean ifPolishFirst;
    String option;
    Integer index = 0;
    Integer correctAnswersCounts = 0;
    Cursor questions;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    Boolean answerAlreadyChecked;
    String questionId;
    String polishWord;
    String foreignWord;
    String polishUsage;
    String foreignUsage;
    String comment;
    String nagranie;
    Integer questionLevel;
    Boolean showPolishNow;
    Boolean questionLearn;
    Boolean questionNotLearn;
    MediaPlayer mp;




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
                difficultyLevel = null;
                learnLevel= null;
                categoryName = null;
                random = null;
                option = null;
                ifPolishFirst = null;
            } else {
                indexStr = extrasBundle.getString("numbersOfQuestions");
                difficultyLevel = extrasBundle.getString("level");
                learnLevel = extrasBundle.getString("learnLevel");
                categoryName = extrasBundle.getString("name");
                random = extrasBundle.getBoolean("random");
                option = extrasBundle.getString("option");
                ifPolishFirst= extrasBundle.getBoolean("ifPolishFirst");
            }
        } else {
            indexStr = (String) savedInstanceState.getSerializable("numbersOfQuestions");
            difficultyLevel = (String) savedInstanceState.getSerializable("level");
            learnLevel = (String) savedInstanceState.getSerializable("learnLevel");
            categoryName = (String) savedInstanceState.getSerializable("name");
            random = (Boolean) savedInstanceState.getSerializable("random");
            option = (String) savedInstanceState.getSerializable("option");
            ifPolishFirst= (Boolean) savedInstanceState.getSerializable("ifPolishFirst");
        }

//        Get questions and show the first one
        questions = this.getQuestions(categoryName, indexStr);
        questions.moveToFirst();

        answerAlreadyChecked=false;
        setQuestion();
        setQuestionNumber();

        final Button learnButton = findViewById(R.id.learn_button);
        learnButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                learnQuestion();
            }
        });

        final Button notLearnButton = findViewById(R.id.not_learn_button);
        notLearnButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                notLearnQuestion();
            }
        });

//        If set training the button won't show
        final Button checkButton= findViewById(R.id.check_question);
        checkButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                turnCard();
            }
        });
        final Button nextButton= findViewById(R.id.next_question);
        nextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                nextQuestionButton();
            }
        });
        final ImageView soundButton = findViewById(R.id.play_sound);
        soundButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                playSound();
            }
        });
    }

//    Show question, answers and set stats
    public void setQuestion() {
        questionId =questions.getString(0);
        polishWord =questions.getString(1);
        foreignWord =questions.getString(4);
        polishUsage =questions.getString(3);
        foreignUsage=questions.getString(5);
        comment = questions.getString(2);
        questionLevel = questions.getInt(8);
        setStats(questions.getString(6),questions.getString(7));
        nagranie = questions.getString(9);
        if(ifPolishFirst){
            showPolishNow = true;
        }else {
            showPolishNow = false;
        }
        questionLearn=false;
        questionNotLearn=false;
        turnCard();
    }

//    Set stats wrong/correct number of answers
    public void setStats(String wrong, String correct){
        TextView wrongTextView = (TextView) findViewById(R.id.wrong_stats_number);
        TextView correctTextView = (TextView) findViewById(R.id.correct_stats_number);
        wrongTextView.setText(wrong);
        correctTextView.setText(correct);
    }

    public void turnCard(){
        TextView wordTextView = (TextView) findViewById(R.id.question);
        TextView commentTextView = (TextView) findViewById(R.id.comment);
        TextView usageTextView = (TextView) findViewById(R.id.word_usage);
        if(showPolishNow){
             wordTextView.setText(polishWord);
             if(comment != null){
                 commentTextView.setText(comment);
                 commentTextView.setVisibility(View.VISIBLE);
             }else{
                 commentTextView.setVisibility(View.GONE);
             }
             usageTextView.setText(polishUsage);
        }else{
            wordTextView.setText(foreignWord);
            commentTextView.setVisibility(View.GONE);
            usageTextView.setText(foreignUsage);
        }
        showPolishNow=!showPolishNow;
    }

//    If this is last question go to result if not show next question
    public void nextQuestionButton() {
        ContentValues values = new ContentValues();
        try{
            mp.stop();
        }catch(Exception e) {
            Log.e("tle99", e.getMessage());
        }
        if(questionLearn){
            upgradeCorrect();
            correctAnswersCounts++;
            if(questionLevel<5){
                questionLevel++;
                values.put("PoziomNauczenia",questionLevel);
                db.update("Statystyki", values, "IdFiszki = ?", new String[]{questionId});
            }
        }else{
            upgradeError();
            if(questionLevel>1){
                questionLevel--;
                values.put("PoziomNauczenia",questionLevel);
                db.update("Statystyki", values, "IdFiszki = ?", new String[]{questionId});
            }
        }
        if (index == Integer.parseInt(indexStr)) {
            db.close();
            Intent intent = new Intent(getApplicationContext(), Result.class);
            intent.putExtra("result", correctAnswersCounts + "/" + indexStr);
            startActivity(intent);
        } else {
            answerAlreadyChecked=false;
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
    public Cursor getQuestions(String nazwaOpcji, String limit) {
        Cursor c = null;
        String optionQuery = "";
        if (option.equals("Kategoria")){
            optionQuery = "JOIN Kategoria k ON k.IdKategorii=f.IdKategorii where k.NazwaKategorii = \"" + nazwaOpcji;
        }else{
            optionQuery = "JOIN Zestaw z ON z.idZestawu=f.idZestawu where z.NazwaZestawu = \"" + nazwaOpcji;
        }

        try {
            if (random) {
                if(difficultyLevel.equals("ALL")){
                    if(learnLevel.equals("ALL")){
                        c = db.rawQuery("SELECT f.IdFiszki, f.Polski, f.Komentarz, f.Uzyciepolski, f.Obcy, f.UzycieObcy, s.IloscBlednychOdp, s.IloscPrawidlowychOdp, s.PoziomNauczenia, f.Nagranie FROM Fiszka f JOIN Statystyki s ON s.IdFiszki=f.IdFiszki " + optionQuery + "\" ORDER BY RANDOM() LIMIT " + limit, null);
                    }else{
                        c = db.rawQuery("SELECT f.IdFiszki, f.Polski, f.Komentarz, f.Uzyciepolski, f.Obcy, f.UzycieObcy, s.IloscBlednychOdp, s.IloscPrawidlowychOdp, s.PoziomNauczenia, f.Nagranie FROM Fiszka f JOIN Statystyki s ON s.IdFiszki=f.IdFiszki " + optionQuery + "\" AND s.PoziomNauczenia = "+ learnLevel + " ORDER BY RANDOM() LIMIT " + limit, null);
                    }
                }else{
                    if(learnLevel.equals("ALL")){
                        c = db.rawQuery("SELECT f.IdFiszki, f.Polski, f.Komentarz, f.Uzyciepolski, f.Obcy, f.UzycieObcy, s.IloscBlednychOdp, s.IloscPrawidlowychOdp, s.PoziomNauczenia, f.Nagranie FROM Fiszka f JOIN Statystyki s ON s.IdFiszki=f.IdFiszki " + optionQuery + "\" AND f.Poziom = \"" + difficultyLevel + "\" ORDER BY RANDOM() LIMIT " + limit, null);
                    }else{
                        c = db.rawQuery("SELECT f.IdFiszki, f.Polski, f.Komentarz, f.Uzyciepolski, f.Obcy, f.UzycieObcy, s.IloscBlednychOdp, s.IloscPrawidlowychOdp, s.PoziomNauczenia, f.Nagranie FROM Fiszka f JOIN Statystyki s ON s.IdFiszki=f.IdFiszki " + optionQuery + "\" AND f.Poziom = \"" + difficultyLevel + "\" AND s.PoziomNauczenia = "+ learnLevel + " ORDER BY RANDOM() LIMIT " + limit, null);
                    }
                }
            }else{
                if(difficultyLevel.equals("ALL")){
                    if(learnLevel.equals("ALL")){
                        c = db.rawQuery("SELECT f.IdFiszki, f.Polski, f.Komentarz, f.Uzyciepolski, f.Obcy, f.UzycieObcy, s.IloscBlednychOdp, s.IloscPrawidlowychOdp, s.PoziomNauczenia, f.Nagranie FROM Fiszka f JOIN Statystyki s ON s.IdFiszki=f.IdFiszki " + optionQuery + "\" LIMIT " + limit, null);
                    }else{
                        c = db.rawQuery("SELECT f.IdFiszki, f.Polski, f.Komentarz, f.Uzyciepolski, f.Obcy, f.UzycieObcy, s.IloscBlednychOdp, s.IloscPrawidlowychOdp, s.PoziomNauczenia, f.Nagranie FROM Fiszka f JOIN Statystyki s ON s.IdFiszki=f.IdFiszki " + optionQuery + "\" AND s.PoziomNauczenia = "+ learnLevel + " LIMIT " + limit, null);
                    }
                }else{
                    if(learnLevel.equals("ALL")){
                        c = db.rawQuery("SELECT f.IdFiszki, f.Polski, f.Komentarz, f.Uzyciepolski, f.Obcy, f.UzycieObcy, s.IloscBlednychOdp, s.IloscPrawidlowychOdp, s.PoziomNauczenia, f.Nagranie FROM Fiszka f JOIN Statystyki s ON s.IdFiszki=f.IdFiszki " + optionQuery + "\" AND f.Poziom = \"" + difficultyLevel + "\" LIMIT " + limit, null);
                    }else{
                        c = db.rawQuery("SELECT f.IdFiszki, f.Polski, f.Komentarz, f.Uzyciepolski, f.Obcy, f.UzycieObcy, s.IloscBlednychOdp, s.IloscPrawidlowychOdp, s.PoziomNauczenia, f.Nagranie FROM Fiszka f JOIN Statystyki s ON s.IdFiszki=f.IdFiszki " + optionQuery + "\" AND f.Poziom = \"" + difficultyLevel + "\" AND s.PoziomNauczenia = "+ learnLevel + " LIMIT " + limit, null);
                    }
                }
            }
            if (c == null) return null;
        } catch (Exception e) {
            Log.e("tle99", e.getMessage());
        }
        return c;
    }

    public void learnQuestion(){
        if(!questionLearn){
            questionLearn = true;
            questionNotLearn = false;
            if(questionLevel==5) {
                Toast.makeText(getApplicationContext(), "Max level achived", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(getApplicationContext(), "Fiszka nauczona", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "Fiszka już oznaczona jako nienauczona", Toast.LENGTH_SHORT).setGravity(Gravity.TOP|Gravity.LEFT, 0, 0);
        }
    }

    public void notLearnQuestion(){
        if(!questionNotLearn){
            questionNotLearn = true;
            questionLearn = false;
            if(questionLevel==1) {
                Toast.makeText(getApplicationContext(), "Min level achived", Toast.LENGTH_SHORT).show();
                return;
            }
            Toast.makeText(getApplicationContext(), "Fiszka nienauczona", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "Fiszka już oznaczona jako nienauczona", Toast.LENGTH_SHORT).show();
        }
    }


    //    Upgrade number of wrong answers in database
    public void upgradeError(){
        Cursor cursor =db.rawQuery("SELECT IloscBlednychOdp FROM Statystyki WHERE IdFiszki = " + questionId, null);
        int cardnum=0;
        cursor.moveToFirst();
        if( cursor != null )
        {
            cardnum = cursor.getInt(0);
            cardnum++;
        }
        ContentValues values = new ContentValues();
        values.put("IloscBlednychOdp",cardnum);
        db.update("Statystyki", values, "IdFiszki = ?", new String[]{questionId});
    }

    //    Upgrade number of correct answers in database
    public void upgradeCorrect(){
        Cursor cursor =db.rawQuery("SELECT IloscPrawidlowychOdp FROM Statystyki WHERE IdFiszki = " + questionId, null);
        int cardnum=0;
        cursor.moveToFirst();
        if( cursor != null )
        {
            cardnum = cursor.getInt(0);
            cardnum++;
        }
        ContentValues values = new ContentValues();
        values.put("IloscPrawidlowychOdp",cardnum);
        db.update("Statystyki", values, "IdFiszki = ?", new String[]{questionId});
    }

    public void playSound(){
        try{
            mp = MediaPlayer.create(getApplicationContext(), getResources().getIdentifier(nagranie, "raw", getPackageName()));
            mp.start();
        } catch (Exception e) {
            Log.e("tle99", e.getMessage());
            Toast.makeText(getApplicationContext(), "Brak nagrania audio", Toast.LENGTH_SHORT).show();
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
