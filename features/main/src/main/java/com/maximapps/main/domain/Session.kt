package com.maximapps.main.domain

import com.maximapps.main.domain.phases.PhaseType

private const val MaxStepCount = 8

private const val LastStep = MaxStepCount - 1

data class Session(
    val remainingTime: Long = 0L,
    val currentInterval: Int = 0,
    val phase: PhaseType = PhaseType.Idle
) {
    private var intervals: List<Interval> = emptyList()

    fun updateRemainingTime(value: Long) = copy(remainingTime = value)

    fun toFocusSessionPhase() = copy(phase = PhaseType.FocusSession).apply {
        intervals = createIntervalOrEnlargeInterval()
    }

    private fun createIntervalOrEnlargeInterval() =
        if (isFirstInterval()) enlargeInterval() else intervals + Interval(1)

    private fun isFirstInterval() = intervals.size == 1 && intervals.first().steps < 2

    fun toBreakPhase() =
        copy(phase = PhaseType.ShortBreak, currentInterval = intervals.size).apply {
            intervals = enlargeInterval()
        }

    fun toLongBreakPhase() =
        copy(phase = PhaseType.LongBreak, currentInterval = intervals.size).apply {
            intervals = enlargeInterval()
        }

    private fun enlargeInterval(): List<Interval> = intervals.toMutableList().apply {
        this[lastIndex] = Interval(lastOrNull()?.steps?.inc() ?: 0)
    }

    fun toTerminalPhase() = Session()

    fun determinePhase() = when {
        isSessionOver() -> PhaseType.Idle
        isLastFocusSession() -> PhaseType.LongBreak
        isFocusSession() -> PhaseType.ShortBreak
        else -> PhaseType.FocusSession
    }

    private fun isSessionOver() = intervals.sumOf(Interval::steps) == MaxStepCount

    private fun isFocusSession() = intervals.sumOf(Interval::steps) % 2 != 0

    private fun isLastFocusSession() = intervals.sumOf(Interval::steps) == LastStep
}