package com.example.pts.Advertisement;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pts.DatabaseManagement.DBHelper;
import com.example.pts.R;
import com.example.pts.TutoringCategories.Category;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BecomeTutorAdActivity extends AppCompatActivity {

    private EditText editTextFirstName, editTextLastName, editTextEmail, editTextPhone, editTextNewUsername, editTextNewPassword, editTextSecurity;
    private Button btnNext;
    String selectedCategory = "";
    DBHelper DB = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_becomeatutor);

        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhone = findViewById(R.id.editTextPhone);
        btnNext = findViewById(R.id.btnNext);

        Spinner categorySpinner = findViewById(R.id.categorySpinner);
        List<String> categoriesList = readCategoriesFromFile("categories.txt");
        String[] categories = categoriesList.toArray(new String[0]);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        categorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                selectedCategory = (String) parentView.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {

            }

        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = editTextFirstName.getText().toString().trim();
                String lastName = editTextLastName.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String phone = editTextPhone.getText().toString().trim();

                Intent intent = new Intent(BecomeTutorAdActivity.this, QualificationsAdActivity.class);
                intent.putExtra("categoryName", selectedCategory);
                intent.putExtra("firstName", firstName);
                intent.putExtra("lastName", lastName);
                intent.putExtra("email", email);
                intent.putExtra("phone", phone);

                startActivity(intent);
                finish();
            }
        });

    }

    private List<String> readCategoriesFromFile(String filename) {
        List<String> list = new ArrayList<>();

        try {
            String catname;
            Cursor cursor = DB.getCat();
            while (cursor.moveToNext()){
                catname = (cursor).getString(0);
                list.add(catname);
            }
        }
        catch (Exception CatReadError){
            CatReadError.printStackTrace();
        }
        return list;

    }








}
