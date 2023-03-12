package com.maximapps.main.domain

import com.maximapps.main.data.TimerEvents
import com.maximapps.main.domain.phases.Phase
import com.maximapps.main.domain.phases.PhaseType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onEach

@OptIn(ExperimentalCoroutinesApi::class)
class ObserveSessionEventsUseCase(
    private val pineTimerRepository: PineTimerRepository = PineTimerRepositoryImpl,
    private val sessionRepository: SessionRepository = SessionRepositoryImpl,
    private val phases: Map<PhaseType, Phase> = SessionPhases
) {

    fun observe() = pineTimerRepository.observeTimerEvents()
        .onEach(::process)
        .flatMapLatest { sessionRepository.observeSessionEvents() }

    private fun process(timerEvents: TimerEvents) = when (timerEvents) {
        is TimerEvents.Elapsed -> sessionRepository.update {
            it.updateRemainingTime(timerEvents.value)
        }
        is TimerEvents.Stopped -> sessionRepository.update {
            phases.getValue(it.determinePhase()).proceed(it)
        }
        else -> {
            //Idle state
        }
    }
}