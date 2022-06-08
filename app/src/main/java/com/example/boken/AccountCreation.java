package com.example.boken;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AccountCreation extends AppCompatActivity {
    private static final String TAG = "AccountCreation";
    public FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser currentFirebaseUser = mAuth.getCurrentUser();
        if (currentFirebaseUser == null) {
            Log.e(TAG, "onStart: Current Firebase User is empty");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_creation);
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentFirebaseUser = mAuth.getCurrentUser();
        EditText f_name = findViewById(R.id.register_name);
        EditText l_name = findViewById(R.id.register_lastname);
        EditText p_number = findViewById(R.id.register_phone_number);
        Button btn_next = findViewById(R.id.b_account_creation_next);


        btn_next.setOnClickListener(view -> {
            sendUserToDatabase(currentFirebaseUser, f_name, l_name, p_number);
            Toast.makeText(this, "User Created", Toast.LENGTH_SHORT).show();
            Intent homepage = new Intent(AccountCreation.this,Homepage.class);
            startActivity(homepage);
        });

    }

    public void sendUserToDatabase(FirebaseUser currentFirebaseUser, EditText f_name, EditText l_name, EditText p_number){
        String firstname = f_name.getText().toString();
        String lastname = l_name.getText().toString();
        String phone_number = p_number.getText().toString();
        String email = null;
        String uid = null;

        if (currentFirebaseUser != null) {
            email = currentFirebaseUser.getEmail();
            uid = currentFirebaseUser.getUid();
        }
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://boken-c2118-default-rtdb.europe-west1.firebasedatabase.app");
        DatabaseReference myRef = database.getReference("/Users/"+uid);
        User user = new User(uid,firstname, lastname,phone_number,email);
        myRef.setValue(user); // Sends values to Database

    }


}