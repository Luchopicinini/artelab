package com.localgo.artelab.repository

import com.localgo.artelab.data.remote.ApiService

class ExternalProductRepository(
    private val api: ApiService
) {
    suspend fun getExternalProduct() = api.getExternalProduct()
}
