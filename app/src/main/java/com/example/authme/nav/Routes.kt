package com.example.authme.nav

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
}

