package com.example.counterapp.event

sealed interface  CounterEvent {
    data class Increase(val value: Int = 3) : CounterEvent
    data class Decrease(val value: Int = 3) : CounterEvent
    object Reset : CounterEvent

}