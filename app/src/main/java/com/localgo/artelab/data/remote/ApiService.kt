package com.localgo.artelab.data.remote

import retrofit2.http.GET

interface ApiService {
    @GET("products")
    suspend fun getProductos(): List<ProductoDto>
}
