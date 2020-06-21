package com.example.flowers.DB

import androidx.room.*

@Dao
interface FlowerDAO {
    @Query("SELECT* FROM flower")
    fun getAllFlowers(): List<Flower>

    @Query("SELECT * FROM flower where name= :name")
    fun getFlowerByName(name: String): Flower

    @Query("SELECT * FROM flower where flowerId= :id")
    fun getFlowerById(id: Long): Flower

    @Insert
    fun insert(flower: Flower)

    @Insert
    fun insertAll(flowers: List<Flower>)

    @Delete
    fun delete(flower: Flower)

    @Update
    fun update(flower: Flower)
}