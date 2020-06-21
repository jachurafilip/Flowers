package com.example.flowers

import android.content.BroadcastReceiver
import android.content.Intent
import android.content.IntentFilter
import android.content.res.Configuration
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.flowers.DB.AppDatabase


class MainActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase
    private lateinit var myReceiver: BroadcastReceiver


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = AppDatabase.getInstance(applicationContext)

        myReceiver = MyReceiver()
        val mTime = IntentFilter(Intent.ACTION_TIME_TICK)
        registerReceiver(myReceiver, mTime)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(myReceiver)
    }
    fun addFlower(view: View)
    {
        val intent = Intent(this, AddFlowerActivity::class.java)
        startActivity(intent)
    }

    fun listFlowers(view: View)
    {
        val intent = Intent(this, ListFlowersActivity::class.java)
        startActivity(intent)
    }
}