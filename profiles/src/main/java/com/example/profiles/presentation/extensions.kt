package com.example.profiles.presentation

import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

fun String.isMale() = this.lowercase(Locale.ROOT) == "male"

fun String.toFormattedDate(): String {
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS[VV]", Locale.ENGLISH)
    val outputFormatter = DateTimeFormatter.ofPattern("MMM, dd, yyyy", Locale.ENGLISH)
    val zonedDateTime = ZonedDateTime.parse(this, inputFormatter)
    return outputFormatter.format(zonedDateTime)
}