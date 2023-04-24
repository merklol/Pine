package com.madfrog.core.di

import androidx.compose.runtime.Composable

interface Component : Context

@Composable
inline fun <reified T : Component> component(defaultValue: () -> T): T {
    val store = LocalComponentStore.current
    val key = getKey(T::class.java)
    val component = store[key]

    return when {
        T::class.isInstance(component) -> component as T
        else -> defaultValue().also { store[key] = it }
    }
}

fun <T> getKey(clazz: Class<T>) =
    requireNotNull(clazz.canonicalName) { "Local and anonymous classes can not be Components" }
