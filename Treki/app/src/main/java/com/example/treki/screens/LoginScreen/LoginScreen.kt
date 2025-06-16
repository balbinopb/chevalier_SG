

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.treki.HomeScreen
import com.example.treki.R
import com.example.treki.screens.LoginScreen.LoginEvent
import com.example.treki.screens.LoginScreen.LoginState
import com.example.treki.screens.LoginScreen.compents.passwordField
import com.example.treki.screens.LoginScreen.compents.textField
import com.example.treki.ui.theme.PrimaryColor
import kotlin.reflect.KFunction1

@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    onNavigate: (Any)->Unit,
    state: LoginState,
    onEvent: KFunction1<LoginEvent, Unit>
) {

    val pacifico = FontFamily(Font(R.font.pacifico_regular))

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
                hint = "email",
                value = state.email,
                onValueChange = { newEmail ->
                    onEvent(LoginEvent.OnEmailChanged(newEmail))
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
                hint = "Password",
                value = state.password, // Use state password value
                isPasswordVisible = state.isVisible, // Use state visibility
                onValueChange = { newPassword -> onEvent(LoginEvent.OnPasswordChanged(newPassword)) },
                onToggleVisibility = { onEvent(LoginEvent.TogglePasswordVisibility) }, // Use the new event
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Password"
                    )
                }
            )


            Button(
                onClick = {
                    onNavigate(HomeScreen)
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