package com.cvaccari.features.commons.extensions

import java.text.SimpleDateFormat
import java.util.*

fun String.toFormattedDate(): String {
    val parsedDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", getLocale())
        .parse(this)
    return SimpleDateFormat("dd/MM/yyyy'\n às 'HH:mm", getLocale()).format(parsedDate)
}

private fun getLocale() = Locale("pt", "Br")