package com.example.boken;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {
    private static final String TAG = "EmailPassword";
    private void updateUI(FirebaseUser user) {
    }
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    EditText loginEmail = findViewById(R.id.login_email);
    EditText loginPassword = findViewById(R.id.login_password);
    EditText loginPasswordConfirm = findViewById(R.id.login_password_confirm);
    String login = loginEmail.getText().toString();
    String password = loginPassword.getText().toString();
    String passwordConfirm = loginPasswordConfirm.getText().toString();


    protected void passwordChecker(){
        if (!passwordConfirm.equals(password)){
            Toast.makeText(this, "Your Password is invalid", Toast.LENGTH_SHORT).show();
        } else {
          password = passwordConfirm;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        passwordChecker();
        mAuth.createUserWithEmailAndPassword(login, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            Log.d(TAG, "createUserWithEmail:failure");
                            Toast.makeText(Register.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });


    }


}