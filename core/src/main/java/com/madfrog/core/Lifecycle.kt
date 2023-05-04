package com.madfrog.core

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

/**
 * Receives [Lifecycle.Event] on any lifecycle change.
 */
@Composable
fun LifecycleEvent(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    event: Lifecycle.Event.() -> Unit
) {
    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event -> event(event) }
        lifecycleOwner.lifecycle.addObserver(observer)
        onDispose { lifecycleOwner.lifecycle.removeObserver(observer) }
    }
}

fun Lifecycle.Event.onPause(action: () -> Unit) = onEvent(Lifecycle.Event.ON_PAUSE, action)

private fun Lifecycle.Event.onEvent(event: Lifecycle.Event, action: () -> Unit) =
    if (this == event) action() else Unit
