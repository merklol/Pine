package com.madfrog.main.di

import com.madfrog.core.di.ViewModelFactory
import com.madfrog.core.di.ContextAwareComponent
import com.maximapps.main.di.DaggerMainComponent
import com.maximapps.main.di.MainDependencies
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
interface DefaultClockComponent : ContextAwareComponent {
    override val viewModelFactory: ViewModelFactory

    companion object {
        fun create(dependencies: MainDependencies): DefaultClockComponent =
            DaggerMainComponent.builder().mainDependencies(dependencies).build()
    }
}
