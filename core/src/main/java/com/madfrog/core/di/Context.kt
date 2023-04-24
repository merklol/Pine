package com.madfrog.core.di

import androidx.lifecycle.ViewModelProvider

interface Context {
    val viewModelFactory: ViewModelProvider.Factory
}