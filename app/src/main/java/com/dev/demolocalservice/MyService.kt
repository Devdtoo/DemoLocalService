package com.dev.demolocalservice

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.Build

import android.os.IBinder
import android.provider.Settings
import android.util.Log
import androidx.core.app.NotificationCompat

class MyService : Service() {
    var player : MediaPlayer? = null
    private val CHANNEL_ID: String = "007"


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }


    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        Thread(Runnable {
            player = MediaPlayer.create(this, Settings.System.DEFAULT_RINGTONE_URI)
            player?.isLooping = true
            player?.start()
        }).run()

        createNotificationChannel()
        val notificationBuilder = NotificationCompat.Builder(this,CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle("Playing Music")
            .setContentText("Music is on loop mode")
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        startForeground(1, notificationBuilder.build())
        return START_STICKY
    }

    fun createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name = getString(R.string.channel_name)
            val descriptionText = getString(R.string.channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}