package com.app.cricbuzz.data.api

import javax.inject.Inject

/**
 * Helper class to fetch the restaurant service like fetching
 *
 * restaurant details and menu details
 *
 */
class ApiHelper @Inject constructor(private val apiService: ApiService) {

    suspend fun getRestaurent() = apiService.getRestaurents()

    suspend fun getMenus() = apiService.getMenus()


}