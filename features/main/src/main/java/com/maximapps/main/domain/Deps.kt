package com.maximapps.main.domain

import com.maximapps.main.data.PineCountdownTimer
import com.maximapps.main.domain.phases.BreakPhase
import com.maximapps.main.domain.phases.FocusSessionPhase
import com.maximapps.main.domain.phases.LongBreakPhase
import com.maximapps.main.domain.phases.PhaseType
import com.maximapps.main.domain.phases.TerminalPhase

/**
 * Deps
 */

val UserSettingsImpl = UserSettings()

val PineTimerRepositoryImpl = PineTimerRepository(PineCountdownTimer(), UserSettingsImpl)

val SessionRepositoryImpl = SessionRepository()

val SessionPhases = mapOf(
    PhaseType.Idle to TerminalPhase(PineTimerRepositoryImpl),
    PhaseType.FocusSession to FocusSessionPhase(PineTimerRepositoryImpl),
    PhaseType.ShortBreak to BreakPhase(PineTimerRepositoryImpl),
    PhaseType.LongBreak to LongBreakPhase(PineTimerRepositoryImpl)
)