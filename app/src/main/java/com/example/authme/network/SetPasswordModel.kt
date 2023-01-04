package com.example.authme.network

data class SetPasswordModel(
    val success: Boolean,
    val title: String
)

data class SetPasswordRequestModel(
    val key: String,
    val password: String
)