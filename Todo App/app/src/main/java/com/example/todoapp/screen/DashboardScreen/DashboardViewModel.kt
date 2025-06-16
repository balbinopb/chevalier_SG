package com.example.todoapp.screen.DashboardScreen

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.example.todoapp.screen.AddTask.Task
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class DashboardViewModel : ViewModel() {
    private val _state = MutableStateFlow(DashboardState())
    val state = _state.asStateFlow()

    fun onEvent(event: DashboardEvent) {
        when (event) {
            is DashboardEvent.AddTask -> addTask(event.task)
            is DashboardEvent.RemoveTask -> removeTask(event.index)
            DashboardEvent.ShowDialog -> updateShowDialog(true)
            DashboardEvent.HideDialog -> updateShowDialog(false)
        }
    }

    private fun addTask(task: Task) {
        _state.update { curState ->
            curState.copy(
                tasks = curState.tasks + task,
                showDialog = false
            )
        }
    }

    private fun removeTask(index: Int) {
        _state.update { curState ->
            val updatedTasks = curState.tasks.toMutableList().apply { removeAt(index) }
            curState.copy(tasks = updatedTasks)
        }
    }

    private fun updateShowDialog(show: Boolean) {
        _state.update { curState ->
            curState.copy(showDialog = show)
        }
    }

    fun onTitleChange(newTitle: TextFieldValue) {
        _state.update { curState ->
            curState.copy(newTaskTitle = newTitle)
        }
    }

    fun onDescriptionChange(newDescription: TextFieldValue) {
        _state.update { curState ->
            curState.copy(newTaskDescription = newDescription)
        }
    }

    fun onCategoryChange(newCategory: TextFieldValue) {
        _state.update { curState ->
            curState.copy(newTaskCategory = newCategory)
        }
    }
}
