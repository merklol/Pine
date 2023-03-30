package com.madfrog.navigation

fun interface Transition<in T1, out T2> {
    fun determine(scope: T1): T2
}