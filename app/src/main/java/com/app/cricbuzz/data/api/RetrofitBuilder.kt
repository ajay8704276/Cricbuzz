package com.app.cricbuzz.data.api

import android.content.Context
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


/**
 * Singleton retrofit build object for initializing the retrofit object
 *
 * to access the restaurant api service
 */
object RetrofitBuilder {

    // Mock base URL using Moockoon app
    private const val  BASE_URL = "http://192.168.23.243:3001"
    //var context: Context? = null

    /**
     * Get the retrofit object
     */
    private fun getRetrofit(context: Context):Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    /**
     * Return the retrofit api service instance
     */
    fun getRetrofitInstance(context: Context):ApiService{
        var apiService = getRetrofit(context).create(ApiService::class.java)
        return apiService
    }

}