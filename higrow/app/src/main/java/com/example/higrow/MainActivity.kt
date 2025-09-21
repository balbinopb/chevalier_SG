package com.example.higrow

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.higrow.ui.screens.ARScreen
import com.example.higrow.ui.theme.HigrowTheme
import com.example.higrow.viewmodel.ARViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HigrowTheme {
                val arViewModel = viewModel<ARViewModel>()

                ARScreen(modifier = Modifier, arViewModel=arViewModel)
            }
        }
    }

}
