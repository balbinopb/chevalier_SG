package com.example.treki

sealed interface MainEvent {
    data class OnEmailChanged(val email: String): MainEvent
    data class OnPasswordChanged(val password: String): MainEvent
    data class OnIsVisibleChanged(val isVisible: Boolean): MainEvent
    data object OnLogin:MainEvent
    data object TogglePasswordVisibility : MainEvent

}