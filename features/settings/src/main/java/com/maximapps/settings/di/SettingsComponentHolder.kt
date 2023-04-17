package com.maximapps.settings.di

import android.util.Log
import com.madfrog.core.di.ComponentHolder
import com.madfrog.core.di.ViewModelFactoryProvider

//TODO: Add an interface and abstract class
object SettingsComponentHolder : ComponentHolder {
    private var component: Lazy<SettingsComponent>? = null

    override val viewModelFactoryProvider = ViewModelFactoryProvider {
        checkNotNull(component).value.viewModelFactory
    }

    override fun init() {
        Log.e("INIT", this::class.simpleName.toString())
        component = lazy(this) {
            DaggerSettingsComponent.builder().build()
        }
    }

    override fun reset() {
        component = null
    }
}
