package com.example.flowers

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.flowers.DB.AppDatabase
import com.example.flowers.DB.FlowerDAO
import java.util.*
import kotlin.properties.Delegates

class FlowerDetailsActivity : AppCompatActivity() {
    private var flowerId: Long = -1
    private val db: AppDatabase = AppDatabase.getInstance()
    private val flowerDao: FlowerDAO = db.FlowerDAO()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flower_details)
        flowerId = intent.getLongExtra("com.example.myfirstapp.FLOWERID", -1)

    }

    fun water(view: View)
    {
        val flower = flowerDao.getFlowerById(flowerId)
        flower.lastWatering.time = Calendar.getInstance().time.time
        flowerDao.update(flower)
    }

    fun delete(view: View)
    {
        val flower = flowerDao.getFlowerById(flowerId)
        flowerDao.delete(flower)
    }



}