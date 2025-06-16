package com.example.todolist

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todolist.Navigation.DashboardScreen
import com.example.todolist.Navigation.LoginScreen
import com.example.todolist.Screens.Dashboard.DashboardScreen
import com.example.todolist.Screens.Login.LoginViewModel
import com.example.todolist.ui.theme.TodoListTheme




class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TodoListTheme {
                val navController= rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = LoginScreen,
                ) {
                    composable<LoginScreen>{
                        val viewmodel = viewModel<LoginViewModel>()
                        val state by viewmodel.state.collectAsStateWithLifecycle()
                        LoginScreen(
                            onNavigate = { dest ->
                                navController.navigate(dest)

                            },
                            state = state,
                            onEvent = viewmodel::onEvent

                        )

                    }
                    composable<DashboardScreen>{
                        DashboardScreen(username = "Balbino")
                    }
                }
                //LoginScreen()

            }
        }
    }
}
