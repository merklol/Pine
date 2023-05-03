package com.madfrog.core.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf

/**
 * Class to store Components.
 */
class ComponentStore {
    private val map = mutableMapOf<String, Component>()

    /**
     * Returns [Component] mapped to the given key.
     */
    operator fun get(key: String): Component? = map[key]

    /**
     * Saves a new component under the given key.
     */
    operator fun set(key: String, component: Component) {
        map[key] = component
    }

    /**
     * Removes [Component] mapped to the given key from the store.
     */
    fun remove(key: String) {
        map.remove(key)
    }
}

/**
 * Returns [Component] mapped to the given key or save a new component
 * under the given key and returns it as result.
 */
inline fun <reified T : Component> ComponentStore.getOrElse(defaultValue: () -> T): T =
    with(this[getKey<T>()]) {
        if (T::class.isInstance(this)) this as T else defaultValue()
    }

/**
 * Removes [Component] mapped to the given key from the store.
 */
inline fun <reified T : Component> ComponentStore.remove() = remove(getKey<T>())

/**
 * Returns [Component] key.
 */
inline fun <reified T: Component> getKey() = requireNotNull(T::class.java.canonicalName) {
    "Local and anonymous classes can not be Components"
}

/**
 * Binds the given [ComponentStore] to [LocalComponentStore].
 */
@Composable
fun ComponentStoreProvider(store: ComponentStore, content: @Composable () -> Unit) {
    CompositionLocalProvider(LocalComponentStore provides store, content = content)
}

/**
 * CompositionLocal used to pass [ComponentStore] down the tree.
 */
val LocalComponentStore = compositionLocalOf<ComponentStore>() {
    error("No instances available for ComponentStore.")
}
