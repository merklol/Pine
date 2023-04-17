package com.maximapps.settings.di

import com.madfrog.core.di.ViewModelFactory
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [
        SettingsModule::class,
        ViewModelFactoryModule::class
    ]
)
@Singleton
interface SettingsComponent {
    val viewModelFactory: ViewModelFactory
}
