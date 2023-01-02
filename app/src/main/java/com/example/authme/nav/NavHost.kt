package com.example.authme.nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.authme.screens.auth.AuthScreen
import com.example.authme.screens.projectAuth.ProjectAuthScreen
import com.example.authme.screens.projectsList.ProjectsListScreen

@Composable
fun NavigationHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = AuthScreen.route,
        modifier = modifier
    ) {
        composable(route = AuthScreen.route) {
            AuthScreen()
        }
        composable(route = ProjectsList.route) {
            ProjectsListScreen()
        }
        composable(route = ProjectAuth.route) {
            ProjectAuthScreen()
        }
    }

}