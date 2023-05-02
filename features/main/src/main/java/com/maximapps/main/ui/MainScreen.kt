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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.madfrog.core.di.daggerViewModel
import com.maximapps.coreui.buttons.LargeButton
import com.maximapps.coreui.theme.DarkGrey500
import com.maximapps.coreui.theme.Green200
import com.maximapps.coreui.theme.Green600
import com.maximapps.coreui.theme.Green700
import com.maximapps.coreui.theme.Green800
import com.maximapps.coreui.theme.Grey300
import com.maximapps.coreui.theme.Grey400
import com.maximapps.coreui.theme.Grey500
import com.maximapps.main.R
import com.maximapps.main.domain.phases.PhaseType
import com.maximapps.main.ui.views.ClockFaceDefaults
import com.maximapps.main.ui.views.CountdownTimer
import com.maximapps.main.ui.views.CurrentDateView
import com.maximapps.main.ui.views.MessageBar
import com.maximapps.main.ui.views.progressindicators.StepProgressIndicator
import com.maximapps.main.ui.views.progressindicators.animateProgressAsState
import java.util.Date

private const val AnimationTime = 60000L

@Composable
fun MainScreen(
    viewModel: MainViewModel = daggerViewModel(),
    onSettingsButtonClick: () -> Unit
) {
    val uiController = rememberSystemUiController()

    val state by viewModel.screenState.collectAsState()
    val animatedCountdownProgress by animateProgressAsState(state.remainingTime, AnimationTime)

    val systemBarsColor by animateColorAsState(
        targetValue = when (state.phase) {
            PhaseType.ShortBreak, PhaseType.LongBreak -> DarkGrey500
            else -> Green800
        }
    )
    val backgroundColor by animateColorAsState(
        targetValue = when (state.phase) {
            PhaseType.ShortBreak, PhaseType.LongBreak -> Grey400
            else -> Green600
        }
    )

    val bezelColor by animateColorAsState(
        targetValue = when (state.phase) {
            PhaseType.ShortBreak, PhaseType.LongBreak -> Grey300
            else -> Green200
        }
    )

    val dateViewBackgroundColor by animateColorAsState(
        targetValue = when (state.phase) {
            PhaseType.ShortBreak, PhaseType.LongBreak -> Grey500
            else -> Green700
        }
    )

    SideEffect {
        uiController.setSystemBarsColor(systemBarsColor)
    }

    Scaffold(
        backgroundColor = backgroundColor,
        topBar = { MainTopBar(dateViewBackgroundColor, onSettingsButtonClick) },
        bottomBar = { MainBottomBar() }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            MessageBar(
                message = when(state.phase) {
                    PhaseType.ShortBreak -> "Take a short break!"
                    PhaseType.LongBreak -> "Take a long break!"
                    PhaseType.FocusSession -> stringResource(id = R.string.current_work_msg, state.currentInterval + 1)
                    else -> ""
                },
                isVisible = state.phase.isActiveSession()
            )
            CountdownTimer(
                isRunning = state.phase.isActiveSession(),
                remainingTime = state.remainingTime,
                clockFaceColors = ClockFaceDefaults.ClockFaceColors.copy(bezelColor = bezelColor),
                progress = animatedCountdownProgress,
                alpha = if (state.phase != PhaseType.Idle) 1f else 0f
            )
            Spacer(modifier = Modifier.height(22.dp))
            StepProgressIndicator(
                step = state.currentInterval,
                totalSteps = 4
            )
            Spacer(modifier = Modifier.height(22.dp))
            LargeButton(
                modifier = Modifier.width(132.dp),
                text = if (state.phase.isActiveSession()) "Give Up" else "Start",
                onClick = {
                    if (state.phase == PhaseType.Idle) {
                        viewModel.startSession()
                    } else {
                        viewModel.stopSession()
                    }
                }
            )
        }
    }
}

private fun PhaseType.isActiveSession() = this != PhaseType.Idle

@Composable
fun MainTopBar(dateViewBackgroundColor: Color, onSettingsButtonClick: () -> Unit) {
    Box(Modifier.fillMaxWidth()) {
        CurrentDateView(
            date = Date(),
            backgroundColor = dateViewBackgroundColor,
            modifier = Modifier.align(Alignment.Center)
        )
        IconButton(modifier = Modifier.align(Alignment.CenterEnd), onClick = onSettingsButtonClick) {
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