package com.app.cricbuzz.ui.main.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.app.cricbuzz.data.api.ApiHelper
import com.app.cricbuzz.data.repository.MainRespository
import java.lang.IllegalArgumentException

class RestaurentViewModelFactory constructor(private val apiHelper: ApiHelper,private val context: Context) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RestaurentViewModel::class.java)){
            return RestaurentViewModel(MainRespository(apiHelper),context) as T
        }else{
            throw IllegalArgumentException("View Model not found")
        }
    }
}