package com.app.cricbuzz.utils

import android.content.Context
import java.io.IOException
import java.io.InputStream


/**
 * Object responsible for parsing of data
 */
object ParseJson {

    fun getJsonFromAssets(context: Context, fileName: String?): String? {
        val jsonString: String
        jsonString = try {
            val inputStream: InputStream? = fileName?.let { context.getAssets().open(it) }
            val size: Int = inputStream?.available()!!
            val buffer = ByteArray(size)
            inputStream?.read(buffer)
            inputStream?.close()
            String(buffer, charset("UTF-8"))
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return jsonString
    }
}