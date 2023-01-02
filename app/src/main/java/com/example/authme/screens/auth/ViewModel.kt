package com.example.authme.screens.auth

import android.content.Context
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModel
import com.example.authme.utils.BiometricUtil

class AuthViewModel : ViewModel() {
    fun callBiometry(
        activity: FragmentActivity,
        context: Context,
        onSuccess: () -> Unit,
    ) {
        BiometricUtil.authenticate(activity, context, onSuccess)
    }
}