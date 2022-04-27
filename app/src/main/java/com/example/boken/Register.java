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

public class Register extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private static final String TAG = "Register";
    private void updateUI(FirebaseUser user) {}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EditText mail = findViewById(R.id.register_email);
        Button btn_next = findViewById(R.id.b_register_next);

        mAuth = FirebaseAuth.getInstance();
        btn_next.setOnClickListener(view -> {
            String correctPassword = checkPassword();
            String email = mail.getText().toString();
            createAccount(email,correctPassword);
            Intent login_page = new Intent(Register.this, Login.class);
            startActivity(login_page);
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }



    private String checkPassword(){
        EditText pass = findViewById(R.id.register_password);
        EditText passConfirm = findViewById(R.id.register_password_confirm);
        String password = pass.getText().toString();
        String passwordConfirm = passConfirm.getText().toString();
        
        if (passwordConfirm.equals(password)){
            Log.d(TAG, "checkPassword: true");
            password = passwordConfirm;
            return password;
        }
        else{
            Log.d(TAG, "checkPassword: false");
            Toast.makeText(Register.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
        }
        return "";
    }
    private void createAccount(String email, String password) {
        // [START create_user_with_email]
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                            Toast.makeText(Register.this, "Auth True", Toast.LENGTH_SHORT).show();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Register.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
        // [END create_user_with_email]
    }






}