package com.maximapps.main.di.modules

import com.madfrog.usersettings.UserSettingsStorage
import com.maximapps.main.data.PineCountdownTimer
import com.maximapps.main.domain.ManageSessionUseCase
import com.maximapps.main.domain.ObserveSessionEventsUseCase
import com.maximapps.main.domain.PineTimerRepository
import com.maximapps.main.domain.SessionRepository
import com.maximapps.main.domain.phases.BreakPhase
import com.maximapps.main.domain.phases.FocusSessionPhase
import com.maximapps.main.domain.phases.LongBreakPhase
import com.maximapps.main.domain.phases.Phase
import com.maximapps.main.domain.phases.PhaseType
import com.maximapps.main.domain.phases.TerminalPhase
import dagger.MapKey
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap
import javax.inject.Singleton

@Module
//TODO: Refactor this module
object MainModule {

    @Provides
    //TODO: should be custom scope and replaced with @Binds
    @Singleton
    fun provideSessionScreen(): SessionRepository = SessionRepository()

    @Provides
    //TODO: should be custom scope and replaced with @Binds
    @Singleton
    fun provideManageSessionUseCase(
        sessionRepository: SessionRepository,
        phases: Map<PhaseType, @JvmSuppressWildcards Phase>
    ): ManageSessionUseCase = ManageSessionUseCase(sessionRepository, phases)


    @Provides
    //TODO: should be custom scope and replaced with @Binds
    @Singleton
    fun providePineCountdownTimer(): PineCountdownTimer = PineCountdownTimer()

    @Provides
    //TODO: should be custom scope and replaced with @Binds
    @Singleton
    fun providePineTimerRepository(
        pineCountdownTimer: PineCountdownTimer,
        userSettings: UserSettingsStorage
    ): PineTimerRepository = PineTimerRepository(pineCountdownTimer, userSettings)


    @Provides
    //TODO: should be custom scope and replaced with @Binds
    @Singleton
    fun prObserveSessionEventsUseCase(
        pineTimerRepository: PineTimerRepository,
        sessionRepository: SessionRepository,
        phases: Map<PhaseType, @JvmSuppressWildcards Phase>
    ): ObserveSessionEventsUseCase = ObserveSessionEventsUseCase(
        pineTimerRepository,
        sessionRepository,
        phases
    )


    //Phases

    @Singleton
    @IntoMap
    @Provides
    //TODO: Consider to rename PhaseType
    @PhaseKey(PhaseType.Idle)
    fun provideTerminalPhase(
        timerRepository: PineTimerRepository
    ): Phase = TerminalPhase(timerRepository)

    @Singleton
    @IntoMap
    @Provides
    //TODO: Consider to rename PhaseType
    @PhaseKey(PhaseType.FocusSession)
    fun provideFocusSessionPhase(
        timerRepository: PineTimerRepository
    ): Phase = FocusSessionPhase(timerRepository)

    @Singleton
    @IntoMap
    @Provides
    //TODO: Consider to rename PhaseType
    @PhaseKey(PhaseType.ShortBreak)
    fun provideBreakPhase(
        timerRepository: PineTimerRepository
    ): Phase = BreakPhase(timerRepository)

    //TODO: Consider to rename PhaseType
    @[Provides Singleton IntoMap PhaseKey(PhaseType.LongBreak)]
    fun provideLongBreakPhase(
        timerRepository: PineTimerRepository
    ): Phase = LongBreakPhase(timerRepository)

}


@[MapKey Target(AnnotationTarget.FUNCTION)]
annotation class PhaseKey(val value: PhaseType)