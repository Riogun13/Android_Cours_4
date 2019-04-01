package com.example.a533.android_cours_4.notification;


import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import com.example.a533.android_cours_4.MainActivity;
import com.example.a533.android_cours_4.R;
import com.example.a533.android_cours_4.notification.model.MessageModel;

public class NotificationCreator {


    public static Notification createNotificationForMessage(Context context, MessageModel message){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "42")
                .setSmallIcon(R.drawable.stfu)
                .setContentTitle(message.getSender())
                .setContentText(message.getMessage())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);
        return builder.build();
    }

    public static Notification createNotificationForMessageImportante(Context context, MessageModel message){
        Intent TestIntent = new Intent(context, MainActivity.class);
        PendingIntent PendingIntentTest = PendingIntent.getBroadcast(context, 0, TestIntent, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "23")
                .setSmallIcon(R.drawable.stfu)
                .setContentTitle(message.getSender())
                .setContentText(message.getMessage())
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .addAction(R.drawable.stfu, "k fine criss", PendingIntentTest.getActivity(context,0,TestIntent,0));
        return builder.build();
    }


}
