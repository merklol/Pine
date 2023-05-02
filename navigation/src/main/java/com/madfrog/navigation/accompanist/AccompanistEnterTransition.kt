package com.madfrog.navigation.accompanist

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavBackStackEntry
import com.madfrog.navigation.Transition

@OptIn(ExperimentalAnimationApi::class)
fun interface AccompanistEnterTransition :
    Transition<AnimatedContentScope<NavBackStackEntry>, EnterTransition>
