package com.localgo.artelab.data.repository

import com.localgo.artelab.data.local.SessionManager//administrador de sesión

class UserRepository(private val sessionManager: SessionManager) {

    suspend fun login(email: String, password: String): Boolean {
        // Simulación: si los datos son correctos
        return if (email == "admin@artelab.com" && password == "1234") {
            sessionManager.saveAuthToken("fake-token-123")
            true
        } else {
            false
        }
    }

    suspend fun logout() {//usuario salio de sesion
        sessionManager.clearAuthToken()
    }

    suspend fun isUserLoggedIn(): Boolean {//el usuario ingreso
        return sessionManager.getAuthToken() != null
    }
}
