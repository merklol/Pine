package com.madfrog.navigation

@JvmInline
value class Route(internal val value: String)

fun String.toRoute() = Route(this)