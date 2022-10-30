package com.example.medquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper dbHeplper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Button button = findViewById(R.id.start_button);
//        dbHeplper = new DatabaseHelper(getApplicationContext());
//        try {
//            dbHeplper.createDataBase();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        List<String> listUsers = dbHeplper.getAllSubjects();
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                for (String subject : listUsers){
//                    Toast.makeText(getBaseContext(), subject, Toast.LENGTH_SHORT ).show();
//                }
                Intent activity2Intent = new Intent(getApplicationContext(), CategoryList.class);
                startActivity(activity2Intent);
            }
        });

    }

    boolean backPressedOnce = false;

    public void onBackPressed() {
        if (backPressedOnce){
            super.onBackPressed();
        }else {
            backPressedOnce = true;
            Toast.makeText(getApplicationContext(), "press again to exit", Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    backPressedOnce = false;
                }
            }, 2000);
        }
    }

}