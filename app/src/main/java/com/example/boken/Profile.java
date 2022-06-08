package com.example.boken;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

        btn_edit_profile.setOnClickListener(view -> {
            EditText firstname;
            EditText lastname;
            EditText email;
        });

        btn_logout.setOnClickListener(view -> {
            mAuth.signOut();
            Intent main_activity = new Intent(Profile.this,MainActivity.class);
            startActivity(main_activity);
        });
    }


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null)
        FirebaseUser currentFirebaseUser = mAuth.getCurrentUser();
        if (currentFirebaseUser == null) {
            Log.e(TAG, "onStart: Current Firebase User is empty");
        }

        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase database = FirebaseDatabase.getInstance("https://boken-c2118-default-rtdb.europe-west1.firebasedatabase.app");

        if (currentFirebaseUser != null) {
            uid = currentFirebaseUser.getUid();
        }

        DatabaseReference userReference = database.getReference("/Users/"+uid);
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                User user = dataSnapshot.getValue(User.class);
                // ..
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        userReference.addValueEventListener(postListener);

        TextView firstname = findViewById(R.id.profile_firstname);
        TextView lastname = findViewById(R.id.profile_lastname);
        TextView email = findViewById(R.id.profile_email);
        TextView phonenumber = findViewById(R.id.profile_phone_number);

        firstname.setText(user);

    }

}