package com.madfrog.core.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.compositionLocalOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import javax.inject.Inject
import javax.inject.Provider

//TODO: ADD docs
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

//TODO: ADD docs
@Composable
inline fun <reified T : ViewModel> daggerViewModel(): T =
    with(LocalViewModelFactory.current) { viewModel { create(T::class.java) } }


//TODO: ADD docs
val LocalViewModelFactory = compositionLocalOf<ViewModelProvider.Factory>() {
    error("No ViewModelFactory was provided via LocalViewModelFactory")
}
