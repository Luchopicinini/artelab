package com.localgo.artelab.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.localgo.artelab.data.repository.ExternalProductRepository
import com.localgo.artelab.data.remote.RetrofitClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    private val repo = ExternalProductRepository(RetrofitClient.api)

    private val _externalProductTitle = MutableStateFlow("Cargando...")
    val externalProductTitle: StateFlow<String> = _externalProductTitle

    // -----------------------------
    // Método original para cargar productos de FakeStore
    fun loadExternalProduct() {
        viewModelScope.launch {
            try {
                val producto = repo.getExternalProduct()
                _externalProductTitle.value = "${producto.title} - ${producto.price} USD"
            } catch (e: Exception) {
                _externalProductTitle.value = "Error cargando producto externo"
            }
        }
    }

    // -----------------------------
    // Método para probar conexión a Render
    fun testRenderConnection() {
        viewModelScope.launch {
            try {
                // Llamada de prueba a Render (asegúrate que el endpoint exista)
                val productos = RetrofitClient.renderApi.getProductos() // devuelve List<ProductoDto>
                Log.d("RENDER_TEST", "✅ Conexión OK, recibidos ${productos.size} productos")
            } catch (e: Exception) {
                Log.e("RENDER_TEST", "❌ Fallo conexión: $e")
            }
        }
    }
}
