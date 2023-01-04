package com.example.authme.screens.projectAuth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.authme.data.ProjectsListData
import com.example.authme.network.SetPasswordRequestModel
import com.example.authme.network.r3d3.R3D3Endpoints
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProjectViewModel : ViewModel() {
    private val _projectName = MutableStateFlow("")
    private val _projectSecKey = MutableStateFlow("")
    private val _isLoading = MutableStateFlow(false)
    private val _login = MutableStateFlow("")
    private val _password = MutableStateFlow("")
    private val _projectType = MutableStateFlow(0)
    private val _isSetPassLoading = MutableStateFlow(false)

    val projectName: StateFlow<String>
        get() = _projectName
    val isLoading: StateFlow<Boolean>
        get() = _isLoading
    val login: StateFlow<String>
        get() = _login
    val password: StateFlow<String>
        get() = _password
    val isSetPassLoading: StateFlow<Boolean>
        get() = _isSetPassLoading

    fun extractProjectData(projectType: String) {
        val project = ProjectsListData.find { it.type == projectType.toInt() }!!
        _projectName.value = project.name
        _projectSecKey.value = project.secKey
        _projectType.value = project.type
        getLogin()
    }

    fun getLogin() {
        _isLoading.value = true
        viewModelScope.launch {
            val result = when (_projectType.value) {
                0 -> R3D3Endpoints.getLoginEndpoint.getLogin(_projectSecKey.value)
                else -> R3D3Endpoints.getLoginEndpoint.getLogin(_projectSecKey.value)
            }
            if (result.isSuccessful) {
                _login.value = result.body()!!.data.login
                _password.value = result.body()!!.data.password
                _isLoading.value = false
            }
        }
    }

    fun setPassword(newPassword: String) {
        _isSetPassLoading.value = true
        viewModelScope.launch {
            val result = when (_projectType.value) {
                0 -> R3D3Endpoints.setPasswordEndpoint.setPassword(
                    data = SetPasswordRequestModel(
                        _projectSecKey.value,
                        newPassword
                    )
                )
                else -> R3D3Endpoints.setPasswordEndpoint.setPassword(
                    data = SetPasswordRequestModel(
                        _projectSecKey.value,
                        newPassword
                    )
                )
            }
            if (result.isSuccessful) {
                _isSetPassLoading.value = false
                _password.value = newPassword
            }
        }
    }
}