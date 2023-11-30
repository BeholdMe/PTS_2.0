package com.example.pts.LoginAndRegistration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/*
FirebaseApp.initializeApp(this);
FirebaseAuth mAuth;
mAuth = FirebaseAuth.getInstance();





     mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NotNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

 */

import androidx.appcompat.app.AppCompatActivity;

import com.example.pts.DatabaseManagement.DBHelper;
import com.example.pts.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.database.Cursor;
//import androidx.annotation.Nullable;

public class RegisterActivity extends AppCompatActivity {

    private TextView textViewRegister;
    private EditText editTextFirstName, editTextLastName, editTextEmail, editTextPhone, editTextNewUsername, editTextNewPassword, editTextSecurity;
    private Button btnRegister;
    FirebaseDatabase db;
    DatabaseReference reference;
    //DBHelper DB = new DBHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        textViewRegister = findViewById(R.id.textViewRegister);
        editTextFirstName = findViewById(R.id.editTextFirstName);
        editTextLastName = findViewById(R.id.editTextLastName);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPhone = findViewById(R.id.editTextPhone);
        editTextNewPassword = findViewById(R.id.editTextNewPassword);
        editTextSecurity = findViewById(R.id.editTextSecurity);
        btnRegister = findViewById(R.id.btnRegister);
        FirebaseApp.initializeApp(this);
        FirebaseAuth mAuth;
        mAuth = FirebaseAuth.getInstance();


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstName = editTextFirstName.getText().toString().trim();
                String lastName = editTextLastName.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                String phone = editTextPhone.getText().toString().trim();
                String newPassword = editTextNewPassword.getText().toString().trim();
                String security = editTextSecurity.getText().toString().trim();

                if (!isValidPassword(newPassword)) {
                    Toast.makeText(RegisterActivity.this, "Password must include one letter, number, Capital letter, and wild character", Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.createUserWithEmailAndPassword(email, newPassword)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NotNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                        if (!firstName.isEmpty() && !lastName.isEmpty() && !phone.isEmpty() && !security.isEmpty()) {

                                            db = FirebaseDatabase.getInstance();
                                            reference = db.getReference("Users");


                                            user = mAuth.getCurrentUser();
                                            String uid = user.getUid();

                                            User userData = new User(firstName, lastName, email, newPassword, phone, security);

                                            reference.child(uid).push().setValue(userData);

                                            Toast.makeText(RegisterActivity.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                        } else {
                                            Toast.makeText(RegisterActivity.this, "SignUp Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                            });
                }
            }

        });
    }

    public static boolean isValidPassword(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!]).+$";

        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }
}

/*
if(newUsername.length()<8){

                    showToast("Username should be 8 characters long");

                }
                else if(isValidUsername(newUsername)) {
                    showToast("Username already exists");
                }
                else if(!isValidPassword(newPassword)) {
                    showToast("Password must include one letter, number, Capital letter, and wild character");
                }
                else {
                    register(newUsername, newPassword, security, firstName, lastName, email, phone);
                    showToast("Successfully Registered");
                    finish();
                }
            }
        });


    }

    private void register(String newUsername, String newPassword, String security, String firstName, String lastName, String email, String phone) {
        try {
            String data = newUsername + "," + newPassword +  "," + security +  "," + firstName +  "," + lastName +  "," + email +  "," + phone + "\n";
            FileOutputStream fileOutputStream = openFileOutput("login.txt", MODE_APPEND);
            fileOutputStream.write(data.getBytes());
            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try{
            //DBHelper DB = new DBHelper(this);
            int userID = DB.insertNewUser(newUsername, security, firstName, lastName, email, phone, newPassword);
        }
        catch (Exception DBReg){
            DBReg.printStackTrace();
        }

    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public boolean isValidUsername(String username){
            try {
                FileInputStream fileInputStream = openFileInput("login.txt");
                InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts[0].equals(username)) {
                        return true;
                    }
                }

                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return false;
    }


}
 */