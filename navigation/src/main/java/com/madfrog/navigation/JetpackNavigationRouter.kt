package com.madfrog.navigation

import androidx.navigation.NavController

private const val Separator = "/"

@JvmInline
internal value class JetpackNavigationRouter(private val navController: NavController) : Router {

    override fun navigate(navigationCommand: NavigationCommand) {
        if (navigationCommand.arguments.isNotEmpty()) {
            val args = navigationCommand.arguments.joinToString(separator = Separator)
            navController.navigate("${navigationCommand.route.value}/$args")
        } else {
            navController.navigate(navigationCommand.route.value)
        }
    }
}