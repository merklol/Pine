package com.maximapps.main.domain.phases

import com.maximapps.main.domain.Session
import com.maximapps.main.domain.PineTimerRepository
import javax.inject.Inject

class LongBreakPhase @Inject constructor(private val timerRepository: PineTimerRepository) : Phase {
    override fun proceed(state: Session) =
        state.toLongBreakPhase().also { timerRepository.startLongBreakTimer() }
}