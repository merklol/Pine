@file:OptIn(ExperimentalAnimationApi::class)

package com.madfrog.navigation.accompanist

import android.os.Bundle
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.madfrog.navigation.Argument
import com.madfrog.navigation.DeepLink
import com.madfrog.navigation.Route
import com.madfrog.navigation.toNavDeepLink

@JvmInline
value class NavigationGraph(private val navGraphBuilder: NavGraphBuilder) {

    fun addDestination(
        route: Route,
        arguments: List<Argument> = emptyList(),
        deepLinks: List<DeepLink> = emptyList(),
        transitions: AccompanistTransitions = EmptyTransitions,
        popTransitions: AccompanistTransitions = EmptyTransitions,
        content: @Composable (Bundle?) -> Unit
    ) {
        navGraphBuilder.composable(
            route = route.toString(),
            arguments.map(Argument::toNavNamedNavArgument),
            deepLinks.map(DeepLink::toNavDeepLink),
            enterTransition = { transitions.enterTransition.determine(this) },
            exitTransition = { transitions.exitTransition.determine(this) },
            popEnterTransition = { popTransitions.enterTransition.determine(this) },
            popExitTransition = { popTransitions.exitTransition.determine(this) },
            content = { content(it.arguments) }
        )
    }
}
