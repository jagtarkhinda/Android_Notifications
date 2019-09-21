package com.jagtar.androidnotificationbasics;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import java.util.Calendar;
import android.os.Build;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


import java.util.Date;
import java.util.Timer;

public class MainActivity extends AppCompatActivity {
    public static final String CHANNEL_ID = "notf1";
    Calendar setTime = Calendar.getInstance();
    private AlarmManager myAlarm;
    private PendingIntent pendingIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //need to add the code to register the notification channel for Android 8.0+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = getString(R.string.channel_name);
            String description = getString(R.string.channel_description);
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
        ////////////////////////////////
        // ALARM MANAGER (setting up alarm)
        myAlarm = (AlarmManager) getSystemService(ALARM_SERVICE);


        //intent to use to broadcast the alarm request
        Intent intent = new Intent(this, Notification_Receive.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        //setting time at which we need the notification

        //looping 2 times to set 2 alarms
        // we need to give unique id to pending intent for each alarm and pass it to alarm manager
        int idd= 1;
        for(int i= 0; i< 2; i++) {
            setTime.set(Calendar.HOUR_OF_DAY, 15 );
            setTime.set(Calendar.MINUTE, (26 + i));
            idd = i + 1;
            pendingIntent = PendingIntent.getBroadcast(this, idd, intent, PendingIntent.FLAG_UPDATE_CURRENT);
            myAlarm.setRepeating(AlarmManager.RTC_WAKEUP, setTime.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);

        }

        ///////////

    }


//    //use this to get notification on button press
//    public void notifyMe(View view){
//
//        // Create an explicit intent for an Activity in your app
//        //it is used when user tap on the notification
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
//
//        //Building a notification using notification builder
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(this, CHANNEL_ID)
//                .setSmallIcon(R.drawable.ic_launcher_background)
//                .setContentTitle("Thie is my first android notification")
//                .setContentText("Hello Android from top bar")
//                //set style is optional to modify the appearance
//                .setStyle(new NotificationCompat.BigTextStyle().bigText("More to add in notification"))
//                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
//                .setContentIntent(pendingIntent)
//                //remove the notification when user tap on it
//                .setAutoCancel(true);
//
//        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
//
//        // notificationId is a unique int for each notification that you must define
//        notificationManager.notify(12, builder.build());
//
//    }


}
