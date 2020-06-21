package com.example.flowers

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.flowers.DB.AppDatabase
import com.example.flowers.DB.FlowerDAO
import java.util.*
import kotlin.properties.Delegates

class FlowerDetailsActivity : AppCompatActivity(),FlowerListFragment.OnFlowerSelected {
    val FLOWER_ID = "com.example.myfirstapp.FLOWERID"
    private var flowerId: Long = -1
    private val db: AppDatabase = AppDatabase.getInstance()
    private val flowerDao: FlowerDAO = db.FlowerDAO()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flower_details)
        flowerId = intent.getLongExtra("com.example.myfirstapp.FLOWERID", -1)
        Log.e("Details", flowerId.toString())

        val flower = flowerDao.getFlowerById(flowerId)

        val model = FlowerModel(flower.flowerId,flower.name, flower.lastWatering, flower.frequency)

        val detailsFragment = FlowerDetailsFragment.newInstance(model)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.root_layout, detailsFragment, "flowerDetails")
            .addToBackStack(null)
            .commit()
    }

    fun water(view: View)
    {
        val flower = flowerDao.getFlowerById(flowerId)
        flower.lastWatering.time = Calendar.getInstance().time.time
        flower.isNotificationSent = false
        Log.i("WATER", flower.toString())
        flowerDao.update(flower)
        val resultIntent = Intent(this, FlowerDetailsActivity::class.java)
        resultIntent.putExtra(FLOWER_ID, flower.flowerId)
        startActivity(resultIntent)

    }

    fun delete(view: View)
    {
        val flower = flowerDao.getFlowerById(flowerId)
        flowerDao.delete(flower)
        val resultIntent = Intent(this, ListFlowersActivity::class.java)
        startActivity(resultIntent)

    }

    override fun onFlowerSelected(flowerModel: FlowerModel) {
        val flower = flowerDao.getFlowerById(flowerId)

        val model = FlowerModel(flower.flowerId, flower.name, flower.lastWatering, flower.frequency)

        val detailsFragment = FlowerDetailsFragment.newInstance(model)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.root_layout, detailsFragment, "flowerDetails")
            .addToBackStack(null)
            .commit()    }


}