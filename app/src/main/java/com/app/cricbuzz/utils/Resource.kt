package com.app.cricbuzz.utils

import androidx.lifecycle.LiveData

data class Resource<out T>(val status :Status,val data :T , val message:String?){

    companion object{

        //return the success status and data to the caller to inform
        fun<T> success(data :T?): Resource<T?> {
            return Resource(Status.SUCCESS,data,null)
        }

        //return the error status and data to the caller to inform
        fun <T> error(message: String?,data:T):Resource<T?>{
            return Resource(Status.ERROR,data,message)
        }

        //return the loading data and show the progress bar
        fun <T> loading(data: T?): Resource<T?> {
            return Resource(Status.LOADING,data , null)
        }

    }
}