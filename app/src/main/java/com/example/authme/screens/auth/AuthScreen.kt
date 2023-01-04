package com.example.authme.screens.auth

import android.content.Context
import android.content.ContextWrapper
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.fragment.app.FragmentActivity
import com.example.authme.R
import com.example.authme.utils.BiometricUtil

@Composable
fun AuthScreen(
    onAuthHandler: () -> Unit = {},
) {
    val context = LocalContext.current
    val activity = context.findActivity()!!

    LaunchedEffect(Unit) {
        callBiometry(
            activity = activity,
            context = context,
            onSuccess = onAuthHandler
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            stringResource(R.string.you_need_to_log_in_with_biometrics),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        Button(
            onClick = {
                callBiometry(
                    activity = activity,
                    context = context,
                    onSuccess = onAuthHandler
                )
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 32.dp)
        ) {
            Text(stringResource(R.string.login), style = MaterialTheme.typography.h5)
        }
    }
}

fun Context.findActivity(): FragmentActivity? = when (this) {
    is FragmentActivity -> this
    is ContextWrapper -> baseContext.findActivity()
    else -> null
}

private fun callBiometry(
    activity: FragmentActivity,
    context: Context,
    onSuccess: () -> Unit,
) {
    BiometricUtil.authenticate(activity, context, onSuccess)
}