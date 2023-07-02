package com.example.android.lifecycleweather.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.lifecycleweather.api.ForecastService
import com.example.android.lifecycleweather.data.FiveDayForecast
import com.example.android.lifecycleweather.data.ForecastRepository
import com.example.android.lifecycleweather.data.LoadingStatus
import kotlinx.coroutines.launch

class ForecastViewModel : ViewModel() {
    private val repository = ForecastRepository(ForecastService.create())
    private val _forecastResults = MutableLiveData<FiveDayForecast?>(null)
    val forecastResults: LiveData<FiveDayForecast?> = _forecastResults

    private val _loadingStatus = MutableLiveData(LoadingStatus.SUCCESS)
    val loadingStatus: LiveData<LoadingStatus> = _loadingStatus

    fun loadForecastResults(city: String?, units: String?, apiKey: String) {
        viewModelScope.launch {
            _loadingStatus.value = LoadingStatus.LOADING
            val result = repository.loadSearchForecast(city, units, apiKey)
            _forecastResults.value = result.getOrNull()
            _loadingStatus.value = when (result.isSuccess) {
                true -> LoadingStatus.SUCCESS
                false -> LoadingStatus.ERROR
            }
        }
    }
}