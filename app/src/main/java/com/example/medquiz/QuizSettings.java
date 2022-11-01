package com.example.medquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import java.io.IOException;
import java.util.Arrays;

public class QuizSettings extends AppCompatActivity {

    Bundle extrasBundle;
    String indexStr;
    String categoryName;
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
            }else{
                indexStr = extrasBundle.getString("id");
                categoryName = extrasBundle.getString("name");
            }
        }else{
            indexStr = (String) savedInstanceState.getSerializable("id");
            categoryName = (String) savedInstanceState.getSerializable("name");
        }

//        Set Title
        TextView tytulTextView = (TextView) findViewById(R.id.setting_tittle);
        tytulTextView.setText(categoryName +"\nUstawienia");

        setSpinner();
        startQuizClick();
    }

    public void setSpinner(){
        Spinner mspin = (Spinner) findViewById(R.id.spinner_questions_number);
        Integer questionsNumber = dbHelper.getQuestionsNumber(indexStr);
        Integer[] items = new Integer[questionsNumber];
        Arrays.setAll(items, i -> i+1);
        ArrayAdapter<Integer> adapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, items);
        mspin.setAdapter(adapter);
    }

    public void startQuizClick(){
        final Button button = findViewById(R.id.start_quiz);
        Spinner numbersSpinner = (Spinner) findViewById(R.id.spinner_questions_number);
        Switch randomSwitch = (Switch) findViewById(R.id.random_switch);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Quiz.class);
                intent.putExtra("numbersOfQuestions", "" + numbersSpinner.getSelectedItem());
                intent.putExtra("name", categoryName);
                intent.putExtra("random", randomSwitch.isChecked());
                startActivity(intent);
            }
        });
    }
}