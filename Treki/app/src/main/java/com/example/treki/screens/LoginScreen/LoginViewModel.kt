package com.example.treki.screens.LoginScreen

import androidx.lifecycle.ViewModel
import com.example.treki.MainEvent
import com.example.treki.MainState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {

    private val _state= MutableStateFlow(LoginState())
    val state=_state.asStateFlow()

    fun onEvent(event: LoginEvent) {
        when (event) {
            is LoginEvent.OnEmailChanged -> updateUsername(event.email)
            is LoginEvent.OnPasswordChanged -> updatePassword(event.password)
            is LoginEvent.OnIsVisibleChanged -> updateIsVisible(event.isVisible)
            LoginEvent.OnLogin -> login()
            LoginEvent.TogglePasswordVisibility -> togglePasswordVisibility()
        }
    }

    private fun togglePasswordVisibility() {
        _state.update { currentState ->
            currentState.copy(isVisible = !currentState.isVisible)
        }
    }



    private fun login() {

    }

    private fun updateIsVisible(newIsVisible: Boolean) {
        _state.update {
            it.copy(
                isVisible = newIsVisible
            )
        }
    }

    private fun updatePassword(newPassword: String) {
        _state.update {
            it.copy(
                password = newPassword
            )
        }

    }

    private fun updateUsername(newEmail: String) {
        _state.update {
            it.copy(email = newEmail)
        }
    }




}