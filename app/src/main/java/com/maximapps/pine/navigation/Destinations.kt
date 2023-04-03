package com.maximapps.pine.navigation

import com.madfrog.navigation.toDestination

//TODO: Need to refactor
object Destinations {
    const val BaseRoute = "settings"

    val settingsScreen = "${BaseRoute}/{test}/{test2}".toDestination()
}
