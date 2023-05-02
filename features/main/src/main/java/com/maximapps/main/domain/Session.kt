package com.maximapps.main.domain

import com.maximapps.main.domain.phases.PhaseType

private const val MaxStepCount = 8

private const val LastStep = MaxStepCount - 1

data class Session(
    val remainingTime: Long = 0L,
    val phase: PhaseType = PhaseType.Idle,
    private val intervals: List<Interval> = emptyList()
) {
    val currentInterval get() = intervals.sumOf(Interval::steps) / 2

    fun updateRemainingTime(value: Long) = copy(remainingTime = value)

    fun toFocusSessionPhase() =
        copy(phase = PhaseType.FocusSession, intervals = createOrEnlargeInterval())

    private fun createOrEnlargeInterval() =
        if (isFirstInterval()) enlargeInterval() else intervals + Interval(1)

    private fun isFirstInterval() = intervals.size == 1 && intervals.first().steps < 2

    fun toBreakPhase() =
        copy(phase = PhaseType.ShortBreak, intervals = enlargeInterval())

    fun toLongBreakPhase() =
        copy(phase = PhaseType.LongBreak, intervals = enlargeInterval())

    private fun enlargeInterval(): List<Interval> = intervals.toMutableList().apply {
        this[lastIndex] = Interval(lastOrNull()?.steps?.inc() ?: 0)
    }

    fun toTerminalPhase() = Session()

    fun determineNextPhase() = when {
        isSessionOver() -> PhaseType.Idle
        isLastFocusSession() -> PhaseType.LongBreak
        isFocusSession() -> PhaseType.ShortBreak
        else -> PhaseType.FocusSession
    }

    private fun isSessionOver() = intervals.sumOf(Interval::steps) == MaxStepCount

    private fun isFocusSession() = intervals.sumOf(Interval::steps) % 2 != 0

    private fun isLastFocusSession() = intervals.sumOf(Interval::steps) == LastStep
}