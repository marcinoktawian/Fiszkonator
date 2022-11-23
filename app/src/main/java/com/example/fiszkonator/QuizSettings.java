package com.example.fiszkonator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class QuizSettings extends AppCompatActivity {

    Bundle extrasBundle;
    String indexStr;
    String categoryName;
    String option;
    DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_settings);

        dbHelper = new DatabaseHelper(getApplicationContext());
        try {
            dbHelper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        Get extra values from Bundle
        if(savedInstanceState==null){
            extrasBundle =getIntent().getExtras();
            if(extrasBundle ==null){
                indexStr =null;
                categoryName = null;
                option = null;
            }else{
                indexStr = extrasBundle.getString("id");
                categoryName = extrasBundle.getString("name");
                option = extrasBundle.getString("option");
            }
        }else{
            indexStr = (String) savedInstanceState.getSerializable("id");
            categoryName = (String) savedInstanceState.getSerializable("name");
            option = (String) savedInstanceState.getSerializable("option");
        }

//        Set Title
        TextView tytulTextView = (TextView) findViewById(R.id.setting_tittle);
        tytulTextView.setText(categoryName +"\nUstawienia");

        setLevelSpinner();
        Spinner mspin = (Spinner) findViewById(R.id.spinner_level);
        mspin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                setSpinner();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
        startQuizClick();
    }

    public void setSpinner(){
        Spinner mspin = (Spinner) findViewById(R.id.spinner_questions_number);
        Spinner levelSpinner = (Spinner) findViewById(R.id.spinner_level);
        Integer questionsNumber;
        if(levelSpinner.getSelectedItem() == "ALL"){
            questionsNumber = dbHelper.getQuestionsNumber(indexStr, "",option);
        }else{
            questionsNumber = dbHelper.getQuestionsNumber(indexStr, "" + levelSpinner.getSelectedItem(),option);
        }

        Integer[] items = new Integer[questionsNumber];
        Arrays.setAll(items, i -> i+1);
        Arrays.sort(items, Collections.reverseOrder());
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, items);
        mspin.setAdapter(adapter);
    }

    public void setLevelSpinner(){
        Spinner mspin = (Spinner) findViewById(R.id.spinner_level);
        List<String> questionsLevels = dbHelper.getQuestionsLevels(indexStr,option);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, questionsLevels);
        mspin.setAdapter(adapter);
        setSpinner();
    }

    public void startQuizClick(){
        final Button button = findViewById(R.id.start_quiz);
        Spinner numbersSpinner = (Spinner) findViewById(R.id.spinner_questions_number);
        Spinner levelSpinner = (Spinner) findViewById(R.id.spinner_level);
        Spinner learnLevelSpinner = (Spinner) findViewById(R.id.spinner_learn_level);
        Switch randomSwitch = (Switch) findViewById(R.id.random_switch);
        Switch trainingSwitch = (Switch) findViewById(R.id.training_switch);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Quiz.class);
                intent.putExtra("numbersOfQuestions", "" + numbersSpinner.getSelectedItem());
                intent.putExtra("level", "" + levelSpinner.getSelectedItem());
                intent.putExtra("Learn:evel", "" + learnLevelSpinner.getSelectedItem());
                intent.putExtra("name", categoryName);
                intent.putExtra("random", randomSwitch.isChecked());
                intent.putExtra("training", trainingSwitch.isChecked());
                intent.putExtra("option", option);
                startActivity(intent);
            }
        });
    }

}