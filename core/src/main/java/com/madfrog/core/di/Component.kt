package com.madfrog.core.di

import androidx.compose.runtime.Composable

interface Component : Context

@Composable
inline fun <reified T : Component> component(defaultValue: () -> T): T {
    val store = LocalComponentStore.current
    return store.getOrElse { defaultValue().also { store[getKey(T::class.java)] = it } }
}

fun <T> getKey(clazz: Class<T>) =
    requireNotNull(clazz.canonicalName) { "Local and anonymous classes can not be Components" }
