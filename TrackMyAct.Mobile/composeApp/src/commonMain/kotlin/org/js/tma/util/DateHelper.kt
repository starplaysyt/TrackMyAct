package org.js.tma.util

import kotlinx.datetime.TimeZone
import kotlinx.datetime.number
import kotlinx.datetime.toLocalDateTime
import kotlin.time.Instant

fun convertMillisToDate(millis: Long): String {
    val instant = Instant.fromEpochMilliseconds(millis)
    val date = instant.toLocalDateTime(TimeZone.currentSystemDefault()).date
    return "${date.year}.${if (date.month.number.toString().length == 1) "0" + date.month.number else date.month.number}.${if (date.day.toString().length == 1) "0" + date.day else date.day}"
}