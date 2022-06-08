package com.example.boken;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class Profile extends AppCompatActivity {
    private static final String TAG = "Profile";
    private FirebaseAuth mAuth;
    String uid = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button btn_edit_profile = findViewById(R.id.button_edit_profile);
        Button btn_logout = findViewById(R.id.button_logout);

        btn_logout.setOnClickListener(view -> {
            mAuth.signOut();
            finish();
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null)


    }

}