package com.example.authme.network.r3d3

data class R3D3Model(
    val success: Boolean,
    val data: R3D3DataModel,
)

data class R3D3DataModel(
    val login: String,
    val password: String
)