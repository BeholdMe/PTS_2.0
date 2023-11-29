package com.example.pts.Advertisement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pts.R;

public class QualificationsAdActivity extends AppCompatActivity {

    private EditText editTextQualifications, editTextWorkExperience;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qualifications);

        editTextQualifications = findViewById(R.id.editTextQualifications);
        editTextWorkExperience = findViewById(R.id.editTextWorkExperience);
        btnNext = findViewById(R.id.btnNext);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String qualifications = editTextQualifications.getText().toString().trim();
                String workexperience = editTextWorkExperience.getText().toString().trim();

                String selectedCategory = getIntent().getStringExtra("categoryName");
                String firstName = getIntent().getStringExtra("firstName");
                String lastName = getIntent().getStringExtra("lastName");
                String email = getIntent().getStringExtra("email");
                String phone = getIntent().getStringExtra("phone");

                Intent intent2 = new Intent(QualificationsAdActivity.this, InformationAdActivity.class);
                intent2.putExtra("categoryName", selectedCategory);
                intent2.putExtra("firstName", firstName);
                intent2.putExtra("lastName", lastName);
                intent2.putExtra("email", email);
                intent2.putExtra("phone", phone);
                intent2.putExtra("qualifications", qualifications);
                intent2.putExtra("workexperience", workexperience);


                startActivity(intent2);

                finish();
            }
        });


    }

}
