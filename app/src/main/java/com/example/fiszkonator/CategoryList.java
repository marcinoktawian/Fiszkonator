package com.example.fiszkonator;

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
    Bundle extrasBundle;
    String option;

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
        //        Get extra values from Bundle
        if(savedInstanceState==null){
            extrasBundle =getIntent().getExtras();
            if(extrasBundle ==null){
                option =null;
            }else{
                option = extrasBundle.getString("option");
            }
        }else{
            option = (String) savedInstanceState.getSerializable("option");
        }
        createCategoryList();
    }

//    Get list of all subject from database and create button for them
    public void createCategoryList(){
        List<String> listCategories = dbHeplper.getAllNames(option);
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
                intent.putExtra("option", option);
                startActivity(intent);
            }
        });
        buttonListLayout.addView(CategoryButton);
    }

}