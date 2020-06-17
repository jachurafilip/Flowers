package com.example.flowers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View;
import com.example.flowers.DB.AppDatabase

class MainActivity : AppCompatActivity() {

    private lateinit var db: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        db = AppDatabase.getInstance(applicationContext)




    }
    fun addFlower(view: View)
    {
        val intent = Intent(this, AddFlowerActivity::class.java)
        startActivity(intent);
    }

    fun listFlowers(view: View)
    {
        val intent = Intent(this, ListFlowersActivity::class.java)
        startActivity(intent)
    }
}