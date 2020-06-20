package com.example.flowers
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.flowers.DB.AppDatabase
import java.io.Serializable


data class FlowerModel (val name: String,
                        val last_watering: Long,
                        val frequency: Long) : Serializable

