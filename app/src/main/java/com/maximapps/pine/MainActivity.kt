package com.maximapps.pine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.maximapps.coreui.theme.PineTheme
import com.maximapps.pine.navigation.MainScreenDestinationFactory
import com.maximapps.pine.navigation.SettingsScreenDestinationFactory
import com.madfrog.navigation.accompanist.AccompanistNavHost
import com.maximapps.pine.navigation.Destinations

class MainActivity : ComponentActivity() {
    private val factories = setOf(
        MainScreenDestinationFactory(),
        SettingsScreenDestinationFactory()
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PineTheme {
                AccompanistNavHost(Destinations.mainScreen, factories)
            }
        }
    }
}