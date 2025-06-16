package com.example.todolist.Screens.Login

sealed interface LoginEvent {
    data class UsernameChanged(val username : String): LoginEvent
    data class PasswordChanged(val pw : String): LoginEvent
    data class VisibilityChanged(val visibility : Boolean): LoginEvent
    data object TogglePasswordVisibility: LoginEvent
}