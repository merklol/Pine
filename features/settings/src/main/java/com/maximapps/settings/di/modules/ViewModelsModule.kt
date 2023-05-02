package com.maximapps.settings.di.modules

import androidx.lifecycle.ViewModel
import com.madfrog.core.di.ViewModelKey
import com.maximapps.settings.ui.SettingsViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelsModule {
    //VMs
    //TODO: Move it to another dagger module
    @Binds
    @IntoMap
    @ViewModelKey(SettingsViewModel::class)
    fun bindSettingsViewModel(viewModel: SettingsViewModel): ViewModel
}
