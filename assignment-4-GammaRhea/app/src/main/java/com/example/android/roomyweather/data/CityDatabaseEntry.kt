package com.example.android.roomyweather.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class CityDatabaseEntry(
    @PrimaryKey
    val savedCity: String,

    //Use System.currentTimeMillis() to pull this in, might need to convert to Integer
    val timeStamp: Int
) : Serializable