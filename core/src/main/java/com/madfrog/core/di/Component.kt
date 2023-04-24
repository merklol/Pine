package com.madfrog.core.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.lifecycle.ViewModelProvider

fun interface ViewModelFactoryProvider {
    fun get(): ViewModelFactory
}

//TODO: Consider to convert it to ComponentContext/Context, ComponentState
interface Module {
    val viewModelFactoryProvider: ViewModelFactoryProvider
    fun create(): Module
}

@Composable
fun Inject(
    viewModelFactory: ViewModelProvider.Factory,
    content: @Composable () -> Unit
) {
    CompositionLocalProvider(
        LocalViewModelFactory provides viewModelFactory,
        content = content
    )
}
