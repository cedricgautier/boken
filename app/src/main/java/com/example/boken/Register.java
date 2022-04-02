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

    @Override
    // This is the code that is executed when the activity is created.
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        password = passwordChecker();
        if (password == null){
            Log.d(TAG, "onCreate: Password False");
        }
        createAccount(login, password);
    }

    
    protected String passwordChecker() {
        if (!passwordConfirm.equals(password)) {
            Toast.makeText(this, "Your Password is invalid", Toast.LENGTH_SHORT).show();
        } else {
            password = passwordConfirm;
            return password;
        }
        return password;
    }


    /**
     * Create a new user with the email and password
     * 
     * @param login The email address of the user.
     * @param password The password that the user will enter to create their account.
     */
    private void createAccount(String login, String password) {
        mAuth.createUserWithEmailAndPassword(login, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
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


    private static final String TAG = "EmailPassword";
    private void updateUI(FirebaseUser user) {}
    private FirebaseAuth mAuth;

    EditText loginEmail = findViewById(R.id.login_email);
    EditText loginPassword = findViewById(R.id.login_password);
    EditText loginPasswordConfirm = findViewById(R.id.login_password_confirm);

    String login = loginEmail.getText().toString();
    String password = loginPassword.getText().toString();
    String passwordConfirm = loginPasswordConfirm.getText().toString();

}