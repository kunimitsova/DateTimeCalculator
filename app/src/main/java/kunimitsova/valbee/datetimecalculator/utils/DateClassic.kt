package kunimitsova.valbee.datetimecalculator.utils

import android.annotation.SuppressLint
import kunimitsova.valbee.datetimecalculator.utils.leadingZero
import java.text.DateFormat
import java.time.LocalDate
import java.time.Year
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Date

data class DateClassic(val day: Int, val month: Int, val year: Int){
    override fun toString(): String {
        return "$year-${leadingZero(month, 2)}-${leadingZero(day, 2)}"
    }
    companion object {
        @SuppressLint("NewApi")
        fun fromString(dateString: String): DateClassic {
            val dateDate = LocalDate.parse(dateString)
            return DateClassic(dateDate.dayOfMonth, dateDate.monthValue, dateDate.year)
        }
    }
}

