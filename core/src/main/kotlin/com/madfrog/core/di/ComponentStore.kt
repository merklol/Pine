/*
 * Copyright 2023 Maxim Smolyakov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
 * Adds a new component to the [ComponentStore] or return [Component] mapped to the given key.
 */
inline fun <reified T : Component> ComponentStore.add(defaultValue: () -> T): T {
    return this.getOrElse { defaultValue().also { this[getKey<T>()] = it } }
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
 * Returns [Component] mapped to the given key or null if a component is not present in the store.
 */
inline fun <reified T : Component> ComponentStore.getOrNull(): T? = this[getKey<T>()] as? T

/**
 * Returns [Component] mapped to the given key.
 */
inline fun <reified T : Component> ComponentStore.get(): T = checkNotNull(this[getKey<T>()] as? T)

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
