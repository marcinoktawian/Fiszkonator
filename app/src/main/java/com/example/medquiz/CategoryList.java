package com.example.medquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

public class CategoryList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
    }

    public void go_to_settings(View view) {
        Intent activity2Intent = new Intent(getApplicationContext(), QuizSettings.class);
        startActivity(activity2Intent);
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