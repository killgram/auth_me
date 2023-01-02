package com.example.authme.nav

import androidx.navigation.NavType
import androidx.navigation.navArgument

interface Routes {
    val route: String
}

object AuthScreen : Routes {
    override val route: String
        get() = "auth"
}

object ProjectsList : Routes {
    override val route: String
        get() = "projects_list"
}

object ProjectAuth : Routes {
    override val route: String
        get() = "project_auth"
    const val projectTypeArg = 0
    val routeWithArgs = "${route}/{${projectTypeArg}}"
    val arguments = listOf(
        navArgument(projectTypeArg.toString()) { type = NavType.StringType }
    )
}

