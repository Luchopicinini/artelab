package com.localgo.artelab.data.remote

import com.localgo.artelab.data.remote.dto.ProductDto
import retrofit2.http.GET

interface ApiService {

    // API externa
    @GET("products/1")
    suspend fun getExternalProduct(): ProductDto
}
