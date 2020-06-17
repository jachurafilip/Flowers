package com.example.flowers

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.flowers.DB.AppDatabase
import com.example.flowers.DB.Flower
import com.example.flowers.DB.FlowerDAO
import java.sql.Date

class AddFlowerActivity : AppCompatActivity() {
    private val db: AppDatabase = AppDatabase.getInstance()
    private val flowerDao: FlowerDAO = db.FlowerDAO()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.flower_add)

        val flower = Flower( name = "abc", lastWatering = Date(1), frequency =  120)


       insertFlower(flower)


    }

    fun insertFlower(flower: Flower)
    {
        flowerDao.insert(flower)
    }
}