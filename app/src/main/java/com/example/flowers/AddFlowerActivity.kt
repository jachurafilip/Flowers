package com.example.flowers

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import androidx.appcompat.app.AppCompatActivity
import com.example.flowers.DB.AppDatabase
import com.example.flowers.DB.Flower
import com.example.flowers.DB.FlowerDAO
import kotlinx.android.synthetic.main.flower_add.*
import java.util.*

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
        try {
            val datePicker = findViewById<DatePicker>(R.id.date_picker)
            val h = hour.text.toString().toInt()
            val mins = minutes.text.toString().toInt()
            flower.lastWatering = Date(datePicker.year-1900, datePicker.month, datePicker.dayOfMonth, h, mins, 0)
            flower.name = new_item_name.text.toString()
            flower.frequency = new_item_frequency.text.toString().toLong()
            flower.isNotificationSent = false
            insertFlower(flower)
            val intent = Intent(this, ListFlowersActivity::class.java)
            startActivity(intent)
        }
        catch ( e: NumberFormatException)
        {
            val intent = Intent(this, AddFlowerActivity::class.java)
            startActivity(intent)
        }
    }


    fun insertFlower(flower: Flower) {
        flowerDao.insert(flower)
    }
}