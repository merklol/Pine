package com.madfrog.navigation

import com.madfrog.navigation.accompanist.NavigationGraph

fun interface NavigationDestination {
    fun create(navigationGraph: NavigationGraph, router: Router)
}