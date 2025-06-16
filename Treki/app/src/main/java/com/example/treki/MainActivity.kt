package com.example.treki

 
import LoginScreen
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
import org.cheva.miniprojecttodolist.dashboard.HomeScreen
import com.example.treki.screens.LoginScreen.LoginViewModel
import com.example.treki.ui.theme.TrekiTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TrekiTheme {
                val navController = rememberNavController()
                NavHost(
                    startDestination = LoginScreen,
                    navController = navController,
                    builder = {
                        composable<LoginScreen> {
                            val viewModel = viewModel<LoginViewModel>()
                            val state by viewModel.state.collectAsStateWithLifecycle()
                            LoginScreen(

                                onNavigate = {destination->
                                    navController.navigate(destination)
                                },
                                state = state,
                                onEvent = viewModel::onEvent
                            )
                        }
                        composable<HomeScreen> {
                            HomeScreen()
                        }
                    }
                )
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    val viewModel = viewModel<LoginViewModel>()
//                    val state by viewModel.state.collectAsState()
//                    LoginScreen(
//                        modifier = Modifier.padding(innerPadding),
//                        //viewModel=viewmodel
//                        state = state,
//                        onEvent = viewModel::onEvent
//
//                     )
//                }
            }
        }
    }
}
