package com.maximapps.pine.di

import com.maximapps.pine.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AppModule::class, DestinationsModule::class]
)
interface AppComponent {

    fun inject(activity: MainActivity)
}