package com.maximapps.main.domain.phases

import com.maximapps.main.domain.Session
import com.maximapps.main.domain.PineTimerRepository

class TerminalPhase(private val timerRepository: PineTimerRepository) : Phase {
    override fun proceed(state: Session) =
        state.toTerminalPhase().also { timerRepository.stopCountdownTimer() }
}