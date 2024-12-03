@file:Suppress("ktlint:standard:no-wildcard-imports")

package com.fintech.myapplication

import java.util.*

fun isNight(): Boolean {
    val currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
    return (currentHour <= 7 || currentHour >= 18)
}
