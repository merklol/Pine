package com.madfrog.navigation

@JvmInline
value class Destination(internal val value: String)

fun String.toDestination() = Destination(this)