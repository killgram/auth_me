package com.example.authme.screens.projectAuth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authme.data.ProjectsListData
import com.example.authme.network.r3d3.getLoginEndpoint
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProjectViewModel : ViewModel() {
    private val _projectName = MutableStateFlow("")
    private val _projectSecKey = MutableStateFlow("")
    private val _isLoading = MutableStateFlow(false)
    private val _login = MutableStateFlow("")

    val projectName: StateFlow<String>
        get() = _projectName
    val isLoading: StateFlow<Boolean>
        get() = _isLoading
    val login: StateFlow<String>
        get() = _login

    fun extractProjectData(projectType: String) {
        val project = ProjectsListData.find { it.type == projectType.toInt() }!!
        _projectName.value = project.name
        _projectSecKey.value = project.secKey
        getLogin()
    }

    fun getLogin() {
        _isLoading.value = true
        viewModelScope.launch {
            val result = getLoginEndpoint.getLogin(_projectSecKey.value)
            if (result.isSuccessful) {
                _login.value = result.body()!!.data.login
                _isLoading.value = false
            }
        }
    }

}