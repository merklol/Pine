package com.maximapps.main.ext

import com.maximapps.main.utils.Tau
import java.util.Calendar

/**
 * Returns hours in 12-clock format
 */
val Calendar.secondsInDegree get() = Tau().toDegrees() * get(Calendar.SECOND) / 60

/**
 * Returns hours in 12-clock format
 */
val Calendar.minutesInDegree get() = Tau().toDegrees() * get(Calendar.MINUTE) / 60

/**
 * Returns hours in 12-clock format
 */
val Calendar.hoursInDegree get() = Tau().toDegrees() * get(Calendar.HOUR) / 12
