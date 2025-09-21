package com.example.counterapp.event

sealed interface LoginEvent {
    data class EmailChanged(val email: String) : LoginEvent
    data class PasswordChanged(val password: String) : LoginEvent
    object ToggleVisibility : LoginEvent
    object Submit : LoginEvent
}
