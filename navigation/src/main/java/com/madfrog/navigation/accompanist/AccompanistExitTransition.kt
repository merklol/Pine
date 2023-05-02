package com.madfrog.navigation.accompanist

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavBackStackEntry
import com.madfrog.navigation.Transition

@OptIn(ExperimentalAnimationApi::class)
fun interface AccompanistExitTransition :
    Transition<AnimatedContentScope<NavBackStackEntry>, ExitTransition>