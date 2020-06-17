package com.example.flowers.DB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.sql.Time
import java.util.*

@Entity

data class Flower(
    @PrimaryKey(autoGenerate = true)
    val flowerId: Long = 0,
    @ColumnInfo(name = "name")
    val name: String = "",

    @ColumnInfo(name = "lastWatering")
    val lastWatering: Date = Date(),

    @ColumnInfo(name = "frequency")
    val frequency: Long = 0


) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Flower

        if (flowerId != other.flowerId) return false
        if (name != other.name) return false

        return true
    }

}