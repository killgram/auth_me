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
        startDestination = ProjectsList.route,
        modifier = modifier
    ) {
        composable(route = AuthScreen.route) {
            AuthScreen(
                onAuthHandler = {
                    navController.navigateAndReset(ProjectsList.route)
                }
            )
        }
        composable(route = ProjectsList.route) {
            ProjectsListScreen(
                onProjectHandler = { projectType ->
                    navController.navigateToProjectAuth(projectType)
                }
            )
        }
        composable(
            route = ProjectAuth.routeWithArgs,
            arguments = ProjectAuth.arguments
        ) { navBackStackEntry ->
            val projectTypeArg =
                navBackStackEntry.arguments?.getString(ProjectAuth.projectTypeArg.toString())
            ProjectAuthScreen(
                projectTypeArg.toString(),
                onBack = {
                    navController.navigateAndReset(ProjectsList.route)
                }
            )
        }
    }

}

fun NavHostController.navigateSingleTopTo(route: String) =
    this.navigate(route) { launchSingleTop = true }

fun NavHostController.navigateAndReset(route: String) {
    navigate(route = route) {
        popUpTo(graph.startDestinationId) { inclusive = true }
    }
    graph.setStartDestination(route)
}

private fun NavHostController.navigateToProjectAuth(projectType: Int) {
    this.navigateSingleTopTo("${ProjectAuth.route}/$projectType")
}