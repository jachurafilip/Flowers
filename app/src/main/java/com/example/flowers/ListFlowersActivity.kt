package com.example.flowers

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.flowers.DB.AppDatabase
import com.example.flowers.DB.Flower
import com.example.flowers.DB.FlowerDAO

class ListFlowersActivity : AppCompatActivity(), FlowerListFragment.OnFlowerSelected {
    private val db: AppDatabase = AppDatabase.getInstance()
    private val flowerDao: FlowerDAO = db.FlowerDAO()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.root_layout, FlowerListFragment.newInstance(), "flowerList")
                .commit()
        }
    }

    override fun onFlowerSelected(flowerModel: FlowerModel) {
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
}