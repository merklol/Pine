package com.maximapps.main.navigation

import com.madfrog.core.di.Inject
import com.madfrog.core.di.component
import com.madfrog.navigation.NavigationCommand
import com.madfrog.navigation.NavigationDestination
import com.madfrog.navigation.Router
import com.madfrog.navigation.accompanist.NavigationGraph
import com.madfrog.clock.di.DefaultClockComponent
import com.madfrog.clock.di.ClockDependencies
import com.maximapps.main.ui.MainScreen
import javax.inject.Inject

class MainDestination @Inject constructor(
    private val dependencies: ClockDependencies
) : NavigationDestination {
    override fun create(navigationGraph: NavigationGraph, router: Router) {
        navigationGraph.addDestination(
            route = MainRoute,
            deepLinks = listOf(MainDeepLink),
            transitions = ScreenTransitions(),
            popTransitions = ScreenPopTransitions()
        ) {
            val component: DefaultClockComponent = component { DefaultClockComponent(dependencies) }
            Inject(component.viewModelFactory) {
                MainScreen {
                    router.navigate(NavigationCommand(SettingsRoute))
                }
            }
        }
    }
}