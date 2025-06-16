package com.example.treki.screens.LoginScreen

sealed interface LoginEvent {
    data class OnEmailChanged(val email: String): LoginEvent
    data class OnPasswordChanged(val password: String): LoginEvent
    data class OnIsVisibleChanged(val isVisible: Boolean): LoginEvent
    data object OnLogin:LoginEvent
    data object TogglePasswordVisibility : LoginEvent

}