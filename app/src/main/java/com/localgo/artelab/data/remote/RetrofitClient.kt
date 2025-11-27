package com.localgo.artelab.data.remote

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    // ⭐ URL REAL DE TU API EN RENDER
    private const val BASE_URL = "https://artelabapi.onrender.com/api/"

    private val client = OkHttpClient.Builder().build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    // ⭐ ESTA ES TU API (la que subiste tú)
    val api: ApiService = retrofit.create(ApiService::class.java)
}
