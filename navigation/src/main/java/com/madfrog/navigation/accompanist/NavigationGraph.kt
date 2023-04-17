@file:OptIn(ExperimentalAnimationApi::class)

package com.madfrog.navigation.accompanist

import android.os.Bundle
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import com.google.accompanist.navigation.animation.composable
import com.madfrog.navigation.Argument
import com.madfrog.navigation.Destination

@JvmInline
value class NavigationGraph(private val navGraphBuilder: NavGraphBuilder) {

    fun addDestination(
        destination: Destination,
        arguments: List<Argument> = emptyList(),
        transitions: AccompanistTransitions = EmptyTransitions,
        popTransitions: AccompanistTransitions = EmptyTransitions,
        content: @Composable (Bundle?) -> Unit
    ) {
        navGraphBuilder.composable(
            route = destination.value,
            arguments.map(Argument::toNavNamedNavArgument),
            enterTransition = { transitions.enterTransition.determine(this) },
            exitTransition = { transitions.exitTransition.determine(this) },
            popEnterTransition = { popTransitions.enterTransition.determine(this) },
            popExitTransition = { popTransitions.exitTransition.determine(this) },
            content = { content(it.arguments) }
        )
    }
}
