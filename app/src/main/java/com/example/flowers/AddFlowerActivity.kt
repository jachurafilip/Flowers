package com.example.flowers

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.flowers.DB.AppDatabase
import com.example.flowers.DB.Flower
import com.example.flowers.DB.FlowerDAO
import kotlinx.android.synthetic.main.flower_add.*
import java.sql.Date

class AddFlowerActivity : AppCompatActivity() {
    private val db: AppDatabase = AppDatabase.getInstance()
    private val flowerDao: FlowerDAO = db.FlowerDAO()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.flower_add)

    }

    fun addFlower(view: View)
    {
        val flower = Flower()
        flower.name = new_item_name.text.toString()
        flower.lastWatering.time = new_item_last_watering.text.toString().toLong()
        flower.frequency = new_item_frequency.text.toString().toLong()
        flower.isNotificationSent = false
        insertFlower(flower)
        val intent = Intent(this, ListFlowersActivity::class.java)
        startActivity(intent)

    }

    fun insertFlower(flower: Flower) {
        Log.i("TEST", flower.toString())
        flowerDao.insert(flower)
    }
}