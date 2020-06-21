package com.example.flowers

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.DatePicker
import android.widget.TextView
import android.widget.Toast
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


//    var cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.flower_add)

    }

//    textView!!.text = "--/--/----"

    fun addFlower(view: View)
    {
        val flower = Flower()

//        val view = this.new_item_last_watering;
//
//        val dateSetListener = object : DatePickerDialog.OnDateSetListener {
//            override fun onDateSet(view: DatePicker, year: Int, monthOfYear: Int,
//                                   dayOfMonth: Int) {
//                cal.set(Calendar.YEAR, year)
//                cal.set(Calendar.MONTH, monthOfYear)
//                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
//                updateDateInView()
//            }
//        }
//
//        this.new_item_last_watering_button!!.setOnClickListener(object : View.OnClickListener {
//            override fun onClick(view: View) {
//                DatePickerDialog(this@AddFlowerActivity,
//                    dateSetListener,
//                    // set DatePickerDialog to point to today's date when it loads up
//                    cal.get(Calendar.YEAR),
//                    cal.get(Calendar.MONTH),
//                    cal.get(Calendar.DAY_OF_MONTH)).show()
//            }
//        })
//        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
//            cal.set(Calendar.YEAR, year)
//            cal.set(Calendar.MONTH, monthOfYear)
//            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
//
//            val myFormat = "dd.MM.yyyy" // mention the format you need
//            val sdf = SimpleDateFormat(myFormat, Locale.US)
//            this.new_item_last_watering.text = sdf.format(cal.time)
//
//        }


//        val textView: TextView = this.new_item_last_watering
//        val buttonDate: Button = this.new_item_last_watering_button

//        val cal = Calendar.getInstance()
//        datePicker.init(
//            cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
//            cal.get(Calendar.DAY_OF_MONTH)
//        ) { view, year, month, day ->
//            val month = month + 1
//            val msg = "You Selected: $day/$month/$year"
//            val date = "$day$month$year"
//            Toast.makeText(this@AddFlowerActivity, msg, Toast.LENGTH_SHORT).show()
//            flower.lastWatering.time = date.toLong()
//        }

        val datePicker = findViewById<DatePicker>(R.id.date_picker)
        val h = hour.text.toString().toInt()
        val mins = minutes.text.toString().toInt()
        flower.lastWatering = Date(datePicker.year-1900, datePicker.month, datePicker.dayOfMonth, h, mins, 0)
        flower.name = new_item_name.text.toString()
//        flower.lastWatering.time = cal.toString().toLong()
        flower.frequency = new_item_frequency.text.toString().toLong()
        flower.isNotificationSent = false
        insertFlower(flower)
        val intent = Intent(this, ListFlowersActivity::class.java)
        startActivity(intent)

    }

//    private fun updateDateInView(view : TextView) {
//        val myFormat = "MM/dd/yyyy" // mention the format you need
//        val sdf = SimpleDateFormat(myFormat, Locale.US)
//        view!!.text = sdf.format(cal.getTime())
//    }

    fun insertFlower(flower: Flower) {
        Log.i("TEST", flower.toString())
        flowerDao.insert(flower)
    }
}