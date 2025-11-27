package com.localgo.artelab.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.localgo.artelab.data.remote.RetrofitClient
import com.localgo.artelab.repository.ExternalProductRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val externalRepo = ExternalProductRepository(RetrofitClient.externalApi)

    private val _externalProductTitle = MutableStateFlow("")
    val externalProductTitle: StateFlow<String> = _externalProductTitle

    fun loadExternalProduct() {
        viewModelScope.launch {
            try {
                _externalProductTitle.value = "Cargando producto..."
                val product = externalRepo.getExternalProduct()
                _externalProductTitle.value = "Producto externo: ${product.title}"
            } catch (e: Exception) {
                _externalProductTitle.value = "Error cargando producto externo"
            }
        }
    }
}

