package com.madfrog.navigation

interface Transitions<in T1, out T2, out T3> {
    val enterTransition: Transition<T1, T2>
    val exitTransition: Transition<T1, T3>
}