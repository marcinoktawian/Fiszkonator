package com.example.fiszkonator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class InitChoose extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init_choose);
        final Button button = findViewById(R.id.select_category_button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent activity2Intent = new Intent(getApplicationContext(), CategoryList.class);
                activity2Intent.putExtra("option", "Kategoria");
                startActivity(activity2Intent);
            }
        });

        final Button button2 = findViewById(R.id.select_package_button);
//        send to new layout
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CategoryList.class);
                intent.putExtra("option", "Zestaw");
                startActivity(intent);
            }
        });
    }

    //    Click two times back to exit
    boolean isPressed = false;

    public void onBackPressed() {
        if (isPressed) {
            finishAffinity();
            System.exit(0);
        } else {
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