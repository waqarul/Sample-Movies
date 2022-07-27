package com.wtmcodex.samplemovies.extensions

import com.wtmcodex.samplemovies.model.movie.Movie
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

const val DATE_FORMAT_EXIST = "yyyy-MM-dd"
const val DATE_FORMAT_NEW = "LLLL dd, yyyy"

fun Movie.getFormattedReleaseDate(): String =
    formatDateFromString(DATE_FORMAT_EXIST, DATE_FORMAT_NEW, releaseDate)


private fun formatDateFromString(
    inputFormat: String,
    outputFormat: String,
    inputDate: String?
): String {
    var parsed: Date?
    var outputDate = ""
    val dfInput = SimpleDateFormat(inputFormat, Locale.getDefault())
    val dfOutput = SimpleDateFormat(outputFormat, Locale.getDefault())
    try {
        parsed = dfInput.parse(inputDate)
        outputDate = dfOutput.format(parsed)
    } catch (e: ParseException) {
        print(e)
    }
    return outputDate
}