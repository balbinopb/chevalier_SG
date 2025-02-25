package com.example.treki

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.treki.screens.LoginScreen.LoginScreen
import com.example.treki.screens.LoginScreen.LoginViewModel
import com.example.treki.ui.theme.TrekiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrekiTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    val viewmodel= viewModel<LoginViewModel>()
                    val state by viewmodel.state.collectAsState()
                    LoginScreen(
                        modifier = Modifier.padding(innerPadding),
                        //viewModel=viewmodel
                        state=state,
                        onEvent = viewModel::onEvent

                     )
                }
            }
        }
    }
}
