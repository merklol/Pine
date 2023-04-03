package com.maximapps.main.navigation

import com.madfrog.navigation.NavigationCommand
import com.madfrog.navigation.NavigationDestinationFactory
import com.madfrog.navigation.Router
import com.madfrog.navigation.accompanist.NavigationGraph
import com.madfrog.navigation.toDestination
import com.madfrog.navigation.toRoute
import com.maximapps.main.ui.MainScreen

class MainScreenDestinationFactory : NavigationDestinationFactory {
    override fun create(navigationGraph: NavigationGraph, router: Router) {

        navigationGraph.addDestination(
            destination = Destinations.mainScreen,
            transitions = ScreenTransitions(),
            popTransitions = ScreenPopTransitions()
        ) {
            MainScreen {
                router.navigate(NavigationCommand("settings".toRoute()))
            }

//            MainScreen(
//                onClick = { firstParameter, secondParameter ->
//                    val parameters = listOf(firstParameter, secondParameter)
//                    router.navigate(NavigationCommand(Destinations.BaseRoute.toRoute(), parameters))
//                }
//            )
        }
    }
}