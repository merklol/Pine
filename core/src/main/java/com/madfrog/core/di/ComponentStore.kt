package com.madfrog.core.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf

//TODO: Update docs
/**
 * Class to store `LibraryComponent`s.
 */
class ComponentStore {
    private val map = mutableMapOf<String, Component>()

    /**
     * Returns the `Component` mapped to the given `key` or save a new component
     * under the given key and returns it as result.
     */
    operator fun get(key: String): Component? = map[key]

    /**
     * Returns the `Component` mapped to the given `key` or save a new component
     * under the given key and returns it as result.
     */
    operator fun set(key: String, component: Component) {
        map[key] = component
    }

    fun remove(key: String) {
        map.remove(key)
    }
}

inline fun <reified T : Component> ComponentStore.remove() = remove(getKey(T::class.java))

inline fun <reified T : Component> ComponentStore.getOrElse(defaultValue: () -> T): T =
    with(this[getKey(T::class.java)]) {
        if(T::class.isInstance(this)) this as T else defaultValue()
    }

@Composable
fun ComponentStoreProvider(store: ComponentStore, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalComponentStore provides store, content = content)
}

//TODO: ADD docs
val LocalComponentStore = compositionLocalOf<ComponentStore>() {
    error("No Component was provided via ComponentStore")
}
