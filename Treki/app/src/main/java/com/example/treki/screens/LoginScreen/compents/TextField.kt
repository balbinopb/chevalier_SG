package com.example.treki.screens.LoginScreen.compents

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun textField(modifier: Modifier = Modifier,
              //label: String,
              hint: String,
              value: String,
              onValueChange: (String) -> Unit,
              leadingIcon:  @Composable (() -> Unit)
              ) {
    Column(
        modifier = modifier//.padding(vertical = 8.dp)
    ) {
//        Text(
//            //text = label,
//            style = MaterialTheme.typography.labelLarge
//        )
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            //label = { Text(label) },
            value = value,
            onValueChange = onValueChange,
            leadingIcon = leadingIcon,
            placeholder = {
                Text(text = hint)
            } ,
            singleLine = true
        )
    }
    
}