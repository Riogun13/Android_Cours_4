package com.example.a533.android_cours_4;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class SignupOrLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_or_login);
        setListener();
        //Toast.makeText(this, "sup", Toast.LENGTH_SHORT).show();
    }

    private void setListener(){
        findViewById(R.id.button_go_to_signUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToSignUp();
            }
        });
        findViewById(R.id.button_go_to_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToLogin();
            }
        });


    }

    private void sendToSignUp(){
        Intent sendToSignUpOrLogin = new Intent(this, SignUpActivity.class);
        startActivity(sendToSignUpOrLogin);
    }

    private void sendToLogin(){
        Intent intentToSignUpOrLogin = new Intent(this, LoginActivity.class);
        startActivity(intentToSignUpOrLogin);
    }
}
