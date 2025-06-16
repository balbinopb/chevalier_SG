package org.cheva.miniprojecttodolist.dashboard

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.treki.ui.theme.PrimaryColor


@Composable
fun HomeScreen() {
    Text(
        text = "HOME SCREEN",
        fontSize = 15.sp,
        fontWeight = FontWeight.Bold,
        color = PrimaryColor
        //modifier = Modifier.align(Alignment.CenterHorizontally)
    )
}
