package com.madfrog.navigation

data class NavigationCommand(val route: Route, val arguments: List<Any> = emptyList())