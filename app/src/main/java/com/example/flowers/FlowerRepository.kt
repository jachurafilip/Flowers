package com.example.flowers

import androidx.lifecycle.LiveData
import com.example.flowers.DB.Flower
import com.example.flowers.DB.FlowerDAO

class FlowerRepository(private val flowerDAO: FlowerDAO) {

    fun getAllFlowers(): List<Flower> {
        return flowerDAO.getAllFlowers()
    }

    fun getFlower(id: Long): Flower {
        return flowerDAO.getFlowerById(id)
    }
}