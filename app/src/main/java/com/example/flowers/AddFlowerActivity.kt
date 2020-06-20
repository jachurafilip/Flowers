package com.example.flowers

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.flowers.DB.AppDatabase
import com.example.flowers.DB.Flower
import com.example.flowers.DB.FlowerDAO
import kotlinx.android.synthetic.main.flower_add.*
import java.sql.Date
import java.text.SimpleDateFormat
import java.util.*

class AddFlowerActivity : AppCompatActivity() {
    private val db: AppDatabase = AppDatabase.getInstance()
    private val flowerDao: FlowerDAO = db.FlowerDAO()


    var cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.flower_add)

    }

    val textView: TextView = findViewById(R.id.new_item_last_watering)
    val buttonDate: Button = findViewById(R.id.new_item_last_watering_button)

    fun addFlower(view: View)
    {
        val flower = Flower()
        flower.name = new_item_name.text.toString()


        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
                                   dayOfMonth: Int) {
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
                updateDateInView()
            }
        }

        buttonDate!!.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View) {
                DatePickerDialog(this@AddFlowerActivity,
                    dateSetListener,
                    // set DatePickerDialog to point to today's date when it loads up
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
            }
        })
//        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
//            cal.set(Calendar.YEAR, year)
//            cal.set(Calendar.MONTH, monthOfYear)
//            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
//
//            val myFormat = "dd.MM.yyyy" // mention the format you need
//            val sdf = SimpleDateFormat(myFormat, Locale.US)
//            textView.text = sdf.format(cal.time)
//
//        }



        flower.lastWatering.time = cal.toString().toLong()
        flower.frequency = new_item_frequency.text.toString().toLong()
        flower.isNotificationSent = false
        insertFlower(flower)
        val intent = Intent(this, ListFlowersActivity::class.java)
        startActivity(intent)

    }

    private fun updateDateInView() {
        val myFormat = "MM/dd/yyyy" // mention the format you need
        val sdf = SimpleDateFormat(myFormat, Locale.US)
        textView!!.text = sdf.format(cal.getTime())
    }

    fun insertFlower(flower: Flower) {
        Log.i("TEST", flower.toString())
        flowerDao.insert(flower)
    }
}