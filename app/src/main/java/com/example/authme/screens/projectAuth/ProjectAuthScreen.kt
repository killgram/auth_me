package com.example.authme.screens.projectAuth

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Suppress("UNUSED_PARAMETER")
@Composable
fun ProjectAuthScreen(
    projectTypeArg: String
) {
    Column {
        Text("ProjectAuthScreen")
        Text(projectTypeArg)
    }
}