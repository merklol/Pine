package com.maximapps.main.di

import com.madfrog.core.di.ComponentHolder
import com.madfrog.core.di.ViewModelFactoryProvider

//TODO: Add an interface and abstract class
object MainComponentHolder : ComponentHolder {
    private var component: Lazy<MainComponent>? = null

    override val viewModelFactoryProvider = ViewModelFactoryProvider {
        checkNotNull(component).value.viewModelFactory
    }

    override fun init() {
        component = lazy(this) {
            DaggerMainComponent.builder().build()
        }

    }

    override fun reset() {
        component = null
    }
}