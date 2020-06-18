package com.example.flowers

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.flowers.DB.AppDatabase
import com.example.flowers.DB.Flower
import java.util.*

class MyReceiver : BroadcastReceiver() {
    private val db = AppDatabase.getInstance()
    private val flowerDAO = db.FlowerDAO()
    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(p0: Context, p1: Intent?) {

        val flowers = flowerDAO.getAllFlowers()

        for (flower in flowers)
        {
            if (flower.lastWatering.time + flower.frequency * 1000 * 60 < Calendar.getInstance().time.time) {
                sendNotification(p0, flower)
            }
        }

    }

    fun sendNotification(p0: Context, flower: Flower)
    {
        val mNotificationManager = p0.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel("YOUR_CHANNEL_ID",
                "YOUR_CHANNEL_NAME",
                NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = "YOUR_NOTIFICATION_CHANNEL_DESCRIPTION"
            mNotificationManager.createNotificationChannel(channel)
        }
        val builder = NotificationCompat.Builder(p0, "YOUR_CHANNEL_ID")
            .setContentTitle(flower.name)
            .setContentText("Flower"+flower.name+"should be watered now")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        mNotificationManager.notify(0, builder.build())
    }
}