package com.maximapps.main.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maximapps.main.domain.ManageSessionUseCase
import com.maximapps.main.domain.ObserveSessionEventsUseCase
import com.maximapps.main.domain.Session
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val manageSessionUseCase: ManageSessionUseCase,
    observeSessionEventsUseCase: ObserveSessionEventsUseCase
) : ViewModel() {

    val screenState = MutableStateFlow(Session())

    init {
        observeSessionEventsUseCase.observe()
            .onEach(screenState::tryEmit)
            .launchIn(viewModelScope)
    }

    fun startSession() {
        viewModelScope.launch {
            manageSessionUseCase.startSession()
        }
    }

    fun stopSession() {
        viewModelScope.launch {
            manageSessionUseCase.stopSession()
        }
    }
}