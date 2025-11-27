package com.localgo.artelab.data.remote

import retrofit2.http.GET

data class ExternalProductDto(
    val id: Int,
    val title: String,
    val price: Int,
    val description: String
)

interface ApiService {

    // ⭐ TU API REAL EN RENDER
    @GET("external-product")
    suspend fun getExternalProduct(): ExternalProductDto
}
