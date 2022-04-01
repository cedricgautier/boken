package com.example.boken;

import android.os.Bundle;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        EditText login_email =  findViewById(R.id.login_email);
        EditText login_password = findViewById(R.id.login_password);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

}