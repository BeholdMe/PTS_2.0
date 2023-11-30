package com.example.pts.LoginAndRegistration;

import com.example.pts.R;
import com.example.pts.HomePage.*;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.checkerframework.checker.nullness.qual.NonNull;

public class LoginActivity extends AppCompatActivity
{
    private EditText loginEmail, loginPassword;
    private TextView signupRedirectText;
    private Button loginButton;
    private FirebaseAuth auth;
    TextView forgotPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        loginEmail = findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        signupRedirectText = findViewById(R.id.signUpRedirectText);
        forgotPassword = findViewById(R.id.forgot_password);
        auth = FirebaseAuth.getInstance();
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = loginEmail.getText().toString();
                String pass = loginPassword.getText().toString();
                if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    if (!pass.isEmpty()) {
                        auth.signInWithEmailAndPassword(email, pass)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {

                                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(LoginActivity.this, HomePageActivity.class));
                                        finish();
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        loginPassword.setError("Empty fields are not allowed");
                    }
                } else if (email.isEmpty()) {
                    loginEmail.setError("Empty fields are not allowed");
                } else {
                    loginEmail.setError("Please enter correct email");
                }
            }
        });
        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                View dialogView = getLayoutInflater().inflate(R.layout.forgot_dialog, null);
                EditText emailBox = dialogView.findViewById(R.id.login_email);
                builder.setView(dialogView);
                AlertDialog dialog = builder.create();
                dialogView.findViewById(R.id.btnReset).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String userEmail = emailBox.getText().toString();
                        if (TextUtils.isEmpty(userEmail) && !Patterns.EMAIL_ADDRESS.matcher(userEmail).matches()){
                            Toast.makeText(LoginActivity.this, "Enter your registered email id", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        auth.sendPasswordResetEmail(userEmail).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()){
                                    Toast.makeText(LoginActivity.this, "Check your email", Toast.LENGTH_SHORT).show();
                                    dialog.dismiss();
                                } else {
                                    Toast.makeText(LoginActivity.this, "Unable to send, failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                });
                dialogView.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
            }
        });
    }
}


/*
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pts.DatabaseManagement.DBHelper;
import com.example.pts.R;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    private TextView textViewSignIn, textViewForgotPassword, textViewRegisterHere;
    private EditText editTextUsername, editTextPassword;
    private Button btnSubmit;
    DBHelper DB = new DBHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);



        editTextUsername = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextNewPassword);
        textViewSignIn = findViewById(R.id.textViewSignIn);
        textViewForgotPassword = findViewById(R.id.textViewForgotPassword);
        textViewRegisterHere = findViewById(R.id.textViewRegisterHere);
        btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = editTextUsername.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                if (login(username, password)) {
                    showToast("Login successful");
                    startActivity(new Intent(com.example.pts.LoginAndRegistration.LoginActivity.this, com.example.pts.HomePage.HomePageActivity.class));
                } else {
                    showToast("Invalid username or password");
                }

            }
        });
        textViewRegisterHere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
        textViewForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
            }
        });

    }

    private boolean login(String username, String password) {

        boolean returnval = false;
        String dbusername, dbpassword;
        try {
            Cursor cursor = DB.getLogin();
            while (cursor.moveToNext()){
                dbusername = (cursor).getString(1);
                if (dbusername.equals(username)) {
                    dbpassword = (cursor).getString(7);
                    if (dbpassword.equals(password)){
                        returnval = true;
                        //return returnval;
                    }
                }
            }
        }
        catch (Exception LoginFail){
            LoginFail.printStackTrace();
        }


        try {
            FileInputStream fileInputStream = openFileInput("login.txt");
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7 && parts[0].equals(username) && parts[1].equals(password)) {
                    returnval = true;
                    /*
                    This method works even if returnval above is commented out,
                    But for testing purposes it is left in
                    */
                    //return returnval;
/*                }
            }

            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



        return returnval;
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
*/