package com.maximapps.pine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.CompositionLocalProvider
import com.madfrog.core.di.ComponentStore
import com.madfrog.core.di.LocalComponentStore
import com.madfrog.navigation.NavigationDestination
import com.maximapps.coreui.theme.PineTheme
import com.madfrog.navigation.accompanist.AccompanistNavHost
import com.madfrog.navigation.toDestination
import com.maximapps.pine.di.DaggerAppComponent
import javax.inject.Inject

class MainActivity : ComponentActivity() {
    @Inject
    lateinit var factories: Set<@JvmSuppressWildcards NavigationDestination>

    @Inject
    lateinit var componentStore: ComponentStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerAppComponent.builder().build().inject(this)

        setContent {
            PineTheme {
                //TODO: Refactor it
                CompositionLocalProvider(LocalComponentStore provides componentStore) {
                    AccompanistNavHost("main".toDestination(), factories)
                }
            }
        }
    }
}