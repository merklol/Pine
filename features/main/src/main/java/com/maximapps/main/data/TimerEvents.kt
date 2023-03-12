package com.maximapps.main.data

sealed class TimerEvents {
    object Idle : TimerEvents()
    object Stopped : TimerEvents()
    data class Elapsed(val value: Long) : TimerEvents()
}