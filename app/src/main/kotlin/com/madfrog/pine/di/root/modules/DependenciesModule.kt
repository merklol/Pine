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

package com.madfrog.pine.di.root.modules

import com.madfrog.usersettings.UserSettingsStorage
import com.madfrog.clock.di.ClockDependencies
import com.madfrog.pine.di.root.RootScope
import com.maximapps.settings.di.SettingsDependencies
import dagger.Module
import dagger.Provides

@Module
internal object DependenciesModule {

    @[RootScope Provides]
    fun provideSettingsDependencies() = object : SettingsDependencies {}

    @[RootScope Provides]
    fun provideClockDependencies(
        userSettingsStorage: UserSettingsStorage
    ) = object : ClockDependencies {
        override val userSettingsStorage = userSettingsStorage
    }
}