package com.example.boken;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Homepage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);
        Button btn_homepage = findViewById(R.id.homepage_boken_button) ;
        Button btn_search = findViewById(R.id.homepage_search_button);
        Button btn_messages = findViewById(R.id.homepage_messages_button);
        Button btn_profile = findViewById(R.id.homepage_profile_button);

        btn_homepage.setOnClickListener(view -> {
            finish();
            startActivity(getIntent());
        });

        btn_messages.setOnClickListener(view -> {
            Intent messages_page = new Intent(Homepage.this, Messages.class);
            startActivity(messages_page);
        });

        btn_search.setOnClickListener(view -> {
            Intent search_page = new Intent(Homepage.this, Search.class);
            startActivity(search_page);
        });

        btn_profile.setOnClickListener(view -> {
            Intent profile_page = new Intent(Homepage.this, Profile.class);
            startActivity(profile_page);
        });

    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}