package com.maximapps.settings.navigation

import com.madfrog.core.di.Inject
import com.madfrog.core.di.component
import com.madfrog.navigation.NavigationDestination
import com.madfrog.navigation.Router
import com.madfrog.navigation.accompanist.NavigationGraph
import com.maximapps.settings.di.SettingsComponent
import com.maximapps.settings.di.SettingsDependencies
import com.maximapps.settings.ui.SettingsScreen
import javax.inject.Inject

class SettingsDestination @Inject constructor(
    private val dependencies: SettingsDependencies
) : NavigationDestination {
    override fun create(navigationGraph: NavigationGraph, router: Router) {
        navigationGraph.addDestination(
            route = SettingsRoute,
            transitions = SettingsScreenTransitions()
        ) {
            val component: SettingsComponent = component { SettingsComponent.create(dependencies) }
            Inject(component.viewModelFactory) {
                SettingsScreen("Settings")
            }
        }
    }
}