package com.maximapps.settings.navigation

import com.madfrog.core.di.Inject
import com.madfrog.core.di.component
import com.madfrog.navigation.NavigationDestinationFactory
import com.madfrog.navigation.Router
import com.madfrog.navigation.accompanist.NavigationGraph
import com.maximapps.settings.di.SettingsComponent
import com.maximapps.settings.di.SettingsComponentFactory
import com.maximapps.settings.di.SettingsDependencies
import com.maximapps.settings.ui.SettingsScreen
import javax.inject.Inject

class SettingsDestinationFactory @Inject constructor() : NavigationDestinationFactory {
    override fun create(navigationGraph: NavigationGraph, router: Router) {
        navigationGraph.addDestination(
            destination = Destinations.settingsScreen,
            transitions = SettingsScreenTransitions()
        ) {
            val component: SettingsComponent =
                component(SettingsComponentFactory(object : SettingsDependencies {}))
            Inject(component.viewModelFactory) {
                SettingsScreen("Settings")
            }
        }
    }
}