package com.example.todoapp.screen.DashboardScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ExitToApp
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.todoapp.screen.AddTask.Task
import kotlin.reflect.KFunction1
import androidx.navigation.NavController


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(
    state: DashboardState,
    onEvent: KFunction1<DashboardEvent, Unit>,
    navController: NavController
) {
    val viewModel = viewModel<DashboardViewModel>()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Text(
                            text = "Todo App",
                            fontSize = 35.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Cursive
                        )
                    }

                },
                colors = TopAppBarDefaults.topAppBarColors(Color.LightGray),
                actions = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.AutoMirrored.Filled.ExitToApp, contentDescription = "Logout")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onEvent(DashboardEvent.ShowDialog) },
                containerColor = Color.LightGray,
            ) {
                Text("+", color = Color.Black)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {




            if (state.tasks.isEmpty()) {
                Text("No tasks available.", fontSize = 16.sp)
            } else {
                LazyColumn {
                    items(state.tasks.size) { index ->
                        val task = state.tasks[index]
                        Card(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFE0E0E0))
                        ) {
                            Row(
                                modifier = Modifier.padding(16.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    Text(text = "Title: ${task.title}", fontWeight = FontWeight.Bold)
                                    Text(text = "Description: ${task.description}")
                                    Text(text = "Category: ${task.category}", fontWeight = FontWeight.Light)
                                }
                                IconButton(onClick = { onEvent(DashboardEvent.RemoveTask(index)) }) {
                                    Icon(Icons.Filled.Delete, contentDescription = "Delete Task")
                                }
                            }
                        }
                    }
                }
            }

            if (state.showDialog) {
                AlertDialog(
                    onDismissRequest = { onEvent(DashboardEvent.HideDialog) },
                    confirmButton = {
                        TextButton(onClick = {
                            if (state.newTaskTitle.text.isNotBlank() && state.newTaskDescription.text.isNotBlank() && state.newTaskCategory.text.isNotBlank()) {
                                onEvent(
                                    DashboardEvent.AddTask(
                                        Task(state.newTaskTitle.text, state.newTaskDescription.text, state.newTaskCategory.text)
                                    )
                                )
                                viewModel.onTitleChange(TextFieldValue(""))
                                viewModel.onDescriptionChange(TextFieldValue(""))
                                viewModel.onCategoryChange(TextFieldValue(""))
                            }
                        }) {
                            Text("Add Task")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { onEvent(DashboardEvent.HideDialog) }) {
                            Text("Cancel")
                        }
                    },
                    text = {
                        Column {
                            Text(text = "New Task")
                            TextField(
                                value = state.newTaskTitle,
                                onValueChange = { viewModel.onTitleChange(it) },
                                label = { Text("Title",fontSize = 10.sp,fontFamily = FontFamily.Serif) },
                                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                            )
                            TextField(
                                value = state.newTaskDescription,
                                onValueChange = { viewModel.onDescriptionChange(it) },
                                label = { Text("Description",fontSize = 10.sp,fontFamily = FontFamily.Serif) },
                                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                            )
                            TextField(
                                value = state.newTaskCategory,
                                onValueChange = { viewModel.onCategoryChange(it) },
                                label = { Text("Category",fontSize = 10.sp,fontFamily = FontFamily.Serif) },
                                modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                            )
                        }
                    }
                )
            }
        }
    }
}
