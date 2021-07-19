package com.app.cricbuzz.di

import android.content.Context
import com.app.cricbuzz.data.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


/**
 * Singleton retrofit build object for initializing the retrofit object
 *
 * to access the restaurant api service
 */

@Module
@InstallIn(SingletonComponent::class)
object RetrofitBuilder {

    // Mock base URL using Moockoon app
    private const val  BASE_URL = "http://192.168.23.243:3001"
    //var context: Context? = null

    /**
     * Get the retrofit object
     */
    @Singleton
    @Provides
    fun getRetrofit():Retrofit{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    /**
     * Return the retrofit api service instance
     */
    @Singleton
    @Provides
    fun getRetrofitInstance(): ApiService {
        var apiService = getRetrofit().create(ApiService::class.java)
        return apiService
    }

}