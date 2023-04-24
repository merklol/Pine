package com.maximapps.main.di.modules

import androidx.lifecycle.ViewModel
import com.madfrog.core.di.ViewModelKey
import com.maximapps.main.ui.MainViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelsModule {
    //VMs
    //TODO: Move it to another dagger module
    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}
