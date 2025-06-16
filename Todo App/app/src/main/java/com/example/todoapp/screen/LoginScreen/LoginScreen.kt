package com.example.todoapp.screen.LoginScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.todoapp.screen.Navigation.DashboardScreen
import kotlin.reflect.KFunction1

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onNavigate: (Any) -> Unit,
    state: LoginState,
    onEvent: KFunction1<LoginEvent,Unit>
) {

        Column(
            modifier = modifier.fillMaxSize().padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Todo App",
                color = Color.LightGray,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Cursive
            )
            Spacer(modifier=modifier.padding(12.dp))
            Text(
                text = "Login",
                color = Color.Gray,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily.Cursive
            )

            Spacer(modifier=modifier.padding(12.dp))

            //email field
            OutlinedTextField(
                value = state.username,
                onValueChange = { newUsername->
                    onEvent(LoginEvent.UsernameChanged(newUsername))
                },
                placeholder = {
                    Text(text = "username",color = if (state.username.isEmpty()) Color.LightGray else Color.Black)
                } ,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                //textStyle = androidx.compose.ui.text.TextStyle(color = Color.Black),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.DarkGray,   // For focused state
                    unfocusedBorderColor = Color.LightGray,  // For unfocused state
                )
            )

            Spacer(modifier=modifier.padding(10.dp))

            //pw field
            OutlinedTextField(
                value = state.pw,
                onValueChange = { pw->
                    onEvent(LoginEvent.PasswordChanged(pw))
                },
                placeholder = {
                    Text(text = "password", color = Color.LightGray)
                } ,
                singleLine = true,
                modifier = Modifier.fillMaxWidth(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                visualTransformation = if (state.visibility) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    val image = if (state.visibility) Icons.Filled.Visibility else Icons.Filled.VisibilityOff
                    IconButton(
                        onClick = { onEvent(LoginEvent.TogglePasswordVisibility) }) {
                        Icon(imageVector = image, contentDescription = if (state.visibility) "Hide password" else "Show password")
                    }
                },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color.Unspecified,
                    unfocusedBorderColor = Color.LightGray
                )
            )

            Spacer(modifier=modifier.padding(10.dp))

            Button(
                onClick = {
                    onNavigate(DashboardScreen)
                },
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(5.dp),
                elevation = ButtonDefaults.elevatedButtonElevation(3.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Gray
                ),
                enabled = state.username.isNotEmpty() && state.pw.isNotEmpty()
            ) {
                Text(text = "Login", color = Color.LightGray)
            }

        }
}