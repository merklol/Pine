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

package com.madfrog.pine.di.app.modules

import com.madfrog.core.di.ComponentStore
import com.madfrog.core.di.add
import com.madfrog.usersettings.UserSettingsComponent
import com.madfrog.usersettings.UserSettingsStorage
import com.madfrog.usersettings.UserSettingsStorageDependencies
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
internal object AppModule {

    @[Singleton Provides]
    fun provideUserSettingsStorage(
        dependencies: UserSettingsStorageDependencies,
        componentStore: ComponentStore
    ): UserSettingsStorage =
        componentStore.add { UserSettingsComponent(dependencies) }.userSettingsStorage
}