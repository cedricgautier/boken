package com.example.boken;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

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
            String firstname = f_name.getText().toString();
            String lastname = l_name.getText().toString();
            String phone_number = p_number.getText().toString();
            String email = null;

            if (currentFirebaseUser != null) {
                email = currentFirebaseUser.getEmail();
            }
            User user = new User(firstname, lastname,phone_number,email);
            Toast.makeText(this, String.join(" ",user.firstname , user.lastname, user.phone, user.email), Toast.LENGTH_SHORT).show();

        });


    }


}