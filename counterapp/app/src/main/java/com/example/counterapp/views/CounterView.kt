package com.example.counterapp.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import com.example.counterapp.event.CounterEvent
import com.example.counterapp.state.CounterState
import kotlin.reflect.KFunction1

@Composable
fun CounterView(
    state: CounterState,
    onEvent: KFunction1<CounterEvent,Unit>
    ) {

    Row(
        modifier = Modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        IconButton(
            onClick = {
                onEvent(CounterEvent.Decrease())
            }
        ) {
            Icon(
                imageVector = Icons.Filled.Remove,
                contentDescription = "Decrease"
            )
        }
        Text(
            text = "${state.count}",
            fontSize = 20.sp
        )

        IconButton(
            onClick = {
                onEvent(CounterEvent.Increase())
            }
        ) {
            Icon(
                imageVector=Icons.Filled.Add,
                contentDescription = "Increase"
            )
        }
    }

}