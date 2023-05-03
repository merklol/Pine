package com.madfrog.core.di

import androidx.compose.runtime.Composable

/**
 * Interface to provide a graph of dependencies.
 */
interface Component : Context

/**
 * Adds [Component] to the [ComponentStore].
 */
@Composable
inline fun <reified T : Component> component(defaultValue: () -> T): T {
    val store = LocalComponentStore.current
    return store.getOrElse { defaultValue().also { store[getKey<T>()] = it } }
}