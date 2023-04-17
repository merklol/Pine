package com.maximapps.settings.navigation

import com.madfrog.core.di.Inject
import com.madfrog.navigation.NavigationDestinationFactory
import com.madfrog.navigation.Router
import com.madfrog.navigation.accompanist.NavigationGraph
import com.maximapps.settings.di.SettingsComponentHolder
import com.maximapps.settings.ui.SettingsScreen

class SettingsScreenDestinationFactory : NavigationDestinationFactory {
    override fun create(navigationGraph: NavigationGraph, router: Router) {
        SettingsComponentHolder.init()

        navigationGraph.addDestination(
            destination = Destinations.settingsScreen,
            transitions = SettingsScreenTransitions()
        ) {
            Inject(SettingsComponentHolder.viewModelFactoryProvider.get()) {
                SettingsScreen("Settings")
            }
        }
    }
}