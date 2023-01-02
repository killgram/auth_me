package com.example.authme.screens.auth

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment

@Composable
fun AuthScreen(onAuthHandler: () -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "You need to log in with biometrics",
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Button(
            onClick = onAuthHandler,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 32.dp)
        ) {
            Text("Login", style = MaterialTheme.typography.h5)
        }
    }
}
