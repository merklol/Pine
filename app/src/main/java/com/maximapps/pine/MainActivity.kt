package com.maximapps.pine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.madfrog.core.di.ComponentStore
import com.madfrog.core.di.ComponentStoreProvider
import com.madfrog.navigation.NavigationDestination
import com.maximapps.coreui.theme.PineTheme
import com.madfrog.navigation.accompanist.AccompanistNavHost
import com.maximapps.main.navigation.MainRoute
import com.maximapps.pine.di.DaggerAppComponent
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DaggerAppComponent.builder().build().inject(this)
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