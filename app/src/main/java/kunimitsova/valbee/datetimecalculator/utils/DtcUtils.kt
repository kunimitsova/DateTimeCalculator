package kunimitsova.valbee.datetimecalculator.utils

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

//import androidx.annotation.Sampled
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * utilities for validatin', formattin', and calculatin'
 */

fun leadingZero(num: Any, maxDigits: Int): String {
    val numString = num.toString()
    var len = numString.length
    var exitString = numString
    while (len < maxDigits) {
        exitString = "0" + exitString
        len++
    }
    return exitString
}

fun validInt(num: Any?): String {
    val numString = num.toString()
    val nonNumeric = "[^0-9]".toRegex()
    return numString.replace(nonNumeric, "")
}

@SuppressLint("NewApi")
fun validateDate(year: String, month: String, day: String): String {
    // try to parse the numbers to see if it's a valid date, if not,
    // return the 1st of the selected year/month
    val dateStr = formatLocalDate(year, month, day)
    try {
        LocalDate.parse(dateStr, DateTimeFormatter.ISO_LOCAL_DATE)
    } catch (e: Exception) {
        return formatLocalDate(year, month, "01")
    }
    return dateStr
}

fun validateItems(numStr: String, units: DateTimeUnits?) : String {
    return when (units) {
        DateTimeUnits.YEAR -> leadingZero(numStr, 4)
        DateTimeUnits.MONTH -> getMonthString(leadingZero(numStr, 2))
        DateTimeUnits.DAY -> getDayString(leadingZero(numStr, 2))
        DateTimeUnits.HOUR -> getHourString(leadingZero(numStr, 2))
        DateTimeUnits.MINUTE -> getMinString(leadingZero(numStr, 2))
        DateTimeUnits.SECOND -> getSecString(leadingZero(numStr, 2))
        DateTimeUnits.MILLISECOND -> getMilliString(numStr)
        else -> numStr
    }
}

fun getMilliString(milliStr: String): String {
    if (milliStr.length > 3) {
        return milliStr.dropLast(milliStr.length - 3)
    } else {
        return leadingZero(milliStr,3)
    }
}

@SuppressLint("NewApi")
fun getSecString(secStr: String?): String {
    if (secStr.isNullOrBlank()) {
        return "00"
    } else {
        if (secStr.toInt() in (0..59)) {
            return leadingZero(secStr, 2)
        } else {
            return leadingZero(
                LocalTime.now()
                    .format(DateTimeFormatter.ofPattern("ss")), 2
            )
        }
    }
}

@SuppressLint("NewApi")
fun getMinString(minStr: String?): String {
    if (minStr.isNullOrBlank()) {
        return "00"
    } else {
        val minNum = minStr.toInt()
        if (minNum in (0..59)) {
            return leadingZero(minStr, 2)
        } else {
            return leadingZero(
                LocalTime.now()
                    .format(DateTimeFormatter.ofPattern("mm")), 2
            )
        }
    }
}

@SuppressLint("NewApi")
fun getHourString(hourStr: String?) : String {
    if (hourStr.isNullOrBlank()) {
        return "00"
    } else {
        val hourNum = hourStr.toInt()
        if (hourNum in (0..23)) {
            return leadingZero(hourStr, 2)
        } else {
            // 'k' is hour in a day (1-24)
            return leadingZero(
                LocalTime.now()
                    .format(DateTimeFormatter.ofPattern("kk")), 2
            )
        }
    }
}

@SuppressLint("NewApi")
fun getDayString(dayStr: String?) : String {
    if (dayStr.isNullOrBlank()) {
        return "01"
    } else {
        val dayNum = dayStr.toInt()
        if (dayNum in (1..31)) {
            return leadingZero(dayStr, 2)
        } else {
            return leadingZero(
                LocalDate.now()
                    .format(DateTimeFormatter.ofPattern("dd")), 2
            )
        }
    }
}

@SuppressLint("NewApi")
fun getMonthString(month: String?): String {
    if (month.isNullOrBlank()) {
        return "01"
    } else {
        val numMonth = month.toInt()
        if (numMonth in (1..12)) {
            return leadingZero(month, 2)
        } else {
            return leadingZero(
                LocalDate.now()
                    .format(DateTimeFormatter.ofPattern("MM")), 2
            )
        }
    }
}

@SuppressLint("NewApi")
fun getYearStr(year: String?): String {
    if (year.isNullOrBlank()) {
        return "1900"
    } else {
        return leadingZero(validInt(year), 4)
    }
}

fun formatLocalDate(year: String, month: String, day: String): String {
    return  getYearStr(validInt(year)) + "-" +
            getMonthString(validInt(month)) + "-" +
            getDayString(validInt(day))
}

fun formatLocalTime(hour: String, minute: String, second: String, millis: String): String {
    return getHourString(validInt(hour)) + ":" + getMinString(validInt(minute)) +
            ":" + getSecString(validInt(second)) + "." + getMilliString(millis)
}

@SuppressLint("NewApi")
fun getNumAndUnits( numToAdd: String?, units: DateTimeUnits) : Pair<Long, DateTimeUnits>  {
    var setupNum = if(numToAdd.isNullOrBlank()) 0f else numToAdd.toFloat()
    var setupUnits = units
    // first get the new number to add, before updating the actual units.
    while ((setupNum.toInt().toFloat() != setupNum) and (setupUnits != DateTimeUnits.MILLISECOND)) {
        setupNum = setupUnits.nextValue(setupNum)
        setupUnits = setupUnits.getNext()
    }
    return Pair(setupNum.toLong(), setupUnits)
}

@SuppressLint("NewApi")
fun calculatePlus(startDate: LocalDateTime, numToAdd: Long, units: DateTimeUnits): LocalDateTime {
    return startDate.plus(numToAdd, units.unit)
}

@SuppressLint("NewApi")
fun calculateMinus(startDate: LocalDateTime,
                   numToSubtract: Long,
                   units: DateTimeUnits): LocalDateTime {
    return startDate.minus(numToSubtract, units.unit)
}

