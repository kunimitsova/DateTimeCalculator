package kunimitsova.valbee.datetimecalculator.utils

import android.annotation.SuppressLint
import androidx.compose.ui.res.stringResource
import kunimitsova.valbee.datetimecalculator.R
import java.time.LocalDateTime
import java.time.Year
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalUnit

@SuppressLint("NewApi")
enum class DateTimeUnits(val friendlyNameID: Int, val unit: ChronoUnit) {
    YEAR(  R.string.Years, ChronoUnit.YEARS),
    MONTH( R.string.Months, ChronoUnit.MONTHS),
    WEEK(R.string.Weeks, ChronoUnit.WEEKS),
    DAY(R.string.Days, ChronoUnit.DAYS),
    HOUR(R.string.Hours, ChronoUnit.HOURS),
    MINUTE(R.string.Minutes, ChronoUnit.MINUTES),
    SECOND(R.string.Seconds, ChronoUnit.SECONDS),
    MILLISECOND(R.string.Millis, ChronoUnit.MILLIS);

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