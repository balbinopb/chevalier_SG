package com.example.treki.screens.LoginScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.GoogleFont
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.treki.MainEvent
import com.example.treki.MainState
import com.example.treki.R
import com.example.treki.screens.LoginScreen.compents.passwordField
import com.example.treki.screens.LoginScreen.compents.textField
import com.example.treki.ui.theme.PrimaryColor

@Composable
fun LoginScreen(modifier: Modifier = Modifier,state:MainState,onEvent: (MainState)->Unit) {

//    val pacifico = FontFamily(
//        GoogleFont("Pacifico")
//    )
    val pacifico = FontFamily(Font(R.font.pacifico_regular))

//    val email by viewModel.email.collectAsState()
//    val password by viewModel.password.collectAsState()

    Surface {
        Column (
            modifier= Modifier.fillMaxSize().padding(25.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp,Alignment.CenterVertically),
            horizontalAlignment = Alignment.Start

        ){
            Text(
                text = "Treki",
                fontSize = 40.sp,
                color = PrimaryColor,
                fontFamily = pacifico,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier=Modifier.height(7.dp))
            Text(
                text = "Login",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
            Spacer(modifier=Modifier.height(37.dp))
            textField(
                modifier = Modifier.fillMaxWidth(),
                ///label = "email",
                hint = "email",
                value = state.email,
                onValueChange = { newEmail ->
                    //username = newUsername
                    //viewModel.updateEmail(newEmail)
                    onEvent(MainEvent.OnEmailChanged(newEmail))
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Email,
                        contentDescription = "email"
                    )
                }
            )
            Spacer(modifier=Modifier.height(4.dp))
            passwordField(
                modifier = Modifier.fillMaxWidth(),
                //label = "Password",
                hint = "Password",
                value = state.password,
                onValueChange = { newPassword ->
                    //password = text
                    //viewModel.updatePassword(newPassword)
                    onEvent(MainEvent.OnPasswordChanged(newPassword))
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Password"
                    )
                },
                viewModel = LoginViewModel()

            )

            Button(
                onClick = {

                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
                shape = RoundedCornerShape(8.dp),
                elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = 4.dp)
            ) {
                Text(
                    text = "SIGN IN",
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
            }


        }
    }
}