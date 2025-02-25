package com.example.treki.screens.LoginScreen

import androidx.lifecycle.ViewModel
import com.example.treki.MainEvent
import com.example.treki.MainState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LoginViewModel : ViewModel() {

    private val _state= MutableStateFlow(MainState())
    val state=_state.asStateFlow()

    fun onEvent(event: MainEvent){
        when(event){
            is MainEvent.OnEmailChanged -> updateUsername(event.email)
            is MainEvent.OnPasswordChanged -> updatePassword(event.password)
            is MainEvent.OnIsVisibleChanged -> updateIsVisible(event.isVisible)
            MainEvent.OnLogin -> login()
        }
    }

    private fun login() {

    }

    private fun updateIsVisible(newisVisible: Boolean) {
        _state.update {
            it.copy(
                isVisible = newisVisible
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


//    private val _email = MutableStateFlow("")
//    val email=_email.asStateFlow()
//
//    private val _password= MutableStateFlow("")
//    val password=_password.asStateFlow()
//
//    private val _isVisible= MutableStateFlow(false)
//    val isVisible=_isVisible.asStateFlow()
//
//    fun updateEmail(newEmail: String){
//        _email.value=newEmail
//    }
//
//    fun updatePassword(newPw: String){
//        _password.value=newPw
//    }
//
//    fun updateVisible(){
//        _isVisible.value=!_isVisible.value
//    }



}