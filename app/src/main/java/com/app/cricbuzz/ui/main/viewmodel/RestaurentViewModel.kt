package com.app.cricbuzz.ui.main.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.cricbuzz.data.model.MenuResponse
import com.app.cricbuzz.data.model.Restaurants
import com.app.cricbuzz.data.model.RestaurentResponse
import com.app.cricbuzz.data.repository.MainRespository
import com.app.cricbuzz.utils.ParseJson
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.*
import java.lang.reflect.Type


class RestaurentViewModel constructor(
    private val mainRespository: MainRespository,
    private val context: Context
) : ViewModel() {


    lateinit var mutableListRestaurants: MutableList<Restaurants>

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() {
            return _errorMessage
        }

    val _restaurentList = MutableLiveData<List<Restaurants>>()
    /*val restaurentList: LiveData<RestaurentResponse>
        get() {
            return _restaurentList
        }*/



    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() {
            return _loading
        }

    var job = Job()

    val handler = CoroutineExceptionHandler { _, exception ->
        println("Exception ajay  $exception")
    }

    //define coroutine scope
    val coroutineScope = CoroutineScope(Dispatchers.IO + job)

    fun getRestaurantsAndMenu() {

        try {
            coroutineScope.launch {

                val response = ParseJson.getJsonFromAssets(
                    context,
                    "restaurent.json"
                )

                val gson1 = Gson()
                val restaurentResponse: Type = object : TypeToken<RestaurentResponse?>() {}.type

                val restaurents = gson1.fromJson<RestaurentResponse>(response, restaurentResponse)
                mutableListRestaurants = restaurents.restaurants as MutableList<Restaurants>

                val response1 =
                    ParseJson.getJsonFromAssets(context, "menus.json")
                val gson = Gson()
                val menuResponse: Type = object : TypeToken<MenuResponse?>() {}.type

                val menus = gson.fromJson<MenuResponse>(response1, menuResponse)

                if (restaurents != null) {
                    //parseMenu().join()
                    var menuList = menus.menus
                    if (menuList != null) {
                        for (i in 0..menuList.size-1) {
                            for (j in 0..mutableListRestaurants.size-1) {
                                if (menuList[i].restaurantId == mutableListRestaurants[j].id) {
                                    mutableListRestaurants.get(j)?.menus = menuList[i]
                                    //println(mutableListRestaurants)
                                    break
                                }
                            }
                        }
                    }
                }
                withContext(Dispatchers.Main) {
                    _restaurentList.postValue(mutableListRestaurants)
                }
            }
        }catch (e:Exception){
            println(e.printStackTrace())
        }

    }



    private fun onError(message: String) {
        _errorMessage.value = message
        _loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }
}