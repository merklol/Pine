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

/**
 * Interface to provide a graph of dependencies.
 */
interface Component

/**
 * Context aware interface to provide a graph of dependencies.
 */
interface ContextAwareComponent : Component, Context

/**
 * Adds [Component] to the [ComponentStore].
 */
@Composable
inline fun <reified T : Component> component(defaultValue: () -> T): T {
    val store = LocalComponentStore.current
    return store.getOrElse { defaultValue().also { store[getKey<T>()] = it } }
}