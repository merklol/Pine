package com.madfrog.navigation.accompanist

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.madfrog.navigation.Destination
import com.madfrog.navigation.JetpackNavigationRouter
import com.madfrog.navigation.LocalRouter
import com.madfrog.navigation.NavigationDestinationFactory

@Composable
@OptIn(ExperimentalAnimationApi::class)
fun AccompanistNavHost(
    startDestination: Destination,
    destinations: Set<NavigationDestinationFactory>
) {
    val navController = rememberAnimatedNavController()

    CompositionLocalProvider(
        LocalRouter provides remember { JetpackNavigationRouter(navController) }
    ) {
        val router = LocalRouter.current
        AnimatedNavHost(navController, startDestination.value) {
            destinations.forEach { it.create(NavigationGraph(this), router) }
        }
    }
}