package com.example.authme.data

import androidx.compose.runtime.Immutable

@Immutable
data class ProjectsListModel(
    val type: Int,
    val name: String,
    val secKey: String
)

val ProjectsListData = listOf(
    ProjectsListModel(0, "R3D3", R3D3key.secKey),
)
