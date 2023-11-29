package com.example.pts.DatabaseManagement;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;

import androidx.annotation.Nullable;
import java.time.Duration;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE IF NOT EXISTS USER (\n" +
                "  UserID INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  User_name TEXT UNIQUE NOT NULL,\n" +
                "  Fname TEXT NOT NULL,\n" +
                "  Lname TEXT NOT NULL,\n" +
                "  Phone_number TEXT NOT NULL,\n" +
                "  Email TEXT NOT NULL,\n" +
                "  SecurityQ TEXT NOT NULL,\n" +
                "  Password TEXT NOT NULL,\n" +
                "  City TEXT,\n" +
                "  State TEXT,\n" +
                "  Zip_Code INTEGER(5),\n" +
                "  Street_address TEXT\n" +
                ")");
        DB.execSQL("CREATE TABLE IF NOT EXISTS TUTORLISTING (\n" +
                "  TutorID INTEGER,\n" +
                "  List_Num INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  Catagory TEXT NOT NULL,\n" +
                "  Fname TEXT NOT NULL,\n" +
                "  Lname TEXT NOT NULL,\n" +
                "  Email TEXT NOT NULL,\n" +
                "  Phone_number TEXT NOT NULL,\n" +
                "  Work_experience TEXT NOT NULL,\n" +
                "  Qualifications TEXT NOT NULL,\n" +
                "  Is_ad BOOLEAN NOT NULL,\n" +
                "  Price TEXT NOT NULL,\n" +
                "  Time TEXT NOT NULL,\n" +
                "  Distance TEXT NOT NULL,\n" +
                "  State TEXT,\n" +
                "  City TEXT,\n" +
                "  FOREIGN KEY (TutorID) REFERENCES USER(UserID),\n" +
                "  FOREIGN KEY (Catagory) REFERENCES CATEGORY(Catagory_name)\n" +
                ")");
        DB.execSQL("CREATE TABLE IF NOT EXISTS CATEGORY (\n" +
                "  Catagory_name TEXT PRIMARY KEY,\n" +
                "  DESCRIPTION TEXT NOT NULL\n" +
                ");");
        DB.execSQL("CREATE TABLE IF NOT EXISTS STUDENT (\n" +
                "  TutorID INTEGER NOT NULL,\n" +
                "  StudentID INTEGER NOT NULL,\n" +
                "  List_Num INTEGER,\n" +
                "  PRIMARY KEY (TutorID, StudentID),\n" +
                "  FOREIGN KEY (TutorID) REFERENCES TutorID(TutorID),\n" +
                "  FOREIGN KEY (List_Num) REFERENCES TutorID(List_Num),\n" +
                "  FOREIGN KEY (StudentID) REFERENCES USER(UserID)\n" +
                ");");
        DB.execSQL("CREATE TABLE IF NOT EXISTS REVIEW (\n" +
                "  StudentID INTEGER,\n" +
                "  TutorID INTEGER,\n" +
                "  Star_Count INTEGER NOT NULL,\n" +
                "  Comment TEXT,\n" +
                "  PRIMARY KEY (TutorID, StudentID),\n" +
                "  FOREIGN KEY (TutorID) REFERENCES STUDENT(TutorID),\n" +
                "  FOREIGN KEY (StudentID) REFERENCES STUDENT(StudentID)\n" +
                ");");
        DB.execSQL("CREATE TABLE IF NOT EXISTS PAYMENTHISTORY (\n" +
                "  PayersID INTEGER NOT NULL,\n" +
                "  Payment_number INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  Payment_amount DOUBLE NOT NULL,\n" +
                "  Date TEXT NOT NULL,\n" +
                "  Time TEXT NOT NULL,\n" +
                "  ReciversID INTEGER,\n" +
                "  RecivedAmt DOUBLE,\n" +
                "  FOREIGN KEY (PayersID) REFERENCES USER(UserID)\n" +
                ");");
        DB.execSQL("CREATE TABLE IF NOT EXISTS SAVEDPAYMENT (\n" +
                "  UserID INTEGER,\n" +
                "  User_cardCnt INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "  Email TEXT NOT NULL,\n" +
                "  Phone_number TEXT NOT NULL,\n" +
                "  Fname TEXT NOT NULL,\n" +
                "  Lname TEXT NOT NULL,\n" +
                "  City TEXT NOT NULL,\n" +
                "  STATE TEXT NOT NULL,\n" +
                "  Zip_Code INTEGER(5) NOT NULL,\n" +
                "  Street_address TEXT NOT NULL,\n" +
                "  Card_number TEXT,\n" +
                "  Cvn TEXT,\n" +
                "  Card_expDate TEXT,\n" +
                "  PayPal_username TEXT,\n" +
                "  Payment_type TEXT NOT NULL,\n" +
                "  FOREIGN KEY (UserID) REFERENCES USER(UserID)\n" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int j) {
        DB.execSQL("DROP TABLE IF EXISTS USER");
        DB.execSQL("DROP TABLE IF EXISTS TUTORLISTING");
        DB.execSQL("DROP TABLE IF EXISTS CATEGORY");
        DB.execSQL("DROP TABLE IF EXISTS STUDENT");
        DB.execSQL("DROP TABLE IF EXISTS REVIEW");
        DB.execSQL("DROP TABLE IF EXISTS PAYMENTHISTORY");
        DB.execSQL("DROP TABLE IF EXISTS SAVEDPAYMENT");
    }


    public int insertNewUser(String newUsername, String security, String firstName, String lastName, String email, String phone, String password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Fname", firstName);
        contentValues.put("Lname", lastName);
        contentValues.put("Email", email);
        contentValues.put("Phone_number", phone);
        contentValues.put("User_name", newUsername);
        contentValues.put("SecurityQ", security);
        contentValues.put("Password", password);

        long result = DB.insert("USER", null, contentValues);

        if (result != -1) {
            return 1;


//            // Retrieve the User_Number from the USERS table
//            Cursor cursor = DB.rawQuery("SELECT User_Number FROM USERS WHERE Username = ?", new String[]{newUsername});
//            if (cursor.getCount() > 0) {
//                int retrievedUserId = cursor.getInt(cursor.getColumnIndex("User_Number"));
//                cursor.close();
//                return retrievedUserId;
//            } else {
//                cursor.close();
//                return -1;
//            }

        } else {
            return -1;
        }


    }


    public int insertNewTutor(String selectedCategory, String firstName, String lastName, String email, String phone, String qualifications, String workexperience, String city, String state, String distance, String time, String price, Boolean is_ad) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("Catagory", selectedCategory);
        contentValues.put("Fname", firstName);
        contentValues.put("Lname", lastName);
        contentValues.put("Email", email);
        contentValues.put("Phone_number", phone);
        contentValues.put("Qualifications", qualifications);
        contentValues.put("Work_experience", workexperience);
        contentValues.put("City", city);
        contentValues.put("State", state);
        contentValues.put("Distance", distance);
        contentValues.put("Fname", time);
        contentValues.put("Price", price);
        contentValues.put("Is_ad", is_ad);



        long result = DB.insert("TUTORLISTING", null, contentValues);

        if (result != -1) {
            return 1;


//            // Retrieve the User_Number from the USERS table
//            Cursor cursor = DB.rawQuery("SELECT User_Number FROM USERS WHERE Username = ?", new String[]{newUsername});
//            if (cursor.getCount() > 0) {
//                int retrievedUserId = cursor.getInt(cursor.getColumnIndex("User_Number"));
//                cursor.close();
//                return retrievedUserId;
//            } else {
//                cursor.close();
//                return -1;
//            }

        } else {
            return -1;
        }


    }













}






