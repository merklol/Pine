package com.maximapps.main.domain

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SessionRepository {
    private val state = MutableStateFlow(Session())

    fun update(action: (Session) -> Session) = state.update(action)

    fun observeSessionEvents() = state.asStateFlow()
}