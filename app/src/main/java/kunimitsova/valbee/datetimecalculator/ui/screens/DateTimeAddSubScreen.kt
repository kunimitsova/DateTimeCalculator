package kunimitsova.valbee.datetimecalculator.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kunimitsova.valbee.datetimecalculator.R
import kunimitsova.valbee.datetimecalculator.ui.components.DateItemsInput
import kunimitsova.valbee.datetimecalculator.ui.components.DtcDivider
import kunimitsova.valbee.datetimecalculator.ui.components.PlusMinusButton
import kunimitsova.valbee.datetimecalculator.ui.components.TimeItemsInput
import kunimitsova.valbee.datetimecalculator.ui.theme.DateTimeCalculatorTheme
import kunimitsova.valbee.datetimecalculator.utils.leadingZero
import java.time.temporal.ChronoUnit
import java.time.temporal.TemporalUnit
import java.util.*

@Composable
fun DateTimeScreen(){
    val calendar = Calendar.getInstance()
    var startYear by rememberSaveable { mutableStateOf(calendar[Calendar.YEAR].toString()) }
    var startMonth by rememberSaveable {
        mutableStateOf(leadingZero((calendar[Calendar.MONTH] + 1), 2)) }
    var startDay by rememberSaveable {
        mutableStateOf(leadingZero(calendar[Calendar.DAY_OF_MONTH],2)) }
    var startHour by rememberSaveable {
        mutableStateOf(leadingZero(calendar[Calendar.HOUR_OF_DAY], 2)) }
    var startMin by rememberSaveable {
        mutableStateOf(leadingZero(calendar[Calendar.MINUTE], 2)) }
    var startSec by rememberSaveable {
        mutableStateOf(leadingZero(calendar[Calendar.SECOND], 2)) }
    var startMilli by rememberSaveable {
        mutableStateOf(leadingZero(calendar[Calendar.MILLISECOND], 3)) }

    var showMillis by rememberSaveable { mutableStateOf(false) }
    var plusMinus by rememberSaveable { mutableStateOf(true) }

    var numToAdd by rememberSaveable { mutableStateOf("0") }



    Scaffold(
        topBar = { TopAppBar(title = {
            Row(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .selectableGroup()
            ) {
                Surface(elevation = 1.dp, color = MaterialTheme.colors.primary,
                ) {
                    Text(
                        "Add/Subtract from Date",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .weight(1f)
                    )
                }
                Surface(elevation = 1.dp, color = MaterialTheme.colors.primary) {
                    Text(
                        "Difference between Dates",
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.onPrimary,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .weight(1f)
                            .alpha(0.5f)
                    )
                }
            }
        }) }
    ) {
        Column {
            DateItemsInput(
                startYear = startYear,
                startMonth = startMonth,
                startDay = startDay,
                onYrChange = { startYr -> it },
                onMonthChange = { startMonth -> it },
                onDayChange = { startDay -> it },
            )
            TimeItemsInput(
                startHour = startHour,
                startMin = startMin,
                startSec = startSec,
                startMilli = startMilli,
                onHrChange = { startHour -> it },
                onMinChange = { startMin -> it },
                onSecChange = { startSec -> it },
                onMilliChange = { startMilli -> it },
                showMillis = false
            )
            DtcDivider()
            PlusMinusButton(addDate = plusMinus, onToggle = { addDate -> plusMinus = !plusMinus })
            DtcDivider()
        }
    }
}

@Preview
@Composable
fun APreview() {
    DateTimeCalculatorTheme {
        DateTimeScreen()
    }
}