package com.example.boken;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Login extends AppCompatActivity {
    private static final String TAG = "Login";
    private FirebaseAuth mAuth;
    private void updateUI(FirebaseUser user) {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        EditText pass = findViewById(R.id.login_password);
        EditText mail = findViewById(R.id.login_email);
        Button btn_next = findViewById(R.id.b_login_next);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        btn_next.setOnClickListener(view -> {
            String password = pass.getText().toString();
            String email = mail.getText().toString();
            signIn(email,password);
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null)
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    private void signIn(String email, String password) {
        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Toast.makeText(Login.this, "Authentication Successful",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(user);
                            Intent account_creation = new Intent(Login.this, AccountCreation.class);
                            startActivity(account_creation);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
        // [END sign_in_with_email]
    }

}