package com.example.boken;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_login = findViewById(R.id.button_login);
        Button btn_register = findViewById(R.id.button_register);

        btn_login.setOnClickListener(view -> {
            Intent login_page = new Intent(MainActivity.this, Login.class);
            startActivity(login_page);
        });

        btn_register.setOnClickListener(view -> {
            Intent register_page = new Intent(MainActivity.this, Register.class);
            startActivity(register_page);
        });
    }

    @Override
    public void onBackPressed () {
        quit();
    }

    public void quit() {
        new AlertDialog.Builder(MainActivity.this)
                .setTitle("Do you want to Quit")
                .setPositiveButton("Yes", (dialog, which) -> finish())
                .setNegativeButton("No", (dialog, which) -> {
                })
                .setCancelable(false)
                .show();
    }

}