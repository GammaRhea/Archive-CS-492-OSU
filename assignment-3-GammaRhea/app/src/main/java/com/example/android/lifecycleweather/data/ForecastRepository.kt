package com.example.android.lifecycleweather.data

import com.example.android.lifecycleweather.api.ForecastService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class ForecastRepository(
    private val service: ForecastService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun loadSearchForecast(query: String?, units: String?, apiKey: String): Result<FiveDayForecast> = withContext(ioDispatcher) {
        try {
            val results = service.searchForecast(query, units, apiKey)
            Result.success(results)
        } catch(e: Exception) {
            Result.failure(e)
        }
    }

}