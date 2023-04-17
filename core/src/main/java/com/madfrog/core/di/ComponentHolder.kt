package com.madfrog.core.di

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.lifecycle.ViewModelProvider

fun interface ViewModelFactoryProvider {
    fun get(): ViewModelFactory
}

interface ComponentHolder {
    val viewModelFactoryProvider: ViewModelFactoryProvider
    fun init()
    fun reset()
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
