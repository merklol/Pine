package com.maximapps.main.utils

import kotlin.math.PI

/**
 * 2 * PI
 */
@JvmInline
value class Tau(private val value: Double = 2 * PI) {
    fun toDegrees(): Float = Math.toDegrees(value).toFloat()
}
