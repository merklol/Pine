package com.maximapps.pine.navigation

import com.madfrog.navigation.NavigationDestinationFactory
import com.madfrog.navigation.Router
import com.madfrog.navigation.accompanist.NavigationGraph
import com.madfrog.navigation.stringArgument
import com.maximapps.pine.ui.SettingsScreen

class SettingsScreenDestinationFactory : NavigationDestinationFactory {
    override fun create(navigationGraph: NavigationGraph, router: Router) {
        navigationGraph.addDestination(
            destination = Destinations.settingsScreen,
            listOf(stringArgument("test")),
            transitions = SettingsScreenTransitions()
        ) {
            val first = it?.getString("test") ?: ""
            val second = it?.getString("test2") ?: ""
            SettingsScreen("$first $second")
        }
    }
}