package com.example.todoapp.screen.DashboardScreen

import androidx.compose.ui.text.input.TextFieldValue
import com.example.todoapp.screen.AddTask.Task

data class DashboardState(
    val tasks: List<Task> = emptyList(),
    val showDialog: Boolean = false,
    val newTaskTitle: TextFieldValue = TextFieldValue(),
    val newTaskDescription: TextFieldValue = TextFieldValue(),
    val newTaskCategory: TextFieldValue = TextFieldValue()
)