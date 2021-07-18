package com.app.cricbuzz.data.api

import android.content.Context
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException


object RetrofitBuilder {

    private const val  BASE_URL = "http://192.168.23.243:3001"
    //var context: Context? = null

    private fun getRetrofit(context: Context):Retrofit{
        return Retrofit.Builder()
            //.client(OkHttpClient.Builder().addInterceptor(MockClient(context)).build())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    }

    fun getRetrofitInstance(context: Context):ApiService{
        var apiService = getRetrofit(context).create(ApiService::class.java)
        return apiService
    }

    /*class MockClient(context: Context) : Interceptor {

        @Throws(IOException::class)
        override fun intercept(chain: Interceptor.Chain): Response {
            val url: HttpUrl = chain.request().url()
            when (url.encodedPath()) {
                "some/path" -> {
                    val response: String = readJsonFieleFromAssestOrAnyOtherStorage(context)
                    return Response.Builder()
                        .code(200)
                        .message(response)
                        .request(chain.request())
                        .protocol(Protocol.HTTP_1_1)
                        .body(
                            ResponseBody.create(
                                MediaType.parse("application/json"),
                                response.toByteArray()
                            )
                        )
                        .addHeader("content-type", "application/json")
                        .build()
                }
            }
        }

        private fun readJsonFieleFromAssestOrAnyOtherStorage(context: Context?): String {

        }
    }*/
}