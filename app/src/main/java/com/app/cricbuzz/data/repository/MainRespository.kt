package com.app.cricbuzz.data.repository

import com.app.cricbuzz.data.api.ApiHelper

/**
 * Repository class to fetch the data source either
 *
 * from backend api service or from local database
 */
class MainRespository(private val apiHelper: ApiHelper) {

    suspend fun getRestaurents() = apiHelper.getRestaurent()

    suspend fun getMenus() = apiHelper.getMenus()


}