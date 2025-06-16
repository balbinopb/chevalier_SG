package com.example.digitrecognition.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DigitRecognitionScreen() {
    var result by remember { mutableStateOf("") }
    var paths = remember { mutableStateListOf<List<Pair<Float, Float>>>() }
    var currentPath = remember { mutableStateListOf<Pair<Float, Float>>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFB0B05A)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Digit Recognition",
            fontSize = 24.sp,
            color = Color.White,
            modifier = Modifier.padding(16.dp)
        )

        // Drawing Canvas
        DrawingCanvas(
            paths = paths,
            currentPath = currentPath,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .background(Color(0xFFD3D2D3))
                .padding(8.dp)
        )

        // Buttons Row
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFD3D2D3))
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                onClick = {
                    paths.clear()
                    currentPath.clear()
                    result = ""
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9E9E50)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Clear", color = Color.White)
            }

            Button(
                onClick = {
                    result = "Predicting..."
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF9E9E50)),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "Predict", color = Color.White)
            }
        }

        // Result Box
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFF9E9E50))
                .padding(16.dp),
            contentAlignment = Alignment.CenterStart
        ) {
            Text(
                text = "Result : $result",
                color = Color.White,
                fontSize = 18.sp
            )
        }
    }
}