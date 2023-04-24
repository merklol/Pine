package com.madfrog.core.di

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

    fun destroy(key: String) {
        map.remove(key)
    }
}

inline fun <reified T : Component> ComponentStore.destroy() = destroy(getKey(T::class.java))
