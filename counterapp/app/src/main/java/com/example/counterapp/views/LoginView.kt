package com.example.counterapp.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.counterapp.event.CounterEvent
import com.example.counterapp.event.LoginEvent
import com.example.counterapp.state.CounterState
import com.example.counterapp.state.LoginState
import kotlin.reflect.KFunction1

@Composable
fun LoginView(
    modifier: Modifier = Modifier,
    state: LoginState,
    onEvent: KFunction1<LoginEvent,Unit>,
    navController: NavHostController
) {

    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("LOGIN TO YOUR ACCOUNT", fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(15.dp))

//         Email TextField
        OutlinedTextField(
            value =state.email,
            onValueChange = {newEmail-> onEvent(LoginEvent.EmailChanged(newEmail)) },
            label = { Text("Email") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.7f),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

//         Password TextField
        TextField(
            value = state.password,
            onValueChange = {newPw-> onEvent(LoginEvent.PasswordChanged(newPw)) },
            label = { Text("Password") },
            trailingIcon = {
                Icon(
                    imageVector = Icons.Filled.Visibility,
                    contentDescription = "visibility"
                )
            },
            //visualTransformation = PasswordVisualTransformation(),//we will change it when we use visibilty
            singleLine = true,
            modifier = Modifier.fillMaxWidth(0.7f),
            shape = RoundedCornerShape(8.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )

        Spacer(modifier = Modifier.height(10.dp))

//        Button
        Button(
            onClick = {
                onEvent(LoginEvent.Submit)
            },
//            enabled = false,
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(45.dp),
            shape = RoundedCornerShape(8.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF6200EE), // background
                contentColor = Color.White,         // text/icon color
                disabledContainerColor = Color.Red,
                disabledContentColor = Color.Yellow
            )

        ) {
            Text("Login")

        }
    }
}
