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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.authme.R
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
    val password by viewModel.password.collectAsState()
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
                    LineDataRow(title = stringResource(R.string.installed_login), value = login)
                    if (password.isNotEmpty()) {
                        LineDataRow(
                            title = stringResource(R.string.installed_password),
                            value = password
                        )
                    }
                    PasswordRow(value, onChangeValue = { value -> onChangeValue(value) })
                    SetPasswordBox(
                        value.text,
                        isSetPassLoading,
                        setPassword = { newPassword -> viewModel.setPassword(newPassword) })
                } else {
                    EmptyLoginBox { viewModel.getLogin() }
                }
            }
        }
    }
}

@Composable
fun LineDataRow(title: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(title)
        Text(value)
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
    ) {
        Text(
            stringResource(R.string.set_password),
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
            stringResource(R.string.auth_data_is_empty),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        Button(
            onClick = { onRefresh() },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 12.dp)
        ) {
            Text(stringResource(R.string.refresh))
        }
    }
}

@Composable
fun SetPasswordBox(
    pass: String,
    isSetPassLoading: Boolean,
    setPassword: (newPassword: String) -> Unit = {}
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
                onClick = { setPassword(pass) },
                modifier = Modifier.fillMaxWidth(),
                enabled = pass.length > 2,
            ) {
                Text(stringResource(R.string.set_new_password))
            }
        }
    }
}