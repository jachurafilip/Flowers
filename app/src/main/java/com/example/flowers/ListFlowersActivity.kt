package com.example.flowers

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.flowers.DB.AppDatabase
import com.example.flowers.DB.Flower
import com.example.flowers.DB.FlowerDAO
import java.util.*

class ListFlowersActivity : AppCompatActivity(), FlowerListFragment.OnFlowerSelected {
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

    fun getAllFlowers(): List<Flower> {
        return flowerDao.getAllFlowers()
    }
    fun water(view: View)
    {
        val flower = flowerDao.getFlowerById(flowerId)
        flower.lastWatering.time = Calendar.getInstance().time.time
        flower.isNotificationSent = false
        Log.e("WATER", flower.toString())
        flowerDao.update(flower)
    }
    fun delete(view: View)
    {
        val flower = flowerDao.getFlowerById(flowerId)
        flowerDao.delete(flower)
    }

}