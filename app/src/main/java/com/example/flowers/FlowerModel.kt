package com.example.flowers
import java.io.Serializable


data class FlowerModel (val imageResId: Int,
                        val name: String,
                        val description: String,
                        val url: String,
                        var text: String = "") : Serializable