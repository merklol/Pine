package com.madfrog.navigation.accompanist

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavBackStackEntry
import com.madfrog.navigation.Transitions

@OptIn(ExperimentalAnimationApi::class)
abstract class AccompanistTransitions(
    override val enterTransition: AccompanistEnterTransition,
    override val exitTransition: AccompanistExitTransition
    ) : Transitions<AnimatedContentScope<NavBackStackEntry>, EnterTransition, ExitTransition>

object EmptyTransitions : AccompanistTransitions(emptyEnterTransition(), emptyExitTransition())