package com.example.pts.HomePage;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import com.example.pts.R;
import android.widget.Button;
import android.view.View;

import android.os.Bundle;

public class HomePageActivity extends AppCompatActivity {

    private Button btnTutoringCategories, btnBecomeATutor, btnSearch, btnPayments;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        //The three vars are the buttons from the layout of HomePage
        btnTutoringCategories = findViewById(R.id.btnTutoringCategories);
        btnBecomeATutor = findViewById(R.id.btnBecomeATutor);
        btnSearch = findViewById(R.id.btnSearchHome);
        btnPayments = findViewById(R.id.btnPayments);

        //Listeners to send user to the page they click on
        btnTutoringCategories.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(com.example.pts.HomePage.HomePageActivity.this, com.example.pts.TutoringCategories.ViewCategoryActivity.class));
            }
        });

        btnBecomeATutor.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(com.example.pts.HomePage.HomePageActivity.this, com.example.pts.BecomeATutor.BecomeATutorActivity.class));
            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(com.example.pts.HomePage.HomePageActivity.this, com.example.pts.Search.SearchActivity.class));
            }
        });

        btnPayments.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(com.example.pts.HomePage.HomePageActivity.this, com.example.pts.Payments.PaymentPage.class));
            }
        });
    }
}

/*Put in Android Manifest file in case other doesn't work
<activity
            android:name=".HomePage.HomePageActivity"
            android:exported="false"
            android:label="@string/title_activity_home_page"
            android:theme="@style/Theme.PTS.NoActionBar" />
 */