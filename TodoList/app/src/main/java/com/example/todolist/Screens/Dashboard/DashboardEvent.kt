package com.example.todoapp.screen.DashboardScreen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.BasicTextField
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
import kotlinx.coroutines.launch
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed interface DashboardEvent {
    data class AddTask(val title: String, val description: String, val category: String) : DashboardEvent
    object ShowDialog : DashboardEvent
    object HideDialog : DashboardEvent
}

data class DashboardState(
    val tasks: List<Triple<String, String, String>> = emptyList(),
    val showDialog: Boolean = false
)

class DashboardViewModel : ViewModel() {
    private val _state = MutableStateFlow(DashboardState())
    val state: StateFlow<DashboardState> = _state.asStateFlow()

    fun onEvent(event: DashboardEvent) {
        when (event) {
            is DashboardEvent.AddTask -> {
                _state.value = _state.value.copy(
                    tasks = _state.value.tasks + Triple(event.title, event.description, event.category),
                    showDialog = false
                )
            }
            DashboardEvent.ShowDialog -> _state.value = _state.value.copy(showDialog = true)
            DashboardEvent.HideDialog -> _state.value = _state.value.copy(showDialog = false)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DashboardScreen(viewModel: DashboardViewModel = androidx.lifecycle.viewmodel.compose.viewModel(), username: String) {
    val state by viewModel.state.collectAsState()

    var newTaskTitle by remember { mutableStateOf(TextFieldValue()) }
    var newTaskDescription by remember { mutableStateOf(TextFieldValue()) }
    var newTaskCategory by remember { mutableStateOf(TextFieldValue()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Text(
                            text = "Todo App",
                            fontSize = 35.sp,
                            fontWeight = FontWeight.Bold,
                            fontFamily = FontFamily.Cursive
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(Color.LightGray)
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { viewModel.onEvent(DashboardEvent.ShowDialog) },
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
            Text(text = "Welcome, $username!", fontSize = 20.sp)

            Spacer(modifier = Modifier.height(16.dp))

            if (state.tasks.isEmpty()) {
                Text("No tasks available.", fontSize = 16.sp)
            } else {
                LazyColumn {
                    items(state.tasks.size) { index ->
                        val (title, description, category) = state.tasks[index]
                        Card(
                            modifier = Modifier
                                .padding(8.dp)
                                .fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = Color(0xFFE0E0E0))
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(text = "Title: $title", fontWeight = FontWeight.Bold)
                                Text(text = "Description: $description")
                                Text(text = "Category: $category", fontWeight = FontWeight.Light)
                            }
                        }
                    }
                }
            }

            if (state.showDialog) {
                AlertDialog(
                    onDismissRequest = { viewModel.onEvent(DashboardEvent.HideDialog) },
                    confirmButton = {
                        TextButton(onClick = {
                            if (newTaskTitle.text.isNotBlank() && newTaskDescription.text.isNotBlank() && newTaskCategory.text.isNotBlank()) {
                                viewModel.onEvent(
                                    DashboardEvent.AddTask(
                                        newTaskTitle.text,
                                        newTaskDescription.text,
                                        newTaskCategory.text
                                    )
                                )
                                newTaskTitle = TextFieldValue("")
                                newTaskDescription = TextFieldValue("")
                                newTaskCategory = TextFieldValue("")
                            }
                        }) {
                            Text("Add Task")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { viewModel.onEvent(DashboardEvent.HideDialog) }) {
                            Text("Cancel")
                        }
                    },
                    text = {
                        Column {
                            Text(text = "New Task")
                            BasicTextField(
                                value = newTaskTitle,
                                onValueChange = { newTaskTitle = it },
                                modifier = Modifier.fillMaxWidth().padding(8.dp)
                            )
                            BasicTextField(
                                value = newTaskDescription,
                                onValueChange = { newTaskDescription = it },
                                modifier = Modifier.fillMaxWidth().padding(8.dp)
                            )
                            BasicTextField(
                                value = newTaskCategory,
                                onValueChange = { newTaskCategory = it },
                                modifier = Modifier.fillMaxWidth().padding(8.dp)
                            )
                        }
                    }
                )
            }
        }
    }
}
