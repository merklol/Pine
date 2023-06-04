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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import javax.inject.Inject
import javax.inject.Provider

/**
 * Global implementation of [ViewModelProvider.Factory] interface that
 * is responsible to instantiate Dagger bound ViewModels.
 */
class ViewModelFactory @Inject constructor(
    private val providers: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>
) : ViewModelProvider.Factory {

    @Suppress("unchecked_cast")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val provider = checkNotNull(providers.getOrDefault(modelClass, findProvider(modelClass))) {
            "Unknown model class $modelClass"
        }
        return provider.get() as T
    }

    private fun <T> findProvider(modelClass: Class<T>): @JvmSuppressWildcards Provider<ViewModel>? {
        for ((key, provider) in providers) {
            if (modelClass.isAssignableFrom(key)) {
                return provider
            }
        }
        return null
    }
}

/**
 * Binds the given [ViewModelProvider.Factory] to [LocalViewModelFactory].
 */
@Composable
fun Inject(viewModelFactory: ViewModelProvider.Factory, content: @Composable () -> Unit) {
    CompositionLocalProvider(
        LocalViewModelFactory provides viewModelFactory,
        content = content
    )
}

/**
 * Returns an existing Dagger bound ViewModel or creates a new one scoped to
 * the current navigation graph.
 */
@Composable
inline fun <reified T : ViewModel> daggerViewModel(): T =
    with(LocalViewModelFactory.current) { viewModel { create(T::class.java) } }


/**
 * CompositionLocal used to pass [ViewModelProvider.Factory] down the tree.
 */
val LocalViewModelFactory = compositionLocalOf<ViewModelProvider.Factory>() {
    error("No instances available for ViewModelProvider.Factory.")
}