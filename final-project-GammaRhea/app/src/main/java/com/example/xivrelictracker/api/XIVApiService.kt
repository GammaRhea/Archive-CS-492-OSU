package com.example.xivrelictracker.api

import android.util.Log
import com.example.xivrelictracker.data.QuestObject
import com.example.xivrelictracker.data.XIVApiJsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url
import java.util.concurrent.TimeUnit

interface XIVApiService {
    @GET("quest/{qID}")
    suspend fun loadQuestList(
        @Path("qID") questID: String?,
        @Query("language") language: String?,
        @Query("columns") narrow: String?,
        @Query("private_key") apiKey: String
    ) : QuestObject

    companion object {
        private const val BASE_URL = "https://xivapi.com/"

        fun create() : XIVApiService {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .connectTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()
            val moshi = Moshi.Builder()
                .add(XIVApiJsonAdapter())
                .addLast(KotlinJsonAdapterFactory())
                .build()
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
                .create(XIVApiService::class.java)
        }
    }
}