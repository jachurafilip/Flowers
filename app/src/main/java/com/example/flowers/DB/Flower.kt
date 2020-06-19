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
    var name: String = "",

    @ColumnInfo(name = "lastWatering")
    var lastWatering: Date = Date(),

    @ColumnInfo(name = "frequency")
    var frequency: Long = 0, //in minutes

    @ColumnInfo(name = "isNotifcationSent")
    var isNotificationSent: Boolean = false


) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Flower

        if (flowerId != other.flowerId) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = flowerId.hashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + lastWatering.hashCode()
        result = 31 * result + frequency.hashCode()
        return result
    }

}