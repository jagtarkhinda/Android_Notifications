package com.jagtar.androidnotificationbasics;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import static com.jagtar.androidnotificationbasics.MainActivity.CHANNEL_ID;
/* put
* <uses-permission android:name="com.andorid.alarm.permission.SET_ALARM" />
* and
*  <receiver android:name=".Notification_Receive" />
*  in manifest file
* */
//extends broadcast receiver to receive broadcasts
public class Notification_Receive extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
       // making this intent so when user click on notification it can go the MainActivity
        Intent intente = new Intent(context, MainActivity.class);
        intente.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intente, PendingIntent.FLAG_UPDATE_CURRENT);



        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_background)
                .setContentTitle("Thie is my first android notification")
                .setContentText("Hello Android from top bar")
                //set style is optional to modify the appearance
                .setStyle(new NotificationCompat.BigTextStyle().bigText("More to add in notification"))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                //remove the notification when user tap on it
                .setAutoCancel(true);

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(context);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(12, builder.build());
    }
}
