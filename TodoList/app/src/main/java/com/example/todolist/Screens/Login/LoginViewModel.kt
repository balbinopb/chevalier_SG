package com.example.todolist.Screens.Login

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update


class LoginViewModel: ViewModel() {
    private val _state= MutableStateFlow(LoginState())
    val state=_state.asStateFlow()

    fun onEvent(event : LoginEvent){
        when(event){
            is LoginEvent.UsernameChanged -> updateUsername(event.username)
            is LoginEvent.PasswordChanged -> updatePassword(event.pw)
            is LoginEvent.VisibilityChanged-> updateVisibilityChanged(event.visibility)
            LoginEvent.TogglePasswordVisibility-> updateTogglePasswordVisibility()
        }
    }

    private fun updateTogglePasswordVisibility() {
        _state.update { curState ->
            curState.copy(
                visibility = ! curState.visibility
            )

        }
    }

    private fun updateVisibilityChanged(visibility: Boolean) {
        _state.update {
            it.copy(
                visibility=visibility
            )
        }
    }

    private fun updatePassword(pw: String) {
        _state.update {
            it.copy(
                pw=pw
            )
        }
    }

    private fun updateUsername(username: String) {
        _state.update {
            it.copy(
                username=username
            )
        }
    }
}