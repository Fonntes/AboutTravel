package com.example.abouttravel.helpers

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateConverter {

    private const val API_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'"

    private val dateFormat = SimpleDateFormat(API_DATE_FORMAT, Locale.getDefault())

    fun fromString(dateString: String): Date {
        return try {
            dateFormat.parse(dateString)
        } catch (e: ParseException) {
            throw RuntimeException("Invalid date format: $dateString", e)
        }
    }
}
