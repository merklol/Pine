package com.maximapps.main.navigation

import androidx.compose.runtime.remember
import com.madfrog.core.di.Inject
import com.madfrog.navigation.NavigationCommand
import com.madfrog.navigation.NavigationDestinationFactory
import com.madfrog.navigation.Router
import com.madfrog.navigation.accompanist.NavigationGraph
import com.madfrog.navigation.toRoute
import com.maximapps.main.di.MainComponentHolder
import com.maximapps.main.ui.MainScreen

class MainScreenDestinationFactory : NavigationDestinationFactory {
    override fun create(navigationGraph: NavigationGraph, router: Router) {
        navigationGraph.addDestination(
            destination = Destinations.mainScreen,
            transitions = ScreenTransitions(),
            popTransitions = ScreenPopTransitions()
        ) {
            val factory = remember {
                MainComponentHolder.init()
                MainComponentHolder.viewModelFactoryProvider.get()
            }
            Inject(factory) {
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