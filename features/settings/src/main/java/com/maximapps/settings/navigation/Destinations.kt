package com.maximapps.settings.navigation

import com.madfrog.navigation.toDestination

//TODO: Need to refactor
object Destinations {
    private const val BaseRoute = "settings"

    val settingsScreen = BaseRoute.toDestination()
}
