package com.example.counterapp.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.counterapp.event.LoginEvent
import com.example.counterapp.model.LoginRequest
import com.example.counterapp.network.RetrofitClient
import com.example.counterapp.state.LoginState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    private val apiService = RetrofitClient.apiService

    private val _uiState = MutableStateFlow(LoginState())
    val state = _uiState.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.EmailChanged -> {
                _uiState.update { it.copy(email = event.email) }
            }

            is LoginEvent.PasswordChanged -> {
                _uiState.update { it.copy(password = event.password) }
            }

            LoginEvent.ToggleVisibility -> {
                _uiState.update { it.copy(visibility = !it.visibility) }
            }

            LoginEvent.Submit -> {
                login(_uiState.value.email, _uiState.value.password)
            }
        }
    }

    private fun login(email: String, password: String) {
        Log.d("LoginViewModel", "================LOGIN GET CALLED=========================")

        _uiState.update { it.copy(isLoading = true, error = null, loginSuccess = false) }

        viewModelScope.launch {
            try {
                Log.d("LoginViewModel", "================LOGIN GET CALLED before response=========================")

                val response = apiService.login(LoginRequest(email, password)) // suspend call
                Log.d("LoginViewModel",":>================$response=========================")
                if (response.isSuccessful && response.body() != null) {
                    _uiState.update {
                        it.copy(
                            isLoading = false,
                            user = response.body(),
                            loginSuccess = true
                        )
                    }
                } else {
                    _uiState.update {
                        it.copy(isLoading = false, error = "Login failed", loginSuccess = false)
                    }
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(isLoading = false, error = e.message, loginSuccess = false)
                }
            }
        }

    }
}
