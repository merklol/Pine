package com.madfrog.navigation

import com.madfrog.navigation.accompanist.NavigationGraph

fun interface NavigationDestinationFactory {
    fun create(navigationGraph: NavigationGraph, router: Router)
}