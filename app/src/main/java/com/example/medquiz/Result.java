package com.example.medquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Result extends AppCompatActivity {

    Bundle extrasBundle;
    String result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        if(savedInstanceState==null){
            extrasBundle =getIntent().getExtras();
            if(extrasBundle ==null){
                result =null;
            }else{
                result = extrasBundle.getString("result");
            }
        }else{
            result = (String) savedInstanceState.getSerializable("result");
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

    }
}