package com.maximapps.settings.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Modifier
import com.madfrog.core.di.LocalComponentStore
import com.madfrog.core.di.daggerViewModel
import com.madfrog.core.di.destroy
import com.maximapps.settings.di.SettingsComponent

@Composable
fun SettingsScreen(
    message: String,
    viewModel: SettingsViewModel = daggerViewModel()
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background,
    ) {
//        val router = LocalRouter.current
//        router.navigate("main")
        Column {
            Text(text = message)
            Text(text = viewModel.getMessage())
        }
    }
    val store = LocalComponentStore.current
    DisposableEffect(Unit) {
        onDispose {
            store.destroy<SettingsComponent>()
        }
    }
}