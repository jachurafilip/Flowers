package com.example.flowers.DB;


import kotlinx.android.parcel.Parcelize;
import androidx.room.ColumnInfo;
import android.os.Parcelable;


@Parcelize
data class FlowerListItem(
    @ColumnInfo(name = "name") val name: String = "",
    @ColumnInfo(name = "last_watering") val lastWatering: String = "",
    @ColumnInfo(name = "frequency") val frequency: String = ""
        ) : Parcelable