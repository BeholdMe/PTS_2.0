package com.example.pts.LoginAndRegistration;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class User
{
    String fName, lName, email, password, phone, security;

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSecurity() {
        return security;
    }

    public void setSecurity(String security) {
        this.security = security;
    }

    public User(String fName, String lName, String email, String password, String phone, String security) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.security = security;
    }

    public static User fromFirebaseUser(FirebaseUser firebaseUser)
    {
        if(firebaseUser == null)
        {
            return null;
        }

        String email = firebaseUser.getEmail();
        String fName = getNamefromUser(email);
        String lName = "";
        String password = "";
        String phone = firebaseUser.getPhoneNumber();
        String security = "";

        return new User(fName, lName, email, password, phone, security);
    }


    private static String getNamefromUser(String email)
    {
        final String[] userFName = new String[1];
        ArrayList<User> users = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference("Users");
        Query allUsers = FirebaseDatabase.getInstance().getReference("Users");
        allUsers.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren())
                {
                    for(DataSnapshot dataSnapshotSnapshot: dataSnapshot.getChildren())
                    {
                        users.add(dataSnapshotSnapshot.getValue(User.class));
                    }
                    for(User user : users)
                    {
                        if(user.getEmail().equals(email))
                        {
                             userFName[0] = user.getfName();
                        }
                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
        return userFName[0];
    }

    public User() {}

}
