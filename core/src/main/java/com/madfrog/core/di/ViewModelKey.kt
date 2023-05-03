package com.madfrog.core.di

import androidx.lifecycle.ViewModel
import dagger.MapKey
import kotlin.reflect.KClass

/**
 * Annotation to define  key for Dagger bound ViewModels.
 */
@[MapKey Target(AnnotationTarget.FUNCTION)]
annotation class ViewModelKey(val value: KClass<out ViewModel>)