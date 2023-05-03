package com.madfrog.core.di

import androidx.lifecycle.ViewModelProvider

/**
 * Interface that allows access to component-specific resources and classes.
 */
interface Context {
    /**
     * Returns a [ViewModelProvider.Factory] instance for the component.
     */
    val viewModelFactory: ViewModelProvider.Factory
}