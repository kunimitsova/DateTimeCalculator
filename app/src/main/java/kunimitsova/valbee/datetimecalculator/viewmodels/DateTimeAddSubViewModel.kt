package kunimitsova.valbee.datetimecalculator.viewmodels

import android.annotation.SuppressLint
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
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
    private val _startYear = mutableStateOf(calendar[Calendar.YEAR].toString())
    val startYear: State<String> = _startYear
    fun updateStartYear(strYear: String) {
        _startYear.value = strYear
    }

    private val _startMonth = mutableStateOf(leadingZero((calendar[Calendar.MONTH] + 1), 2))
    val startMonth: State<String> = _startMonth
    fun updateStartMonth(strMonth: String) {
        _startMonth.value = strMonth
    }

    private val _startDay = mutableStateOf(leadingZero(calendar[Calendar.DAY_OF_MONTH],2))
    val startDay: State<String> = _startDay
    fun updateStartDay(strDay: String) {
        _startDay.value = strDay
    }

    private val _startHour = mutableStateOf(leadingZero(calendar[Calendar.HOUR_OF_DAY], 2))
    val startHour: State<String> = _startHour
    fun updateStartHour(strHour: String) {
        _startHour.value = strHour
    }

    private val _startMin = mutableStateOf(leadingZero(calendar[Calendar.MINUTE], 2))
    val startMin: State<String> = _startMin
    fun updateStartMin(strMin: String) {
        _startMin.value = strMin
    }

    private val _startSec = mutableStateOf(leadingZero(calendar[Calendar.SECOND], 2))
    val startSec: State<String> = _startSec
    fun updateStartSec(strSec: String) {
        _startSec.value = strSec
    }

    private val _startMilli = mutableStateOf(leadingZero(calendar[Calendar.MILLISECOND], 3))
    val startMilli: State<String> = _startMilli
    fun updateStartMilli(strMilli: String) {
        _startMilli.value = strMilli
    }

// this will be implemented later
//    var showMillis by mutableStateOf(false)

    // state of the toggle button for add(true) subtract(false)
    private val _plusMinus = mutableStateOf(true)
    val plusMinus: State<Boolean> = _plusMinus
    fun updatePlusMinus(boolPlus: Boolean) {
        _plusMinus.value = boolPlus
    }

    // state of the number of [units] to add/subtract
    private val _numToAdd = mutableStateOf("0")
    val numToAdd: State<String> = _numToAdd
    fun updateNumToAdd(strNum: String) {
        _numToAdd.value = strNum
    }
    // state of unit selector
    private val _selectedUnit = mutableStateOf(DateTimeUnits.DAY)
    val selectedUnit: State<DateTimeUnits> = _selectedUnit
    fun updateSelectedUnit(dtuUnit: DateTimeUnits) {
        _selectedUnit.value = dtuUnit
    }

    @SuppressLint("NewApi")
    fun getDateTimeLocal(): LocalDateTime {
        var dateStr = validateDate(startYear.value, startMonth.value, startDay.value)
        var timeStr = formatLocalTime(
            startHour.value, startMin.value,
            startSec.value, startMilli.value
        )
        var dateTimeStr = dateStr + "T" + timeStr

        return LocalDateTime.parse(dateTimeStr)
    }

    @SuppressLint("NewApi")
    fun updateDateTimeFields(ldtDate: LocalDateTime) {
        updateStartYear(getYearStr(ldtDate.year.toString()))
        updateStartMonth(getMonthString(ldtDate.monthValue.toString()))
        // if the date is 01 then the internal day may have been changed by validateDate
        // so make sure the UI is updated with the assumed value
        val strDay = if (ldtDate.dayOfMonth == 1) "01" else
            getDayString(ldtDate.dayOfMonth.toString())
        updateStartDay(strDay = strDay)
        updateStartHour(getHourString(ldtDate.hour.toString()))
        updateStartMin(getMinString(ldtDate.minute.toString()))
        updateStartSec(getSecString(ldtDate.second.toString()))
        // there would be 9 nanos digits for there to be 3 millis digits, the GetMilliString is
        // designed to take the top 3 because that's how it is right now. Millis are not really
        // implemented but here they are so .
        val strNano = leadingZero(ldtDate.nano.toString(), 9)
        updateStartMilli(getMilliString(strNano))
        updateNumToAdd(validFloat(numToAdd.value))
    }
    var startLocalDateTime = getDateTimeLocal()

    fun calculatedDate(): LocalDateTime {
        val numAndUnits = getNumAndUnits(numToAdd.value, selectedUnit.value)
        // plusMInus = TRUE for addition, FALSE for subtraction.
        if (plusMinus.value) {
            return calculatePlus(getDateTimeLocal(), numAndUnits.first, numAndUnits.second)
        } else {
            return calculateMinus(getDateTimeLocal(), numAndUnits.first, numAndUnits.second)
        }
    }

    private val _endDateTime = mutableStateOf(startLocalDateTime)
    val endDateTime: State<LocalDateTime> = _endDateTime
    fun updateEndDateTime(ldtFinal: LocalDateTime) {
        _endDateTime.value = ldtFinal
    }



}