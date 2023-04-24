package com.maximapps.settings.di

import android.util.Log
import com.madfrog.core.di.Component
import com.madfrog.core.di.ViewModelFactory
import com.maximapps.settings.di.modules.SettingsModule
import com.maximapps.settings.di.modules.ViewModelsModule
import javax.inject.Singleton

@dagger.Component(
    dependencies = [SettingsDependencies::class],
    modules = [
        SettingsModule::class,
        ViewModelsModule::class
    ]
)
@Singleton
interface SettingsComponent : Component {
    override val viewModelFactory: ViewModelFactory

    companion object {
        fun create(dependencies: SettingsDependencies): SettingsComponent =
            DaggerSettingsComponent.builder().settingsDependencies(dependencies).build().also {
                Log.e("INIT", "2")
            }
    }
}
