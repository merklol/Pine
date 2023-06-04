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

package com.madfrog.pine.di.root

import android.content.Context
import com.madfrog.core.di.findComponentStore
import com.madfrog.core.di.get
import com.madfrog.pine.api.RootComponent
import com.madfrog.pine.di.app.DefaultAppComponent
import com.madfrog.pine.di.root.modules.DependenciesModule
import com.madfrog.pine.di.root.modules.DestinationsModule
import com.madfrog.pine.di.root.modules.RootModule
import com.madfrog.pine.RootActivity

@RootScope
@dagger.Component(
    modules = [
        RootModule::class,
        DestinationsModule::class,
        DependenciesModule::class,
    ],
    dependencies = [RootDependencies::class]
)
internal interface DefaultRootComponent : RootComponent {
    fun inject(activity: RootActivity)
}

internal fun DefaultRootComponent(context: Context): DefaultRootComponent =
    DaggerDefaultRootComponent.builder()
        .rootDependencies(context.findComponentStore().get<DefaultAppComponent>())
        .build()