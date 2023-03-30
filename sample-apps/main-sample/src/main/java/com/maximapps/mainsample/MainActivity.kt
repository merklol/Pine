package com.maximapps.mainsample

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.lifecycleScope
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.maximapps.coreui.theme.PineTheme
import com.maximapps.main.CountdownTimerState
import com.maximapps.main.TimerState
import com.maximapps.main.domain.ManageSessionUseCase
import com.maximapps.main.domain.ObserveSessionEventsUseCase
import com.maximapps.main.domain.Session
import com.maximapps.main.ui.MainScreen
import com.maximapps.main.domain.phases.PhaseType
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import java.util.Calendar

class MainActivity : ComponentActivity() {
    private val calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val viewModelScreenState = MutableStateFlow(Session())

        val manageSessionUseCase = ManageSessionUseCase()
        val observeSessionEventsUseCase = ObserveSessionEventsUseCase()

        observeSessionEventsUseCase.observe()
            .onEach(viewModelScreenState::tryEmit)
            .launchIn(lifecycleScope)

        setContent {
            val state = viewModelScreenState.collectAsState()
            PineTheme {
                MainScreen(
                    calendar = calendar,
                    timerState = TimerState(
                        when (state.value.phase) {
                            PhaseType.Idle -> CountdownTimerState.Idle
                            PhaseType.FocusSession -> CountdownTimerState.Work
                            PhaseType.ShortBreak -> CountdownTimerState.ShortBreak
                            PhaseType.LongBreak -> CountdownTimerState.LongBreak
                        },
                        state.value.remainingTime,
                        60000L,
                        state.value.currentInterval
                    ),
                    uiController = rememberSystemUiController(),
                    onTimerButtonClick = {
                        if (state.value.phase == PhaseType.Idle) {
                            manageSessionUseCase.startSession()
                        } else {
                            manageSessionUseCase.stopSession()
                        }
                    })
            }
        }
    }
}
