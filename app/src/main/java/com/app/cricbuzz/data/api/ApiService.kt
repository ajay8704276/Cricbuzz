package com.app.cricbuzz.data.api

import com.app.cricbuzz.data.model.MenuResponse
import com.app.cricbuzz.data.model.RestaurentResponse
import retrofit2.Response
import retrofit2.http.GET

/**
 * Api service class getting the response using
 *
 * retrofit service
 */
interface ApiService {

    /**
     * Get the restaurant
     */
    @GET("/restaurents")
    suspend fun getRestaurents():Response<RestaurentResponse>


    /**
     * Get the menus of their restaurant
     */
    @GET("menus")
    suspend fun getMenus():Response<MenuResponse>
}