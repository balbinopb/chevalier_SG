package com.example.higrow.screens

import com.google.ar.core.Frame

object ARCoreHelper {
    fun getDepth(frame: Frame, x: Int, y: Int): Float? {
        return try {
            frame.acquireDepthImage().use { depthImage ->
                val buffer = depthImage.planes[0].buffer
                val width = depthImage.width

                val index = y * width + x
                buffer.getFloat(index * 4)
            }
        } catch (e: Exception) {
            null
        }
    }
}


