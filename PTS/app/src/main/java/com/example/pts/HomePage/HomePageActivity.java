package com.example.pts.HomePage;

import com.example.pts.LoginAndRegistration.*;
import com.example.pts.Payments.*;
import com.example.pts.TutoringCategories.ViewCategoryActivity;
import com.example.pts.BecomeATutor.*;
import com.example.pts.Search.*;
import com.example.pts.R;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.checkerframework.checker.nullness.qual.NonNull;

public class HomePageActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    TextView userName;
    private Button btnTutoringCategories, btnBecomeATutor, btnSearch, btnPayments, btnAd, btnLogout;
    //    GoogleSignInClient gClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        auth = FirebaseAuth.getInstance();

        userName = findViewById(R.id.userName);
        btnLogout = findViewById(R.id.logout);
        //The three vars are the buttons from the layout of HomePage
        btnTutoringCategories = findViewById(R.id.btnTutoringCategories);
        btnBecomeATutor = findViewById(R.id.btnBecomeATutor);
        btnSearch = findViewById(R.id.btnSearchHome);
        btnPayments = findViewById(R.id.btnPayments);
        btnAd = findViewById(R.id.btnAd);

        String user = User.fromFirebaseUser(FirebaseAuth.getInstance().getCurrentUser()).getfName();

        userName.setText(user);


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

        btnAd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(com.example.pts.HomePage.HomePageActivity.this, com.example.pts.Advertisement.BecomeTutorAdActivity.class));
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                auth.signOut();
                startActivity(new Intent(HomePageActivity.this, LoginActivity.class));
            }
        });
    }
}

/*
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import com.example.pts.R;
import android.widget.Button;
import android.view.View;

import android.os.Bundle;

public class HomePageActivity extends AppCompatActivity {

    private Button btnTutoringCategories, btnBecomeATutor, btnSearch, btnPayments, btnAd;

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
        btnAd = findViewById(R.id.btnAd);

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

        btnAd.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(com.example.pts.HomePage.HomePageActivity.this, com.example.pts.Advertisement.BecomeTutorAdActivity.class));
            }
        });

    }
}
 */

/*Put in Android Manifest file in case other doesn't work
<activity
            android:name=".HomePage.HomePageActivity"
            android:exported="false"
            android:label="@string/title_activity_home_page"
            android:theme="@style/Theme.PTS.NoActionBar" />
 */