package com.example.higrow.screens

import com.google.ar.core.Frame
import kotlin.math.absoluteValue

object HeightCalculator {
    fun calculateHeight(frame: Frame, width: Int, height: Int): Float? {
        val headZ = ARCoreHelper.getDepth(frame, width / 2, 50) ?: return null
        val footZ = ARCoreHelper.getDepth(frame, width / 2, height - 50) ?: return null

        return (footZ - headZ).absoluteValue
    }
}

