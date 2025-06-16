package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todoapp.screen.LoginScreen.LoginViewModel
import com.example.todoapp.screen.LoginScreen.LoginScreen
import com.example.todoapp.screen.Navigation.LoginScreen
import com.example.todoapp.ui.theme.TodoAppTheme
import androidx.compose.runtime.getValue
import com.example.todoapp.screen.DashboardScreen.DashboardScreen
import com.example.todoapp.screen.DashboardScreen.DashboardViewModel
import com.example.todoapp.screen.Navigation.DashboardScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoAppTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = LoginScreen
                ) {
                    composable<LoginScreen> {
                        val viewmodel = viewModel<LoginViewModel>()
                        val state by viewmodel.state.collectAsStateWithLifecycle()
                        LoginScreen(

                            onNavigate = {destination->
                                navController.navigate(destination)
                            },
                            state = state,
                            onEvent = viewmodel::onEvent
                        )
                    }
                    composable<DashboardScreen> {
                        val viewmodel = viewModel<DashboardViewModel>()
                        val state by viewmodel.state.collectAsStateWithLifecycle()
                        DashboardScreen(
                            state = state,
                            onEvent = viewmodel::onEvent,
                            navController = navController,
                            )
                    }
                }
            }
        }
    }
}