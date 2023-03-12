package com.maximapps.main.domain

import com.maximapps.main.domain.phases.Phase
import com.maximapps.main.domain.phases.PhaseType

class ManageSessionUseCase(
    private val sessionRepository: SessionRepository = SessionRepositoryImpl,
    private val phases: Map<PhaseType, Phase> = SessionPhases
) {
    fun startSession() = sessionRepository.update {
        phases.getValue(PhaseType.FocusSession).proceed(it)
    }

    fun stopSession() = sessionRepository.update {
        phases.getValue(PhaseType.Idle).proceed(it)
    }
}