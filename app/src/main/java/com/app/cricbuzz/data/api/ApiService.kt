package com.app.cricbuzz.data.api

import com.app.cricbuzz.data.model.MenuResponse
import com.app.cricbuzz.data.model.RestaurentResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("/restaurents")
    suspend fun getRestaurents():Response<RestaurentResponse>


    @GET("menus")
    suspend fun getMenus():Response<MenuResponse>
}