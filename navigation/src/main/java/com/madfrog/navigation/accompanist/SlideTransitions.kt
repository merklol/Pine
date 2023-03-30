package com.madfrog.navigation.accompanist

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween

@OptIn(ExperimentalAnimationApi::class)
fun slideInTransition() = AccompanistEnterTransition {
    it.slideIntoContainer(
        AnimatedContentScope.SlideDirection.Up, animationSpec = tween(1000)
    )
}

@OptIn(ExperimentalAnimationApi::class)
fun slideOutTransition() = AccompanistExitTransition {
    it.slideOutOfContainer(
        AnimatedContentScope.SlideDirection.Down, animationSpec = tween(1000)
    )
}

@OptIn(ExperimentalAnimationApi::class)
fun slideLeftTransition() = AccompanistEnterTransition {
    it.slideIntoContainer(
        AnimatedContentScope.SlideDirection.Left, animationSpec = tween(1000)
    )
}

@OptIn(ExperimentalAnimationApi::class)
fun slideRightTransition() = AccompanistExitTransition {
    it.slideOutOfContainer(
        AnimatedContentScope.SlideDirection.Right, animationSpec = tween(1000)
    )
}

@OptIn(ExperimentalAnimationApi::class)
fun emptyEnterTransition() = AccompanistEnterTransition { EnterTransition.None }

@OptIn(ExperimentalAnimationApi::class)
fun emptyExitTransition() = AccompanistExitTransition {
    ExitTransition.None
}