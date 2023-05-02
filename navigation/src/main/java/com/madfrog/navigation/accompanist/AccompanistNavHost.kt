package com.madfrog.navigation.accompanist

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.madfrog.navigation.JetpackNavigationRouter
import com.madfrog.navigation.LocalRouter
import com.madfrog.navigation.NavigationDestination
import com.madfrog.navigation.Route

@Composable
@OptIn(ExperimentalAnimationApi::class)
fun AccompanistNavHost(startRoute: Route, destinations: Set<NavigationDestination>) {
    val navController = rememberAnimatedNavController()

    CompositionLocalProvider(
        LocalRouter provides JetpackNavigationRouter(navController)
    ) {
        val router = LocalRouter.current
        AnimatedNavHost(navController, startRoute.toString()) {
            destinations.forEach { it.create(NavigationGraph(this), router) }
        }
    }
}