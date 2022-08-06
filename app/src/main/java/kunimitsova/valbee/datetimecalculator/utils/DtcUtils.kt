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
import java.time.format.DateTimeFormatter
import java.util.Date

/**
 * utilities, plus some code testing
 */

fun leadingZero(num: Int, maxDigits: Int): String {
    val numString = num.toString()
    var len = numString.length
    var exitString = numString
    while (len < maxDigits) {
        exitString = "0" + exitString
        len++
    }
    return exitString
}

@SuppressLint("NewApi")
fun addWhatever(dateStr: String, numToAdd: String, units: DateTimeUnits) {
    val dateStart = DateClassic.fromString(dateStr)
    val dateDate = LocalDate.of(dateStart.year, dateStart.month, dateStart.day)
    var setupNum = numToAdd.toFloat()
    var setupUnits = units
    // first get the new number to add, before updating the actual units.
    while ((setupNum.toInt().toFloat() != setupNum) and (setupUnits != DateTimeUnits.MILLISECOND)) {
        setupNum = setupUnits.nextValue(setupNum)
        setupUnits = setupUnits.getNext()
    }
    val date1: LocalDateTime = LocalDateTime.parse(dateStr)
}

