package com.example.android.roomyweather.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.OnConflictStrategy
import kotlinx.coroutines.flow.Flow

@Dao
interface ForecastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cityEntry: CityDatabaseEntry)

    @Delete
    suspend fun delete(cityEntry: CityDatabaseEntry)

    @Query("DELETE FROM CityDatabaseEntry")
    suspend fun nukeTable()

    @Query("SELECT * FROM CityDatabaseEntry ORDER BY timeStamp DESC")
    fun getAllCities() : Flow<List<CityDatabaseEntry>>
}