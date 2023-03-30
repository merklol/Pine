package com.madfrog.navigation

import androidx.compose.runtime.compositionLocalOf

interface Router {
    fun navigate(navigationCommand: NavigationCommand)
}

//TODO: Add a better error message and move it to another file
val LocalRouter = compositionLocalOf<Router>() { error("Router error") }