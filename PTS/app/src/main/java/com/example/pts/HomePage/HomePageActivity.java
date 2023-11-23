package com.example.pts.HomePage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.pts.R;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;


import androidx.appcompat.app.AppCompatActivity;

public class HomePageActivity extends AppCompatActivity {

    private Button btnTutoringCategories, btnBecomeATutor, btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        btnTutoringCategories = findViewById(R.id.btnTutoringCategories);
        btnBecomeATutor = findViewById(R.id.btnBecomeATutor);
        btnSearch = findViewById(R.id.btnSearch);

        btnTutoringCategories.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(com.example.pts.HomePage.HomePageActivity.this, com.example.pts.TutoringCategories.ViewCategoryActivity.class));
            }
        });

        btnBecomeATutor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(com.example.pts.HomePage.HomePageActivity.this, com.example.pts.BecomeATutor.BecomeATutorActivity.class));
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}