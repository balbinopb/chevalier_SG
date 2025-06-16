package com.example.todoapp.screen.DashboardScreen

import com.example.todoapp.screen.AddTask.Task

sealed interface DashboardEvent {
    data class AddTask(val task: Task) : DashboardEvent
    data class RemoveTask(val index: Int) : DashboardEvent
    object ShowDialog : DashboardEvent
    object HideDialog : DashboardEvent
}