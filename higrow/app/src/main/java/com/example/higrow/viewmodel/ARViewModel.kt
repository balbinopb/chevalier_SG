package com.example.higrow.viewmodel

import androidx.lifecycle.ViewModel
import com.example.higrow.screens.HeightCalculator
import com.google.ar.core.Frame
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class ARViewModel : ViewModel() {
    private val _personHeight = MutableStateFlow(0f)
    val personHeight = _personHeight.asStateFlow()

    fun updateHeight(frame: Frame) {
        val image = frame.acquireCameraImage()
        val height = image.height
        val width = image.width

        val result = HeightCalculator.calculateHeight(frame, width, height)
        if (result != null) {
            _personHeight.value = result
        }
    }
}