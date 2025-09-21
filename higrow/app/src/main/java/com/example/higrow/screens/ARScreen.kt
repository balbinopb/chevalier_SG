package com.example.higrow.ui.screens

import android.Manifest
import android.content.pm.PackageManager
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.higrow.viewmodel.ARViewModel
import io.github.sceneview.ar.ARSceneView

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ARScreen(
    modifier: Modifier = Modifier,
    arViewModel: ARViewModel
) {
    val context = LocalContext.current

    // 🔹 State izin kamera
    var hasCameraPermission by remember {
        mutableStateOf(
            ContextCompat.checkSelfPermission(
                context, Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED
        )
    }

    // 🔹 Request permission launcher
    val launcher = rememberLauncherForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { granted ->
        hasCameraPermission = granted
    }

    // 🔹 Jalankan saat Composable pertama kali
    LaunchedEffect(Unit) {
        if (!hasCameraPermission) {
            launcher.launch(Manifest.permission.CAMERA)
        }
    }

    // 🔹 UI
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("HiGrow AR Measurement") })
        }
    ) { padding ->
        Box(
            modifier = modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            if (hasCameraPermission) {
                // 🚀 AR Camera View
                AndroidView(
                    modifier = Modifier.fillMaxSize(),
                    factory = { context ->
                        ARSceneView(context).apply {
                            // Aktifkan plane renderer (biar kelihatan bidang AR)
                            planeRenderer.isVisible = true

                            // Callback setiap ada frame baru dari ARCore
                            onSessionUpdated = { _, frame ->
                                arViewModel.updateHeight(frame)
                            }
                        }
                    }
                )
            } else {
                // 🚫 Kalau izin belum ada
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally
                ) {
                    Text("Camera permission required for AR")
                    Spacer(modifier = Modifier.height(8.dp))
                    Button(onClick = {
                        launcher.launch(Manifest.permission.CAMERA)
                    }) {
                        Text("Grant Permission")
                    }
                }
            }
        }
    }
}
