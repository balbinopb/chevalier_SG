package com.example.treki.screens.LoginScreen.compents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.treki.screens.LoginScreen.LoginViewModel
import androidx.compose.material.icons.filled.*
import com.example.treki.MainEvent
import com.example.treki.MainState

@Composable
fun passwordField(
    modifier: Modifier = Modifier,
    hint: String,
    value: String, // Accept password value
    isPasswordVisible: Boolean, // Accept visibility state
    onValueChange: (String) -> Unit, // Handle password changes
    onToggleVisibility: () -> Unit, // Handle visibility toggle
    leadingIcon: @Composable (() -> Unit)
) {
    Column(modifier = modifier) {
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = value,
            onValueChange = onValueChange, // Use passed event handler
            placeholder = { Text(hint) },
            leadingIcon = leadingIcon,
            trailingIcon = {
                val image = if (isPasswordVisible) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                IconButton(onClick = onToggleVisibility) {
                    Icon(imageVector = image,contentDescription = "Toggle Password Visibility")
                }
            },
            visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Password),
            singleLine = true,
        )
    }
}


