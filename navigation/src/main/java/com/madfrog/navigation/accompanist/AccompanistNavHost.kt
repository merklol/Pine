package com.madfrog.navigation.accompanist

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.madfrog.navigation.Destination
import com.madfrog.navigation.JetpackNavigationRouter
import com.madfrog.navigation.LocalRouter
import com.madfrog.navigation.NavigationDestination

@Composable
@OptIn(ExperimentalAnimationApi::class)
fun AccompanistNavHost(startDestination: Destination, destinations: Set<NavigationDestination>) {
    val navController = rememberAnimatedNavController()

    CompositionLocalProvider(
        LocalRouter provides JetpackNavigationRouter(navController)
    ) {
        val router = LocalRouter.current
        AnimatedNavHost(navController, startDestination.value) {
            destinations.forEach { it.create(NavigationGraph(this), router) }
        }
    }
}