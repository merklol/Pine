/*
 * Copyright 2023 Maxim Smolyakov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.madfrog.pine.di.app

import android.content.Context
import com.madfrog.core.di.ComponentStore
import com.madfrog.usersettings.UserSettingsStorage
import com.madfrog.pine.PineApp
import com.madfrog.pine.api.AppComponent
import com.madfrog.pine.di.root.RootDependencies
import com.madfrog.pine.di.app.modules.AppModule
import com.madfrog.pine.di.app.modules.ComponentStoreModule
import com.madfrog.pine.di.app.modules.DependenciesModule
import com.madfrog.pine.di.app.modules.InitializationModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        DependenciesModule::class,
        ComponentStoreModule::class,
        InitializationModule::class
    ]
)
internal interface DefaultAppComponent : AppComponent, RootDependencies {
    override val componentStore: ComponentStore
    override val userSettingsStorage: UserSettingsStorage

    @Component.Builder
    interface Builder {
        fun build(): DefaultAppComponent

        @BindsInstance
        fun applicationContext(context: Context): Builder
    }

    fun inject(app: PineApp)
}

internal fun DefaultAppComponent(context: Context): DefaultAppComponent =
    DaggerDefaultAppComponent.builder().applicationContext(context).build()