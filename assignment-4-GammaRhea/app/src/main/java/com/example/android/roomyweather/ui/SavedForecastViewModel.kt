package com.example.android.roomyweather.ui

import android.app.Application
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import com.example.android.roomyweather.data.AppDatabase
import com.example.android.roomyweather.data.SavedForecastRepository
import com.example.android.roomyweather.data.CityDatabaseEntry

class SavedForecastViewModel(application: Application) : AndroidViewModel(application) {
    private val repo = SavedForecastRepository(AppDatabase.getInstance(application).forecastDao())

    val savedCities = repo.getAllCities().asLiveData()

    fun addSavedForecast(city: CityDatabaseEntry) {
        viewModelScope.launch {
            repo.insertSavedForecast(city)
        }
    }

    fun removeSavedForecast(city: CityDatabaseEntry) {
        viewModelScope.launch {
            repo.deleteSavedForecast(city)
        }
    }

    fun nukeSavedForecast() {
        viewModelScope.launch {
            repo.nukeSavedForecast()
        }
    }
}
