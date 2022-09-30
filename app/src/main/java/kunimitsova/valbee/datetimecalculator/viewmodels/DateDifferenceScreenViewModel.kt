package kunimitsova.valbee.datetimecalculator.viewmodels

import android.annotation.SuppressLint
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kunimitsova.valbee.datetimecalculator.utils.*
import kunimitsova.valbee.datetimecalculator.utils.functions.calculateDifference
import kunimitsova.valbee.datetimecalculator.utils.functions.getDayString
import kunimitsova.valbee.datetimecalculator.utils.functions.getMonthString
import kunimitsova.valbee.datetimecalculator.utils.functions.getYearStr
import kunimitsova.valbee.datetimecalculator.utils.functions.leadingZero
import kunimitsova.valbee.datetimecalculator.utils.functions.validateDate
import java.time.LocalDateTime
import java.util.Calendar

/**
 * Eventually the time elements will be included but for now it's just dates.
 */

class DateDifferenceScreenViewModel: ViewModel() {
    private val calendar = Calendar.getInstance()
    private val _year1 = mutableStateOf(calendar[Calendar.YEAR].toString())
    val year1: State<String> = _year1
    fun updateYear1(year: String) {
        _year1.value = year
    }

    private val _year2 = mutableStateOf(year1.value)
    val year2: State<String> = _year2
    fun updateYear2(year: String) {
        _year2.value = year
    }
// add plus 1 because Calendar.MONTH is in 0..11 range.
    private val _month1 = mutableStateOf(leadingZero(calendar[Calendar.MONTH] + 1, 2))
    val month1: State<String> = _month1
    fun updateMonth1(month: String) {
        _month1.value = month
    }

    private val _month2 = mutableStateOf(month1.value)
    val month2: State<String> = _month2
    fun updateMonth2(month: String) {
        _month2.value = month
    }

    private val _day1 = mutableStateOf(leadingZero(calendar[Calendar.DAY_OF_MONTH],2))
    val day1: State<String> = _day1
    fun updateDay1(day: String) {
        _day1.value = day
    }

    private val _day2 = mutableStateOf(day1.value)
    val day2: State<String> = _day2
    fun updateDay2(day: String) {
        _day2.value = day
    }

    private val _selectedUnit = mutableStateOf(DateTimeUnits.DAY)
    val selectedUnit: State<DateTimeUnits> = _selectedUnit
    fun updateSelectedUnit(dtuUnit: DateTimeUnits) {
        _selectedUnit.value = dtuUnit
    }

    private val _answer = mutableStateOf(0L)
    val answer: State<Long> = _answer
    fun updateAnswer(numL: Long) {
        _answer.value = numL
    }


    fun getNewAnswer(): Long {
        return calculateDifference(getTwoDates().first, getTwoDates().second, selectedUnit.value)
    }
// eventually it will be DateTime but right now just fake the time with 00:00
    @SuppressLint("NewApi")
    fun getTwoDates(): Pair<LocalDateTime, LocalDateTime> {
        val date1 = validateDate(year1.value, month1.value, day1.value)
        val date2 = validateDate(year2.value, month2.value, day2.value)
        var datestr = date1 + "T00:00"
        val dateTime1 = LocalDateTime.parse(datestr)
        datestr = date2 + "T00:00"
        val dateTime2 = LocalDateTime.parse(datestr)
        return Pair(dateTime1, dateTime2)
    }

    @SuppressLint("NewApi")
    fun updateTwoDates() {
        val date1 = getTwoDates().first
        val date2 = getTwoDates().second
        updateYear1(getYearStr(date1.year.toString()))
        updateMonth1(getMonthString(date1.monthValue.toString()))
        // if the date is 01 then the internal day may have been changed by validateDate
        // so make sure the UI is updated with the assumed value
        val strDay1 = if (date1.dayOfMonth == 1) "01" else
            getDayString(date1.dayOfMonth.toString())
        updateDay1(strDay1)
        updateYear2(getYearStr(date2.year.toString()))
        updateMonth2(getMonthString(date2.monthValue.toString()))
        val strDay2 = if(date2.dayOfMonth == 1) "01" else
            getDayString(date2.dayOfMonth.toString())
        updateDay2(strDay2)
//        updateStartHour(getHourString(ldtDate.hour.toString()))
//        updateStartMin(getMinString(ldtDate.minute.toString()))
//        updateStartSec(getSecString(ldtDate.second.toString()))
//        // there would be 9 nanos digits for there to be 3 millis digits, the GetMilliString is
//        // designed to take the top 3 because that's how it is right now. Millis are not really
//        // implemented but here they are so .
//        val strNano = leadingZero(ldtDate.nano.toString(), 9)
//        updateStartMilli(getMilliString(strNano))
    }
}