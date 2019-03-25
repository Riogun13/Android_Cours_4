package com.example.a533.android_cours_4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth = FirebaseAuth.getInstance();
        setListener();

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = auth.getCurrentUser();
        updateUI(currentUser);
    }

    private void setListener() {
        findViewById(R.id.button_log_out).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
    }

    private void updateUI(FirebaseUser currentUser){
        if (currentUser == null){
            sendUserToSignUpOrLoginActivity();
            //Toast.makeText(this, "sup", Toast.LENGTH_SHORT).show();
        }
    }

    private void sendUserToSignUpOrLoginActivity(){
        Intent intentToSignUpOrLogin = new Intent(this, SignupOrLoginActivity.class);
        startActivity(intentToSignUpOrLogin);
    }

    private void logout(){
        auth.signOut();
        updateUI(auth.getCurrentUser());
    }
}
