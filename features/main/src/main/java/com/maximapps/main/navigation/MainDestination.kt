package com.maximapps.main.navigation

import com.madfrog.core.di.Inject
import com.madfrog.core.di.component
import com.madfrog.navigation.NavigationCommand
import com.madfrog.navigation.NavigationDestinationFactory
import com.madfrog.navigation.Router
import com.madfrog.navigation.accompanist.NavigationGraph
import com.madfrog.navigation.toRoute
import com.maximapps.main.di.MainComponent
import com.maximapps.main.di.MainComponentFactory
import com.maximapps.main.di.MainDependencies
import com.maximapps.main.ui.MainScreen
import javax.inject.Inject

class MainDestinationFactory @Inject constructor(
    private val dependencies: MainDependencies
) : NavigationDestinationFactory {
    override fun create(navigationGraph: NavigationGraph, router: Router) {
        navigationGraph.addDestination(
            destination = Destinations.mainScreen,
            transitions = ScreenTransitions(),
            popTransitions = ScreenPopTransitions()
        ) {
            val component: MainComponent = component(MainComponentFactory(dependencies))
            Inject(component.viewModelFactory) {
                MainScreen {
                    router.navigate(NavigationCommand("settings".toRoute()))
                }
            }

            //TODO: Remove it
//            MainScreen(
//                onClick = { firstParameter, secondParameter ->
//                    val parameters = listOf(firstParameter, secondParameter)
//                    router.navigate(NavigationCommand(Destinations.BaseRoute.toRoute(), parameters))
//                }
//            )
        }
    }
}