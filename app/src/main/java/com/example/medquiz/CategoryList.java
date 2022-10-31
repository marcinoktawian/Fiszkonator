package com.example.medquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.ActionBar;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category_list);
        createCategoryList();
    }

//    public void go_to_settings(View view) {
//        Intent activity2Intent = new Intent(getApplicationContext(), QuizSettings.class);
//        startActivity(activity2Intent);
//    }

    public void createCategoryList(){
        dbHeplper = new DatabaseHelper(getApplicationContext());
        try {
            dbHeplper.createDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> listCategories = dbHeplper.getAllSubjects();
        Integer id = 1;
        for (String subject : listCategories){
            createButon(subject,id);
            id++;
        }
    }

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
                startActivity(intent);
            }
        });
        buttonListLayout.addView(CategoryButton);
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