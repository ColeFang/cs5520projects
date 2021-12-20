package com.example.todoapp;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;


public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent broadcastIntent = new Intent(context, MainActivity2.class);
        int index=intent.getIntExtra("IND",0);
        broadcastIntent.putExtra("IND", index);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context, index , broadcastIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationHelper notificationHelper = new NotificationHelper(context,intent.getStringExtra("TITLE"),intent.getIntExtra("IND",0));
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification();
        nb.setContentIntent(pendingIntent);
        notificationHelper.getManager().notify(1, nb.build());
    }
}
