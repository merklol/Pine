package com.maximapps.main.domain

import com.maximapps.main.domain.phases.Phase
import com.maximapps.main.domain.phases.PhaseType

class ManageSessionUseCase constructor(
    private val sessionRepository: SessionRepository,
    private val phases: Map<PhaseType, Phase>
) {
    fun startSession() = sessionRepository.update {
        phases.getValue(PhaseType.FocusSession).proceed(it)
    }

    fun stopSession() = sessionRepository.update {
        phases.getValue(PhaseType.Idle).proceed(it)
    }
}