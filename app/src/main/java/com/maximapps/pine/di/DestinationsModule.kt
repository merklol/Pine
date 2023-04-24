package com.maximapps.pine.di

import com.madfrog.navigation.NavigationDestination
import com.maximapps.main.navigation.MainDestination
import com.maximapps.settings.navigation.SettingsDestination
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoSet

@Module
interface DestinationsModule {

    @Binds
    @IntoSet
    fun provideMainDestination(destination: MainDestination): NavigationDestination

    @Binds
    @IntoSet
    fun provideSettingsDestination(destination: SettingsDestination): NavigationDestination
}