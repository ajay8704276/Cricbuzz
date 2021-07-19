package com.app.cricbuzz.ui.main.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.cricbuzz.data.model.MenuResponse
import com.app.cricbuzz.data.model.Restaurants
import com.app.cricbuzz.data.model.RestaurentResponse
import com.app.cricbuzz.utils.ParseJson
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.*
import java.lang.reflect.Type
import javax.inject.Inject


@HiltViewModel
class RestaurentViewModel @Inject constructor(
    @ApplicationContext  context: Context
) : ViewModel() {

    var mContext :Context = context

    /**
     * Mutable list of updated restaurent data menu response
     */
    lateinit var mutableListRestaurants: MutableList<Restaurants>

    //Error to show the message
    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String>
        get() {
            return _errorMessage
        }

    //Mutable live data of restaurant date
    val _restaurentList = MutableLiveData<List<Restaurants>>()
    /*val restaurentList: LiveData<RestaurentResponse>
        get() {
            return _restaurentList
        }*/



    //Boolean to handle the login state
    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() {
            return _loading
        }

    //Job to handle the cancellation of the coroutines
    var job = Job()


    //Coroutines of exception handling
    val handler = CoroutineExceptionHandler { _, exception ->
        println("Exception ajay  $exception")
    }

    //define coroutine scope
    val coroutineScope = CoroutineScope(Dispatchers.IO + job)

    /**
     * function to fetch the restaurant data from json file from asset
     *
     * and
     *
     * to fetch the menus data from json file from asset
     *
     * merge the menus data into the restaurent data to perform filtering
     */
    fun getRestaurantsAndMenu() {

        try {
            coroutineScope.launch {


                /**
                 * Parsing restaurant json
                 */
                val response = ParseJson.getJsonFromAssets(
                    mContext,
                    "restaurent.json"
                )

                val gson1 = Gson()
                val restaurentResponse: Type = object : TypeToken<RestaurentResponse?>() {}.type

                val restaurents = gson1.fromJson<RestaurentResponse>(response, restaurentResponse)
                mutableListRestaurants = restaurents.restaurants as MutableList<Restaurants>

                /**
                 * Parsing menus json
                 */
                val response1 =
                    ParseJson.getJsonFromAssets(mContext, "menus.json")
                val gson = Gson()
                val menuResponse: Type = object : TypeToken<MenuResponse?>() {}.type

                val menus = gson.fromJson<MenuResponse>(response1, menuResponse)


                /**
                 * Merging both restaurant and menus data into restaurant
                 */
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
                /**
                 * updating restaurant with post value t
                 */
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