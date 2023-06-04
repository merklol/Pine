package com.maximapps.main.domain.phases

import com.maximapps.main.domain.Session
import com.maximapps.main.domain.PineTimerRepository
import javax.inject.Inject

class TerminalPhase @Inject constructor(private val timerRepository: PineTimerRepository) : Phase {
    override suspend fun proceed(state: Session) =
        state.toTerminalPhase().also { timerRepository.stopCountdownTimer() }
}