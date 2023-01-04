package com.example.authme.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlin.time.Duration.Companion.seconds

private const val WaitTime: Int = 30

@Composable
fun CircularProgressComponent(
    doAction: () -> Unit = {}
) {
    var time by remember { mutableStateOf(WaitTime) }
    val progress by animateFloatAsState((time.toDouble() / 30).toFloat())
    LaunchedEffect(Unit) {
        while (time != 0) {
            delay(1.seconds)
            time--
        }
    }
    if (time == 0) {
        doAction()
    }

    Box(
        modifier = Modifier
            .padding(top = 32.dp)
            .height(350.dp)
            .fillMaxWidth()
    ) {
        CircularProgressIndicator(
            progress = progress,
            modifier = Modifier.fillMaxSize(),
            strokeWidth = 7.dp
        )
        Text(
            time.toString(),
            modifier = Modifier.align(Alignment.Center),
            style = MaterialTheme.typography.h1
        )
    }
}