package nl.hs_hague.pushnotifications.gcm;

import android.app.Activity;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

import nl.hs_hague.pushnotifications.MainActivity;
import nl.hs_hague.pushnotifications.R;

/**
 * Created by vural on 25.09.16.
 */

public class GcmBroadcastReceiver extends WakefulBroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("MainActivity", "Something has arrived");
        // Notification wird gebaut
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.common_ic_googleplayservices)
                        .setContentTitle("Notification Test")
                        .setContentText(intent.getStringExtra("message"));

        // Beim klicken auf die Notification wird die Mainactivity gestartet
        Intent resultIntent = new Intent(context, MainActivity.class);

        // Beim Zurück-Button kommt man direkt auf den Home Screen
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent =
                stackBuilder.getPendingIntent(
                        0,
                        PendingIntent.FLAG_UPDATE_CURRENT
                );
        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager mNotificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(999, mBuilder.build());

        setResultCode(Activity.RESULT_OK);
    }

}