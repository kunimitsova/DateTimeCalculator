package kunimitsova.valbee.datetimecalculator.utils

import androidx.compose.ui.res.stringResource
import kunimitsova.valbee.datetimecalculator.R
import java.time.LocalDateTime
import java.time.Year
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalUnit


enum class DateTimeUnits(val friendlyName: String, val abbrev: String) {
    YEAR(  R.string.Years.toString(), "y"),
    MONTH( R.string.Months.toString(), "M"),
    WEEK(R.string.Weeks.toString(), "w"),
    DAY(R.string.Days.toString(), "d"),
    HOUR(R.string.Hours.toString(), "H"),
    MINUTE(R.string.Minutes.toString(), "m"),
    SECOND(R.string.Seconds.toString(), "s"),
    MILLISECOND(R.string.Millis.toString(), "S");

    // to get to the next granular level of dateness, in case of fractional user inputs
    fun getNext() = when (this) {
        YEAR -> { MONTH }
        MONTH -> { WEEK }
        WEEK -> { DAY }
        DAY -> { HOUR }
        HOUR -> { MINUTE }
        MINUTE -> { SECOND }
        SECOND -> { MILLISECOND }
        MILLISECOND -> { MILLISECOND }
    }
    // to convert the fractional item
    fun nextValue(numF: Float) = when (this) {
        YEAR -> { numF * 12 }
        MONTH -> { numF * 4 }
        WEEK -> { numF * 7 }
        DAY -> { numF * 24 }
        HOUR -> { numF * 60 }
        MINUTE -> { numF * 60 }
        SECOND -> { numF * 1000 }
        MILLISECOND -> { numF }
    }

}