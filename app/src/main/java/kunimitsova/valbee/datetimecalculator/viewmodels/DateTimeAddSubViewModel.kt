package kunimitsova.valbee.datetimecalculator.viewmodels

import android.annotation.SuppressLint
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import kunimitsova.valbee.datetimecalculator.utils.*
import java.time.LocalDateTime
import java.util.*

/**
 * All user entry are implemented as strings.
 * Milliseconds are not implemented as user entry although they will still
 * appear in the list of units you can add/subtract.
 */

class DateTimeAddSubViewModel: ViewModel() {
    // state altering vars for the user input - starting date/time = current.
    private val calendar = Calendar.getInstance()
    var startYear by mutableStateOf(calendar[Calendar.YEAR].toString())
    var startMonth by mutableStateOf(leadingZero((calendar[Calendar.MONTH] + 1), 2))
    var startDay by mutableStateOf(leadingZero(calendar[Calendar.DAY_OF_MONTH],2))
    var startHour by mutableStateOf(leadingZero(calendar[Calendar.HOUR_OF_DAY], 2))
    var startMin by mutableStateOf(leadingZero(calendar[Calendar.MINUTE], 2))
    var startSec by mutableStateOf(leadingZero(calendar[Calendar.SECOND], 2))
    var startMilli by mutableStateOf(leadingZero(calendar[Calendar.MILLISECOND], 3))

    val onYrChange = { startYr: String -> startYear = startYr }
    val onMoChange = { startMo:String -> startMonth = startMo }
    val onDayChange = { startDy: String -> startDay = startDy }
    val onHoChange = { startH: String -> startHour = startH }
    val onMiChange = { startMn: String -> startMin = startMn }
    val onSchange = { startSc: String -> startSec = startSc }
    val onMillChange = { startMil: String -> startMilli = startMil }

// this will be implemented later
//    var showMillis by mutableStateOf(false)

    // state of the toggle button for add(true) subtract(false)
    var plusMinus by mutableStateOf(true)

    // state of the number of [units] to add/subtract
    var numToAdd by mutableStateOf("0")
    val onNumChange = { numCh: String -> numToAdd = numCh}

    // state of unit selector
    var selectedUnit by mutableStateOf(DateTimeUnits.DAY)
    var expanded by mutableStateOf(false)
    val onBoxClick = { expanded = !expanded }
    val onDismissMenu = { expanded = false }
    val onItemClick = { it: DateTimeUnits ->
        selectedUnit = it
        expanded = false
    }
    var dateStr = validateDate(startYear, startMonth, startDay)
    var timeStr = formatLocalTime(startHour, startMin, startSec, startMilli)
    var dateTimeStr = dateStr + "T" + timeStr
    @SuppressLint("NewApi")
    var dateTimeLocal = LocalDateTime.parse(dateTimeStr)

    var numAndUnits = getNumAndUnits(numToAdd, selectedUnit)

    var endDateTime by mutableStateOf(dateTimeLocal)

    // why are we suppress Lint NewApi everywhere? Because according
    // to the documentation, all of the java.util.time is available
    // to older APIs now, but the compiler still thinks it's 26 and up.
    // if I'm wrong, I'll have to rewrite all this.

    @SuppressLint("NewApi")
    val onCalculate = {
        startYear = getYearStr(startYear)
        startMonth = getMonthString(startMonth)
        startDay = getDayString(startDay)
        startHour = getHourString(startHour)
        startMin = getMinString(startMin)
        startSec = getSecString(startSec)
        startMilli = getMilliString(startMilli)
        dateStr = validateDate(startYear, startMonth, startDay)
        if (dateStr.endsWith("01")) {
            startDay = getDayString("01")
        }
        timeStr = formatLocalTime(startHour, startMin, startSec, startMilli)
        dateTimeStr = dateStr + "T" + timeStr
        dateTimeLocal = LocalDateTime.parse(dateTimeStr)
        numAndUnits = getNumAndUnits(numToAdd, selectedUnit)
        if (plusMinus) { // true = Plus, false = Minus
            endDateTime = calculatePlus(dateTimeLocal, numAndUnits.first, numAndUnits.second)
        } else {
            endDateTime = calculateMinus(dateTimeLocal, numAndUnits.first, numAndUnits.second)
        }
    }
}