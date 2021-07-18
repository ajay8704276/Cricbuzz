package com.app.cricbuzz.utils

import android.view.View
import com.google.android.material.snackbar.Snackbar


/**
 * Extension function for showing snack bar
 */
fun View.snackbar(message:String, length :Int = Snackbar.LENGTH_LONG){
    Snackbar.make(this,message,length).show()
}