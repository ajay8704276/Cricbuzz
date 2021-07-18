package com.app.cricbuzz.data.repository

import com.app.cricbuzz.data.api.ApiHelper

class MainRespository(private val apiHelper: ApiHelper) {

    suspend fun getRestaurents() = apiHelper.getRestaurent()

    suspend fun getMenus() = apiHelper.getMenus()


}