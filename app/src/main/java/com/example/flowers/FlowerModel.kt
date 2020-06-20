package com.example.flowers
import java.io.Serializable
import java.util.*


data class FlowerModel (
    var name: String,
    var lastWaterning: Date,
    var frequency: Long) : Serializable
