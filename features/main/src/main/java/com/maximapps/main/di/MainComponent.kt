package com.maximapps.main.di

import com.madfrog.core.di.Component
import com.madfrog.core.di.ViewModelFactory
import com.maximapps.main.di.modules.MainModule
import com.maximapps.main.di.modules.ViewModelsModule
import javax.inject.Singleton

@dagger.Component(
    dependencies = [MainDependencies::class],
    modules = [
        MainModule::class,
        ViewModelsModule::class
    ]
)
@Singleton
interface MainComponent : Component {
    override val viewModelFactory: ViewModelFactory

    companion object {
        fun create(dependencies: MainDependencies): MainComponent =
            DaggerMainComponent.builder().mainDependencies(dependencies).build()
    }
}
