package com.maximapps.pine.di

import com.madfrog.core.di.ComponentStore
import com.madfrog.core.di.ViewModelFactory
import com.madfrog.core.di.getOrElse
import com.madfrog.core.usersettings.UserSettings
import com.maximapps.main.di.MainComponent
import com.maximapps.main.di.MainDependencies
import com.maximapps.settings.di.SettingsDependencies
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    fun provideMainDependencies(userSettings: UserSettings): MainDependencies =
        object : MainDependencies {
            override val userSettings: UserSettings = userSettings
        }

    @Provides
    fun provideSettingsDependencies(): SettingsDependencies = object : SettingsDependencies {}

    @Provides
    //TODO: should be custom scope and replaced with @Binds
    // Probably should be in another module
    @Singleton
    fun provideUserSettings(): UserSettings = UserSettings()

    @Provides
    @Singleton
    fun provideComponentStore(): ComponentStore = ComponentStore()

}