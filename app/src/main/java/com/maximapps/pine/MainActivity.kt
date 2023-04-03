package com.maximapps.pine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.maximapps.coreui.theme.PineTheme
import com.madfrog.navigation.accompanist.AccompanistNavHost
import com.madfrog.navigation.toDestination
import com.maximapps.main.navigation.MainScreenDestinationFactory
import com.maximapps.settings.navigation.SettingsScreenDestinationFactory

class MainActivity : ComponentActivity() {
    private val factories = setOf(
        MainScreenDestinationFactory(),
        SettingsScreenDestinationFactory()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PineTheme {
                AccompanistNavHost("main".toDestination(), factories)
            }
        }
    }
}