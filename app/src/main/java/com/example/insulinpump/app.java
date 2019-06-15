package com.example.insulinpump;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import java.nio.channels.Channel;

public class app extends Application {
public static final String CHANNEL_1_ID = "channel1";
public static final String CHANNEL_2_ID = "channel2";
    @Override
    public void onCreate() {
        super.onCreate();
   createNotificationChannels();
    }
    private void createNotificationChannels()
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Channel",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.enableVibration(true);        }

    }
}
