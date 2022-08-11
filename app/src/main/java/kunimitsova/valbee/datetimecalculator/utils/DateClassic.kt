package kunimitsova.valbee.datetimecalculator.utils

import android.annotation.SuppressLint
import kunimitsova.valbee.datetimecalculator.utils.leadingZero
import java.text.DateFormat
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.Year
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date

data class DateClassic(val year: Int,  val month: Int, val day: Int, val hour: Int,
                       val minute: Int, val second: Int, val millis: Int
) {
    override fun toString(): String {
        var str = "$year-${leadingZero(month, 2)}-${leadingZero(day, 2)}"
        str = str + "T${leadingZero(hour, 2)}:${leadingZero(minute, 2)}"
        str = str + ":${leadingZero(second, 2)}.${leadingZero(millis, 3)}"
        return str
    }
    companion object {
        @SuppressLint("NewApi")
        fun fromString(dateString: String): DateClassic {
            val dateTime = LocalDateTime.parse(dateString)
            return DateClassic(dateTime.year, dateTime.monthValue, dateTime.dayOfMonth,
                dateTime.hour, dateTime.minute, dateTime.second, (dateTime.nano / 1000000)
            )
            // divide by 1mil because DateClassic holds millis, not nanos.
        }
    }
}

