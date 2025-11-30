package com.localgo.artelab.data.repository

import com.localgo.artelab.data.remote.ApiService
import com.localgo.artelab.data.remote.ProductoDto

class ExternalProductRepository(private val api: ApiService) {

    suspend fun getExternalProduct(): ProductoDto {
        return api.getProductos().first() // solo toma el primer producto
    }
}
