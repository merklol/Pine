package com.maximapps.main.domain

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class SessionRepository {
    private val state = MutableStateFlow(Session())

    suspend fun update(action: suspend (Session) -> Session) = state.update { action(it) }

    fun observeSessionEvents() = state.asStateFlow()
}