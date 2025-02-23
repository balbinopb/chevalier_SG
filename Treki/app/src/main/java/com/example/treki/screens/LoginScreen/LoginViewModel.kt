package com.example.treki.screens.LoginScreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class LoginViewModel : ViewModel() {

    private val _email = MutableStateFlow("")
    val email=_email.asStateFlow()

    private val _password= MutableStateFlow("")
    val password=_password.asStateFlow()

    private val _isVisible= MutableStateFlow(false)
    val isVisible=_isVisible.asStateFlow()

    fun updateEmail(newEmail: String){
        _email.value=newEmail
    }

    fun updatePassword(newPw: String){
        _password.value=newPw
    }

    fun updateVisible(){
        _isVisible.value=!_isVisible.value
    }



}