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

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.madfrog.core.di.ComponentStore
import com.madfrog.core.di.ComponentStoreProvider
import com.madfrog.navigation.NavigationDestination
import com.maximapps.coreui.theme.PineTheme
import com.madfrog.navigation.accompanist.AccompanistNavHost
import com.maximapps.main.navigation.MainRoute
import com.madfrog.pine.di.root.DefaultRootComponent
import javax.inject.Inject

internal class RootActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DefaultRootComponent(this).inject(this)
    }

    @Inject
    fun setContentView(
        store: ComponentStore,
        destinations: Set<@JvmSuppressWildcards NavigationDestination>
    ) = setContent {
        PineTheme {
            ComponentStoreProvider(store) {
                AccompanistNavHost(MainRoute, destinations)
            }
        }
    }
}