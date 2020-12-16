package com.deindeal.app.data.sources.api

import com.deindeal.app.data.sources.api.services.BaseService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object ApiSource {
    private const val BASE_URL = "https://cpalasanu.github.io/"

    private val logger =
        HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

    private val client = OkHttpClient.Builder()
        .addInterceptor(logger)
        .build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    /**
     * Create service with Retrofit
     */
    fun <T : BaseService> createService(type: Class<T>) = retrofit.create(type)
}