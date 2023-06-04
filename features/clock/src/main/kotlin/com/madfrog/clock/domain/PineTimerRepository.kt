package com.maximapps.main.domain

import com.madfrog.usersettings.UserSettingsStorage
import com.maximapps.main.data.PineCountdownTimer
import com.maximapps.main.data.TimerEvents
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull

class PineTimerRepository(
    private val countdownTimer: PineCountdownTimer,
    private val userSettingsStorage: UserSettingsStorage
) {
    fun observeTimerEvents(): Flow<TimerEvents> = countdownTimer.state

    suspend fun startTimer() =
        countdownTimer.start(
            userSettingsStorage.latestUserSettings().firstOrNull()?.focusSessionDuration ?: 0
        )

    suspend fun startBreakTimer() =
        countdownTimer.start(
            userSettingsStorage.latestUserSettings().firstOrNull()?.shortBreakDuration ?: 0
        )


    suspend fun startLongBreakTimer() =
        countdownTimer.start(
            userSettingsStorage.latestUserSettings().firstOrNull()?.longBreakDuration ?: 0
        )

    fun stopCountdownTimer() = countdownTimer.stop()
}