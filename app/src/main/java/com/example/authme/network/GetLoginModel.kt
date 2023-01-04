package com.example.authme.network

data class GetLoginModel(
    val success: Boolean,
    val data: GetLoginDataModel,
)

data class GetLoginDataModel(
    val login: String,
    val password: String
)