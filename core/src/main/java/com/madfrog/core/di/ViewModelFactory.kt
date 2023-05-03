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