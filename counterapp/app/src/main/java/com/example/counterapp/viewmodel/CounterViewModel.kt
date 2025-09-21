package com.example.counterapp.viewmodel

import androidx.lifecycle.ViewModel
import com.example.counterapp.event.CounterEvent
import com.example.counterapp.state.CounterState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CounterViewModel: ViewModel(){

    private val _uiState = MutableStateFlow(CounterState())
    val state=_uiState.asStateFlow()

    fun onEvent(event : CounterEvent){
        when(event){
            is CounterEvent.Increase -> {
                _uiState.update { it ->
                    it.copy(
                        count = it.count+event.value
                    )
                }
            }

            is CounterEvent.Decrease -> {
                _uiState.update { it ->
                    it.copy(
                        count = if (it.count>0){
                            it.count-event.value
                        }else{
                            it.count
                        }
                    )
                }
            }
            CounterEvent.Reset -> {
                _uiState.update { it ->
                    it.copy(
                        count = 0
                    )
                }
            }
        }
    }

}