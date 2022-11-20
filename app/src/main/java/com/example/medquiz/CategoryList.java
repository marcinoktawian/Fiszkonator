package com.example.medquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.io.IOException;
import java.util.List;

public class CategoryList extends AppCompatActivity {

    DatabaseHelper dbHeplper;

//    on create function with init db
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        dbHeplper = new DatabaseHelper(getApplicationContext());
        try {
            dbHeplper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        createCategoryList();
    }

//    Get list of all subject from database and create button for them
    public void createCategoryList(){
        List<String> listCategories = dbHeplper.getAllSubjects();
        Integer id = 1;
        for (String subject : listCategories){
            createButon(subject,id);
            id++;
        }
    }

//    Create button for subject
    public void createButon(String categoryName, final int newId) {
        LinearLayout buttonListLayout = (LinearLayout) findViewById(R.id.categories_buttons_layout);
        final Button CategoryButton = new Button(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(150, 20, 150, 20);
        CategoryButton.setText(categoryName);
        CategoryButton.setTextColor(ContextCompat.getColor(getApplicationContext(),R.color.white));
        CategoryButton.setId(newId);
        CategoryButton.setLayoutParams(params);
        CategoryButton.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.custom_button));
        CategoryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), QuizSettings.class);
                intent.putExtra("id", "" + newId);
                intent.putExtra("name", "" + categoryName);
                startActivity(intent);
            }
        });
        buttonListLayout.addView(CategoryButton);
    }

//    Click two times back to exit
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