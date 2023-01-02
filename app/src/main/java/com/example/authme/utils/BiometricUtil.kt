package com.example.authme.utils

import android.content.Context
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import com.example.authme.R

object BiometricUtil {
    fun authenticate(
        activity: FragmentActivity,
        context: Context,
        onSuccess: () -> Unit,
    ) {
        val title = context.getString(R.string.biometric_authentication)
        val negativeText = context.getString(R.string.cancel)
        val executor = ContextCompat.getMainExecutor(activity)
        val biometricPrompt = BiometricPrompt(activity, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    onSuccess()
                }
            })
        val promptInfo: BiometricPrompt.PromptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle(title)
            .setNegativeButtonText(negativeText)
            .build()
        biometricPrompt.authenticate(promptInfo)
    }
}

