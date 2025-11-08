package com.localgo.artelab.viewmodel

import android.app.Application//no es activity
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.localgo.artelab.data.local.UserSessionManager
//
import kotlinx.coroutines.flow.MutableStateFlow// vm lo cambia
import kotlinx.coroutines.flow.asStateFlow //modo lectura ui(lo lee no lo cambia)
//ejecuta launch
import kotlinx.coroutines.launch//ejecuta launch

class LoginViewModel(application: Application) : AndroidViewModel(application) {//viewmodel con login, con contexto de aplicacion

    private val sessionManager = UserSessionManager(application.applicationContext)////administrador de la sesion de usuario

    private val _email = MutableStateFlow("")//cuando el email aun no esta escrito
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _errorMessage = MutableStateFlow("")//estado inicial de errorMessage
    val errorMessage = _errorMessage.asStateFlow()

    fun onEmailChanged(value: String) {//cambio de email
        _email.value = value
    }

    fun onPasswordChanged(value: String) {
        _password.value = value
    }

    fun login(onSuccess: () -> Unit) {
        when {
            _email.value.isEmpty() || _password.value.isEmpty() -> {//email vacio
                _errorMessage.value = "Completa todos los campos"
            }
            !_email.value.contains("@") -> {
                _errorMessage.value = "El correo electrónico debe contener '@'"//correo sin @
            }
            else -> {
                _errorMessage.value = ""
                // Guarda el email en DataStore y luego se pone en el perfil
                viewModelScope.launch {
                    sessionManager.saveUserEmail(_email.value)
                }
                onSuccess()//dentro del "else -> {"
            }
        }
    }
}
