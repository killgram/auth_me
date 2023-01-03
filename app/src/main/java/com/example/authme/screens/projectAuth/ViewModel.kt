package com.example.authme.screens.projectAuth

import androidx.lifecycle.ViewModel
import com.example.authme.data.ProjectsListData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ProjectViewModel : ViewModel() {
    private val _projectName = MutableStateFlow("")
    private val _projectType = MutableStateFlow(0)
    private val _projectSecKey = MutableStateFlow("")

    val projectName: StateFlow<String>
        get() = _projectName
    val projectType: StateFlow<Int>
        get() = _projectType
    val projectSecKey: StateFlow<String>
        get() = _projectSecKey

    fun extractProjectData(projectType: String) {
        val project = ProjectsListData.find { it.type == projectType.toInt() }!!
        _projectName.value = project.name
        _projectType.value = project.type
        _projectSecKey.value = project.secKey
    }

}