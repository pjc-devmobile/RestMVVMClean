package com.examplerest_mvvm_clean.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://api.imgur.com/"
private const val API_VERSION = "3/"

class Service {

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL + API_VERSION)
        .client(OkHttpClient.Builder().addInterceptor(logInterceptor()).addInterceptor(AuthorizationInterceptor()).build())
        .addConverterFactory(MoshiConverterFactory.create())
        .build()

    fun <API> createService(apiClass: Class<API>): API {
        return retrofit.create(apiClass)
    }

    fun logInterceptor() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
}
