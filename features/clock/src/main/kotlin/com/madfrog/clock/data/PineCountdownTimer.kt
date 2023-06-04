package com.maximapps.main.data

import android.os.CountDownTimer
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PineCountdownTimer {
    private val internalState = MutableStateFlow<TimerEvents>(TimerEvents.Idle)
    val state = internalState.asStateFlow()

    private lateinit var timer: CountDownTimer

    fun start(duration: Long, interval: Long = 500) {
        timer = object : CountDownTimer(duration, interval) {
            override fun onTick(millisUntilFinished: Long) {
                internalState.tryEmit(TimerEvents.Elapsed(millisUntilFinished))
            }

            override fun onFinish() {
                internalState.tryEmit(TimerEvents.Stopped)
            }
        }
        timer.start()
    }

    fun stop() {
        timer.cancel()
        internalState.tryEmit(TimerEvents.Idle)
    }
}