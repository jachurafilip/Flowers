package com.example.flowers

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.flowers.DB.AppDatabase
import com.example.flowers.DB.FlowerDAO
import java.util.*

class ListFlowersActivity : AppCompatActivity(), FlowerListFragment.OnFlowerSelected {
    val FLOWER_ID = "com.example.myfirstapp.FLOWERID"
    private val db: AppDatabase = AppDatabase.getInstance()
    private val flowerDao: FlowerDAO = db.FlowerDAO()
    private var flowerId = -1L


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_flowers)


        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.root_layout, FlowerListFragment.newInstance(), "flowerList")
                .commit()
        }
    }

    override fun onFlowerSelected(flowerModel: FlowerModel) {

        flowerId = flowerModel.id
        val detailsFragment =
            FlowerDetailsFragment.newInstance(flowerModel)
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
        Log.e("WATER", flower.toString())
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

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val resultIntent = Intent(this, ListFlowersActivity::class.java)
        startActivity(resultIntent)
    }

}