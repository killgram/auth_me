package com.example.authme.screens.projectAuth

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ProjectAuthScreen(
    projectTypeArg: String,
    onBack: () -> Unit = {},
    viewModel: ProjectViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.extractProjectData(projectTypeArg)
    }
    val name by viewModel.projectName.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    Column {
        TopAppBar(
            title = { Text(name, style = MaterialTheme.typography.h5) },
            navigationIcon = {
                IconButton(
                    onClick = onBack,
                    modifier = Modifier
                        .clearAndSetSemantics {}
                ) {
                    Icon(Icons.Filled.ArrowBack, contentDescription = null)
                }
            }
        )
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

        } else {
            ProjectAuthBox(modifier = Modifier.padding(16.dp))
        }

    }
}

@Composable
fun ProjectAuthBox(modifier: Modifier) {
    Column(modifier = modifier) {
        Text("ProjectAuthBox")
    }
}