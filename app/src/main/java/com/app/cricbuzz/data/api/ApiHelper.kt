package com.app.cricbuzz.data.api

class ApiHelper(private val apiService: ApiService) {

    suspend fun getRestaurent() = apiService.getRestaurents()

    suspend fun getMenus() = apiService.getMenus()


}