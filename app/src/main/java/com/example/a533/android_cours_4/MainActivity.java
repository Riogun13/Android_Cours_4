package com.example.a533.android_cours_4;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a533.android_cours_4.notification.NotificationService;
import com.example.a533.android_cours_4.notification.model.ImportantMessageModel;
import com.example.a533.android_cours_4.notification.model.MessageModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class MainActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseFirestore database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        database = FirebaseFirestore.getInstance();
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

    private void startService(){
        Intent serviceIntent = new Intent(this, NotificationService.class);
        ContextCompat.startForegroundService(this, serviceIntent);
    }

    private void setListener() {
        findViewById(R.id.button_log_out).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logout();
            }
        });
        findViewById(R.id.buttonSendMessage).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
        findViewById(R.id.buttonExercice7).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessageImportante();
            }
        });
    }

    private void sendMessage(){
        EditText editTextMessage = findViewById(R.id.editTextMainActivity);
        MessageModel messageModel = new MessageModel(editTextMessage.getText().toString(),
                auth.getCurrentUser().getEmail());
        database.collection("Notification").add(messageModel).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
               if(task.isSuccessful()){
                   Toast.makeText(getApplicationContext(),"Message sent", Toast.LENGTH_LONG).show();
               }
            }
        });
    }

    private void sendMessageImportante(){
        EditText editTextMessage = findViewById(R.id.editTextMainActivity);
        ImportantMessageModel messageModel = new ImportantMessageModel(editTextMessage.getText().toString(),auth.getCurrentUser().getEmail());
        database.collection("MessageImportant").add(messageModel).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Message important sent", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void updateUI(FirebaseUser currentUser){
        if (currentUser == null){
            sendUserToSignUpOrLoginActivity();
            //Toast.makeText(this, "sup", Toast.LENGTH_SHORT).show();
        }else{
            startService();
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
