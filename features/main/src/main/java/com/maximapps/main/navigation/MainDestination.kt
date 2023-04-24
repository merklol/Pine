package com.maximapps.main.navigation

import com.madfrog.core.di.Inject
import com.madfrog.core.di.component
import com.madfrog.navigation.NavigationCommand
import com.madfrog.navigation.NavigationDestination
import com.madfrog.navigation.Router
import com.madfrog.navigation.accompanist.NavigationGraph
import com.madfrog.navigation.toRoute
import com.maximapps.main.di.MainComponent
import com.maximapps.main.di.MainDependencies
import com.maximapps.main.ui.MainScreen
import javax.inject.Inject

class MainDestination @Inject constructor(
    private val dependencies: MainDependencies
) : NavigationDestination {
    override fun create(navigationGraph: NavigationGraph, router: Router) {
        navigationGraph.addDestination(
            destination = Destinations.mainScreen,
            transitions = ScreenTransitions(),
            popTransitions = ScreenPopTransitions()
        ) {
            val component: MainComponent = component { MainComponent.create(dependencies) }
            Inject(component.viewModelFactory) {
                MainScreen {
                    router.navigate(NavigationCommand("settings".toRoute()))
                }
            }
        }
    }
}