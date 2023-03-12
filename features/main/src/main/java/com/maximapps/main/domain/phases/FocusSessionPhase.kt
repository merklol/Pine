package com.maximapps.main.domain.phases

import com.maximapps.main.domain.Session
import com.maximapps.main.domain.PineTimerRepository

class FocusSessionPhase(private val timerRepository: PineTimerRepository) : Phase {
    override fun proceed(state: Session) =
        state.toFocusSessionPhase().also { timerRepository.startTimer() }
}