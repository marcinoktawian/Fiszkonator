package com.example.fiszkonator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Result extends AppCompatActivity {

    Bundle extrasBundle;
    String result;
    String indexStr;
    List<String> finishedQuestions = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        if(savedInstanceState==null){
            extrasBundle =getIntent().getExtras();
            if(extrasBundle ==null){
                result =null;
                indexStr = null;
            }else{
                result = extrasBundle.getString("result");
                indexStr = extrasBundle.getString("numbersOfQuestions");
                for(int item = 0; item < Integer.valueOf(indexStr); item++){
                    finishedQuestions.add(extrasBundle.getString("IdQuestion" +item));
                    finishedQuestions.add(extrasBundle.getString("AnsQuestion" +item));
                }
            }
        }else{
            result = (String) savedInstanceState.getSerializable("result");
            indexStr = (String) savedInstanceState.getSerializable("numbersOfQuestions");
        }

        TextView scoreTextView = (TextView) findViewById(R.id.score);
        scoreTextView.setText(result);

        final Button button = findViewById(R.id.end_quiz);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activity2Intent = new Intent(getApplicationContext(), CategoryList.class);
                startActivity(activity2Intent);
            }
        });

        final Button review = findViewById(R.id.show_answered);
        review.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FinishedQuiz.class);
                intent.putExtra("result", result);
                intent.putExtra("numbersOfQuestions", indexStr);
                for (int item = 0; item < Integer.valueOf(indexStr); item++) {
                    intent.putExtra("IdQuestion" +item, finishedQuestions.get(item*2));
                    intent.putExtra("AnsQuestion" +item, finishedQuestions.get(item*2+1));
                }
                startActivity(intent);
            }
        });

    }
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