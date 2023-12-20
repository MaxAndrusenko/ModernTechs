package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.TextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.unit.dp

import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ClockApp()
        }
    }
}

@Composable
fun ClockApp() {
    var isRunning by remember { mutableStateOf(false) }
    var seconds by remember { mutableStateOf(0) }
    val coroutineScope = rememberCoroutineScope()
    var job by remember { mutableStateOf<Job?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Simple Clock",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = formatTime(seconds),
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {
                    isRunning = !isRunning
                    if (isRunning) {
                        job?.cancel() // Cancel previous job
                        job = coroutineScope.launch {
                            startTimer(seconds) { updatedSeconds ->
                                seconds = updatedSeconds
                            }
                        }
                    } else {
                        job?.cancel() // Stop the timer
                    }
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = if (isRunning) "Stop" else "Start")
            }
            Button(
                onClick = {
                    isRunning = false
                    seconds = 0
                    job?.cancel() // Reset the timer
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Reset")
            }
        }
    }
}

suspend fun startTimer(initialSeconds: Int, onTick: (Int) -> Unit) {
    var currentSeconds = initialSeconds
    while (true) {
        delay(1000)
        currentSeconds++
        onTick(currentSeconds)
    }
}

fun formatTime(seconds: Int): String {
    val mins = seconds / 60
    val secs = seconds % 60
    return String.format("%02d:%02d", mins, secs)
}

@Preview(showBackground = true)
@Composable
fun PreviewClockApp() {
    ClockApp()
}
