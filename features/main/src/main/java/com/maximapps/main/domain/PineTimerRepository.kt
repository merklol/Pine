package com.maximapps.main.domain

import com.madfrog.core.usersettings.UserSettings
import com.maximapps.main.data.PineCountdownTimer
import com.maximapps.main.data.TimerEvents
import kotlinx.coroutines.flow.Flow

class PineTimerRepository(
    private val countdownTimer: PineCountdownTimer,
    private val userSettings: UserSettings
) {
    fun observeTimerEvents(): Flow<TimerEvents> = countdownTimer.state

    fun startTimer() = countdownTimer.start(userSettings.focusSessionDuration)

    fun startBreakTimer() = countdownTimer.start(userSettings.shortBreakDuration)

    fun startLongBreakTimer() = countdownTimer.start(userSettings.longBreakDuration)

    fun stopCountdownTimer() = countdownTimer.stop()
}