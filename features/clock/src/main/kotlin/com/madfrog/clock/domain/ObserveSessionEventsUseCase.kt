package com.maximapps.main.domain

import com.maximapps.main.data.TimerEvents
import com.maximapps.main.domain.phases.Phase
import com.maximapps.main.domain.phases.PhaseType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
//TODO: Split to interface and implementation to use @Binds instead of @Provide
class ObserveSessionEventsUseCase(
    private val pineTimerRepository: PineTimerRepository,
    private val sessionRepository: SessionRepository,
    private val phases: Map<PhaseType, Phase>
) {

    fun observe() = pineTimerRepository.observeTimerEvents()
        .onEach(::process)
        .flatMapLatest { sessionRepository.observeSessionEvents() }

    private suspend fun process(timerEvents: TimerEvents) = when (timerEvents) {
        is TimerEvents.Elapsed -> sessionRepository.update {
            it.updateRemainingTime(timerEvents.value)
        }
        is TimerEvents.Stopped -> sessionRepository.update {
            phases.getValue(it.determineNextPhase()).proceed(it)
        }
        else -> {
            //Idle state
        }
    }
}