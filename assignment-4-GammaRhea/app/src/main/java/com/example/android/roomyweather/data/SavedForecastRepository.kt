package com.example.android.roomyweather.data

class SavedForecastRepository(
    private val dao: ForecastDao
) {
    suspend fun insertSavedForecast(city: CityDatabaseEntry) = dao.insert(city)
    suspend fun deleteSavedForecast(city: CityDatabaseEntry) = dao.delete(city)
    suspend fun nukeSavedForecast() = dao.nukeTable()
    fun getAllCities() = dao.getAllCities()
}