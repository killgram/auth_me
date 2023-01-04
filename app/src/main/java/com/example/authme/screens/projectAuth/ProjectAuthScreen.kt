package com.example.authme.screens.projectAuth

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.authme.ui.inputs.BaseInput

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
    val login by viewModel.login.collectAsState()
    val focusManager = LocalFocusManager.current
    val isSetPassLoading by viewModel.isSetPassLoading.collectAsState()

    var value by remember { mutableStateOf(TextFieldValue("")) }
    fun onChangeValue(newValue: TextFieldValue) {
        value = newValue
    }
    Column(modifier = Modifier
        .fillMaxSize()
        .pointerInput(Unit) {
            detectTapGestures(onTap = {
                focusManager.clearFocus()
            })
        }) {
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
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize()
            ) {
                if (login.isNotEmpty()) {
                    LoginRow(login)
                    PasswordRow(value, onChangeValue = { value -> onChangeValue(value) })
                    SetPasswordBox(value.text, isSetPassLoading)
                } else {
                    EmptyLoginBox(onRefresh = { viewModel.getLogin() })
                }
            }
        }
    }
}

@Composable
fun LoginRow(login: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text("Installed login:")
        Text(login)
    }
}

@Composable
fun PasswordRow(
    value: TextFieldValue,
    onChangeValue: (TextFieldValue) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 24.dp),
    ) {
        Text(
            "Set password",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(bottom = 12.dp)
        )
        BaseInput(value, onValueChange = { value -> onChangeValue(value) })
    }
}

@Composable
fun EmptyLoginBox(
    onRefresh: () -> Unit = {}
) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Text(
            "Auth data is empty",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        Button(
            onClick = { onRefresh() },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 12.dp)
        ) {
            Text("Refresh")
        }
    }
}

@Composable
fun SetPasswordBox(
    pass: String,
    isSetPassLoading: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 12.dp),
        verticalAlignment = Alignment.Bottom
    ) {
        if (isSetPassLoading) {
            Box(modifier = Modifier.fillMaxWidth()) {
                CircularProgressIndicator(
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )
            }
        } else {
            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(),
                enabled = pass.length > 2,
            ) {
                Text("Set new password")
            }
        }
    }
}