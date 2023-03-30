package com.maximapps.pine.navigation

import com.madfrog.navigation.toDestination

object Destinations {
    const val BaseRoute = "settings"

    val mainScreen = "main".toDestination()
    val settingsScreen = "${BaseRoute}/{test}/{test2}".toDestination()
}
