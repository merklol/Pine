package com.maximapps.main

enum class CountdownTimerState {
    Idle, Work, ShortBreak, LongBreak
}

data class TimerState(
    val countdownTimerState: CountdownTimerState = CountdownTimerState.Work,
    val remainingTime: Long,
    val totalTime: Long,
    val currentStep: Int,
)