package com.example.counterapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.counterapp.viewmodel.CounterViewModel
import com.example.counterapp.viewmodel.LoginViewModel
import com.example.counterapp.views.CounterView
import com.example.counterapp.views.LoginView

@Composable
fun AppNavigation(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = AppRoutes.LOGIN
    ) {
        // Login route
        composable(AppRoutes.LOGIN) {
            val loginViewModel = viewModel<LoginViewModel>()
            val loginState by loginViewModel.state.collectAsStateWithLifecycle()

            // UI
            LoginView(
                state = loginState,
                onEvent = loginViewModel::onEvent,
                navController = navController
            )

            // Observe login success and navigate
            LaunchedEffect(loginState.loginSuccess) {
                if (loginState.loginSuccess) {
                    navController.navigate(AppRoutes.COUNTER) {
                        popUpTo(AppRoutes.LOGIN) { inclusive = true }
                    }
                }
            }
        }

        // Home route
        composable(AppRoutes.COUNTER) {
            val counterviewModel=viewModel<CounterViewModel>()
            val counterState by counterviewModel.state.collectAsStateWithLifecycle()
            CounterView(
                state = counterState,
                onEvent = counterviewModel::onEvent
            )
        }
    }
}

