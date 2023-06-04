package com.maximapps.main.ext

import android.text.format.DateUtils
import kotlin.math.ceil

const val SecondsInMillis = DateUtils.SECOND_IN_MILLIS
const val MinuteInSeconds = 60

/**
 * Converts a Long to a formatted string representation.
 */
fun Long.toFormattedTime() = String.format("%02d:%02d", toMinutes(), toSeconds())

/**
 * Converts a Long to minutes.
 */
private fun Long.toMinutes() = ceil(toDouble() / SecondsInMillis).toLong() / MinuteInSeconds

/**
 * Converts a Long to seconds.
 */
private fun Long.toSeconds() = ceil(toDouble() / SecondsInMillis).toLong() % MinuteInSeconds
