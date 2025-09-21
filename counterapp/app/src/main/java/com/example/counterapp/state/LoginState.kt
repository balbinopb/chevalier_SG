package com.example.counterapp.state

import com.example.counterapp.model.LoginResponse

data class LoginState(
    val email: String = "",
    val password: String = "",
    val visibility: Boolean = false,
    val isLoading: Boolean = false,
    val error: String? = null,
    val loginSuccess: Boolean = false,
    val errorMessage: String? = null,
    val user: LoginResponse? = null
)
