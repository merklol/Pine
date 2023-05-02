package com.madfrog.navigation

abstract class Route(private val value: String) {
    override fun toString(): String = value
}