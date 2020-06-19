package com.example.flowers

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.flowers.DB.AppDatabase
import com.example.flowers.DB.FlowerDAO

class FlowerDetailsActivity : AppCompatActivity() {
    private val db: AppDatabase = AppDatabase.getInstance()
    private val flowerDao: FlowerDAO = db.FlowerDAO()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_flower_details)
    }

    fun water(view: View)
    {
        //flower.lastWatering.time = Calendar.getInstance().time.time
        //flowerDao.update(flower)
    }



}