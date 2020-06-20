package com.example.flowers

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import com.example.flowers.DB.AppDatabase
import com.example.flowers.DB.Flower
import java.util.*

class MyReceiver : BroadcastReceiver() {
    val FLOWER_ID = "com.example.myfirstapp.FLOWERID"
    private val db = AppDatabase.getInstance()
    private val flowerDAO = db.FlowerDAO()
    @SuppressLint("UnsafeProtectedBroadcastReceiver")
    override fun onReceive(p0: Context, p1: Intent?) {

        Log.w("TEST", "onReceive called")
        val flowers = flowerDAO.getAllFlowers()


        for (flower in flowers)
        {
            if (flower.lastWatering.time + flower.frequency * 1000 * 60 < Calendar.getInstance().time.time) {
                if(!flower.isNotificationSent)
                {
                    sendNotification(p0, flower)
                }
            }
        }

    }

    fun sendNotification(p0: Context, flower: Flower)
    {
        val mNotificationManager = p0.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val resultIntent = Intent(p0, FlowerDetailsActivity::class.java)
        resultIntent.putExtra(FLOWER_ID, flower.flowerId)
        val resultPendingIntent: PendingIntent? = TaskStackBuilder.create(p0).run {
            // Add the intent, which inflates the back stack
            addNextIntentWithParentStack(resultIntent)
            // Get the PendingIntent containing the entire back stack
            getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT)
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            val channel = NotificationChannel("YOUR_CHANNEL_ID",
                "YOUR_CHANNEL_NAME",
                NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = "YOUR_NOTIFICATION_CHANNEL_DESCRIPTION"
            mNotificationManager.createNotificationChannel(channel)
        }
        val builder = NotificationCompat.Builder(p0, "YOUR_CHANNEL_ID")
            .setContentTitle(flower.name)
            .setContentText("Flower "+flower.name+" should be watered now")
            .setContentIntent(resultPendingIntent)
            .setSmallIcon(R.drawable.flower)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        mNotificationManager.notify(flower.hashCode(), builder.build())

        flower.isNotificationSent = true
        flowerDAO.update(flower)

    }
}