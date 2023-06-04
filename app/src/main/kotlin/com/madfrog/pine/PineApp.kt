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

package com.madfrog.pine

import android.app.Application
import com.madfrog.core.di.ComponentStore
import com.madfrog.core.di.ComponentStoreProvider
import com.madfrog.core.di.OnInitialization
import com.madfrog.core.di.add
import com.madfrog.pine.di.app.DefaultAppComponent
import javax.inject.Inject

internal class PineApp : Application(), ComponentStoreProvider {
    @Inject
    lateinit var componentStore: ComponentStore
    private val appComponent by lazy {
        DefaultAppComponent(context = applicationContext).apply {
            inject(this@PineApp)
            componentStore.add { this }
        }
    }

    override fun get(): ComponentStore = appComponent.componentStore

    @Inject
    fun launchInitializers(initializers: @JvmSuppressWildcards Set<OnInitialization>) {
        initializers.forEach { initializer -> initializer() }
    }
}