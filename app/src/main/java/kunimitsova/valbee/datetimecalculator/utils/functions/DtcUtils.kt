package kunimitsova.valbee.datetimecalculator.utils.functions

import android.annotation.SuppressLint

//import androidx.annotation.Sampled
import kunimitsova.valbee.datetimecalculator.utils.DateTimeUnits
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import kotlin.math.abs

/**
 * utilities for validatin', formattin', and calculatin'
 *
 * why are we suppress Lint NewApi everywhere? Because according
 * to the documentation, all of the java.util.time is available
 * to older APIs now, but the compiler still thinks it's 26 and up.
 * if I'm wrong, I'll have to rewrite all this.
 *
 */

fun leadingZero(num: Any, maxDigits: Int): String {
    val numString = num.toString().toLong().toString()
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

fun validFloat(num: Any?): String {
    // returns either the string representing a Float or
    // a string with the numbers extracted from something weird.
    val numString = num.toString()
    val regFloaty = "\\d*\\.?\\d+".toRegex()
    return if (numString.matches(regFloaty)) {
        numString
    } else {
        // select all chars prior to a dot and send them to validInt
        var index = numString.indexOf(".")
        index = if (index == -1) 0 else index
        val tmp = validInt(numString.dropLast(numString.length - index))
        if (tmp.isBlank()) validInt(numString) else validInt(tmp)
    }
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

fun getMilliString(milliStr: String): String {
    /**
     * Why cut off the length when it's possible to add any number of milliseconds?
     * Millis might be more than one second, for example 1,234 is 1 second and 234 millis.
     * but this particular string is just for the formatted time. So although you can
     * add 1,234 millis to the time, you can't have 1,234 millis as part of the time because
     * if it was 12:00:00.[1,234 millis] the time would actually show 12:00:01.234
     * This not is more for me than anyone looking because I keep forgetting how
     * millis work in my app.
     * The app assumes if you've typed in more than 3 millis for starting time, it's
     * because you've accidentally typed in micros or nanos.
     */
    return if (milliStr.length > 3) {
        milliStr.dropLast(milliStr.length - 3)
    } else {
        leadingZero(milliStr, 3)
    }
}

@SuppressLint("NewApi")
fun getSecString(secStr: String?): String {
    return if (secStr.isNullOrBlank()) {
        "00"
    } else {
        if (secStr.toInt() in (0..59)) {
            leadingZero(secStr, 2)
        } else {
            leadingZero(
                LocalTime.now()
                    .format(DateTimeFormatter.ofPattern("ss")), 2
            )
        }
    }
}

@SuppressLint("NewApi")
fun getMinString(minStr: String?): String {
    return if (minStr.isNullOrBlank()) {
        "00"
    } else {
        val minNum = minStr.toInt()
        if (minNum in (0..59)) {
            leadingZero(minStr, 2)
        } else {
            leadingZero(
                LocalTime.now()
                    .format(DateTimeFormatter.ofPattern("mm")), 2
            )
        }
    }
}

@SuppressLint("NewApi")
fun getHourString(hourStr: String?) : String {
    return if (hourStr.isNullOrBlank()) {
        "00"
    } else {
        val hourNum = hourStr.toInt()
        if (hourNum in (0..23)) {
            leadingZero(hourStr, 2)
        } else {
            // 'k' is hour in a day (1-24)
            leadingZero(
                LocalTime.now()
                    .format(DateTimeFormatter.ofPattern("kk")), 2
            )
        }
    }
}

@SuppressLint("NewApi")
fun getDayString(dayStr: String?) : String {
    return if (dayStr.isNullOrBlank()) {
        "01"
    } else {
        val dayNum = dayStr.toInt()
        if (dayNum in (1..31)) {
            leadingZero(dayStr, 2)
        } else {
            leadingZero(
                LocalDate.now()
                    .format(DateTimeFormatter.ofPattern("dd")), 2
            )
        }
    }
}

@SuppressLint("NewApi")
fun getMonthString(month: String?): String {
    return if (month.isNullOrBlank()) {
        "01"
    } else {
        val numMonth = month.toInt()
        if (numMonth in (1..12)) {
            leadingZero(month, 2)
        } else {
            leadingZero(
                LocalDate.now()
                    .format(DateTimeFormatter.ofPattern("MM")), 2
            )
        }
    }
}

@SuppressLint("NewApi")
fun getYearStr(year: String?): String {
    return if (year.isNullOrBlank()) {
        "1900"
    } else {
        leadingZero(validInt(year), 4)
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
fun getDateTimeLocal(year: String, month: String, day: String, hour: String,
                     min: String, sec: String, milli: String
): LocalDateTime {
    val dateStr = validateDate(year, month, day)
    val timeStr = formatLocalTime(
        hour, min, sec, milli
    )
    val dateTimeStr = dateStr + "T" + timeStr

    return LocalDateTime.parse(dateTimeStr)
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
                   units: DateTimeUnits
): LocalDateTime {
    return startDate.minus(numToSubtract, units.unit)
}


@SuppressLint("NewApi")
fun calculateDifference(date1: LocalDateTime, date2: LocalDateTime, units: DateTimeUnits): Long {
    val chronoUnits: ChronoUnit = units.unit
    return abs(chronoUnits.between(date1, date2))
}

