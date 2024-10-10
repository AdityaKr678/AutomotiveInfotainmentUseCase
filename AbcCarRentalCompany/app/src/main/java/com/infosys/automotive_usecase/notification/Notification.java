//Copyright (C) 2024 Aditya Kumar

package com.infosys.automotive_usecase.notification;

//Need to include required import statement

import android.app.NotificationManager;
import android.content.Context;
import androidx.core.app.NotificationCompat;

public class Notification {

    private static final int NOTIFICATION_ID = 1;

    public static void showSpeedLimitExceededNotification(Context context, String message) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "speed_alert")
                .setSmallIcon(android.R.drawable.ic_dialog_alert)
                .setContentTitle("Speed Alert")
                .setContentText(message)
                .setAutoCancel(true);

        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(NOTIFICATION_ID, builder.build());
    }
}
