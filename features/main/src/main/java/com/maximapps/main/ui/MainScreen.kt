package com.maximapps.main.ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.maximapps.coreui.buttons.LargeButton
import com.maximapps.coreui.theme.DarkGrey500
import com.maximapps.coreui.theme.Green200
import com.maximapps.coreui.theme.Green600
import com.maximapps.coreui.theme.Green700
import com.maximapps.coreui.theme.Green800
import com.maximapps.coreui.theme.Grey300
import com.maximapps.coreui.theme.Grey400
import com.maximapps.coreui.theme.Grey500
import com.maximapps.main.CountdownTimerState
import com.maximapps.main.R
import com.maximapps.main.TimerState
import com.maximapps.main.ui.views.ClockFaceDefaults
import com.maximapps.main.ui.views.CountdownTimer
import com.maximapps.main.ui.views.CurrentDateView
import com.maximapps.main.ui.views.MessageBar
import com.maximapps.main.ui.views.progressindicators.StepProgressIndicator
import com.maximapps.main.ui.views.progressindicators.animateProgressAsState
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun MainScreen(
    calendar: Calendar,
    timerState: TimerState,
    uiController: SystemUiController,
    onTimerButtonClick: () -> Unit
) {
    val (countdownState, remainingTime, totalTime, currentStep) = timerState

    val animatedCountdownProgress by animateProgressAsState(remainingTime, totalTime)

    val systemBarsColor by animateColorAsState(
        targetValue = when (countdownState) {
            CountdownTimerState.ShortBreak, CountdownTimerState.LongBreak -> DarkGrey500
            else -> Green800
        }
    )
    val backgroundColor by animateColorAsState(
        targetValue = when (countdownState) {
            CountdownTimerState.ShortBreak, CountdownTimerState.LongBreak -> Grey400
            else -> Green600
        }
    )

    val bezelColor by animateColorAsState(
        targetValue = when (countdownState) {
            CountdownTimerState.ShortBreak, CountdownTimerState.LongBreak -> Grey300
            else -> Green200
        }
    )

    val dateViewBackgroundColor by animateColorAsState(
        targetValue = when (countdownState) {
            CountdownTimerState.ShortBreak, CountdownTimerState.LongBreak -> Grey500
            else -> Green700
        }
    )

    SideEffect {
        uiController.setSystemBarsColor(systemBarsColor)
    }

    Scaffold(
        backgroundColor = backgroundColor,
        topBar = { MainTopBar(dateViewBackgroundColor) },
        bottomBar = { MainBottomBar() }
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            MessageBar(
                message = when(countdownState) {
                    CountdownTimerState.ShortBreak -> "Take a short break!"
                    CountdownTimerState.LongBreak -> "Take a long break!"
                    CountdownTimerState.Work -> stringResource(id = R.string.current_work_msg, currentStep + 1)
                    else -> ""
                },
                isVisible = timerState.isActiveSession()
            )
            CountdownTimer(
                calendar = calendar,
                isRunning = timerState.isActiveSession(),
                remainingTime = remainingTime,
                clockFaceColors = ClockFaceDefaults.ClockFaceColors.copy(bezelColor = bezelColor),
                progress = animatedCountdownProgress,
                alpha = if (timerState.countdownTimerState != CountdownTimerState.Idle) 1f else 0f
            )
            Spacer(modifier = Modifier.height(22.dp))
            StepProgressIndicator(
                step = currentStep,
                totalSteps = 4
            )
            Spacer(modifier = Modifier.height(22.dp))
            LargeButton(
                modifier = Modifier.width(132.dp),
                text = if (timerState.isActiveSession())
                    "Give Up".uppercase(Locale.getDefault())
                else "Start".uppercase(Locale.getDefault()), onClick = onTimerButtonClick
            )
        }
    }
}

private fun TimerState.isActiveSession() = this.countdownTimerState != CountdownTimerState.Idle

@Composable
fun MainTopBar(dateViewBackgroundColor: Color) {
    Box(Modifier.fillMaxWidth()) {
        CurrentDateView(
            date = Date(),
            backgroundColor = dateViewBackgroundColor,
            modifier = Modifier.align(Alignment.Center)
        )
        IconButton(modifier = Modifier.align(Alignment.CenterEnd), onClick = { }) {
            Icon(
                imageVector = Icons.Filled.Settings,
                contentDescription = stringResource(id = R.string.settings_btn),
                tint = Color.White
            )
        }
    }
}

@Composable
fun MainBottomBar() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.End) {
        IconButton(onClick = { }) {
            Icon(
                painter = painterResource(R.drawable.ic_baseline_bar_chart_24),
                contentDescription = stringResource(id = R.string.statistics_btn),
                tint = Color.White
            )
        }
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    MainScreen(
        calendar = Calendar.getInstance(),
        uiController = rememberSystemUiController(),
        timerState = TimerState(
            remainingTime = 3000L,
            totalTime = 5000L,
            currentStep = 1
        )
    ) { }
}
