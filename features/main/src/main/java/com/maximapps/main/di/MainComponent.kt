package com.maximapps.main.di

import com.madfrog.core.di.ViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        MainModule::class,
        ViewModelFactoryModule::class
    ]
)
@Singleton
interface MainComponent {
    val viewModelFactory: ViewModelFactory
}
