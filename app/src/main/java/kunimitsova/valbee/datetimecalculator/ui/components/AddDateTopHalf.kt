package kunimitsova.valbee.datetimecalculator.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kunimitsova.valbee.datetimecalculator.R
import kunimitsova.valbee.datetimecalculator.ui.components.reusables.DtcDivider
import kunimitsova.valbee.datetimecalculator.ui.components.segments.DateItemsInput
import kunimitsova.valbee.datetimecalculator.ui.components.segments.PlusMinusButton
import kunimitsova.valbee.datetimecalculator.ui.components.segments.TimeItemsInput

@Composable
fun AddDateTopHalf(
    startYear: String,
    startMonth: String,
    startDay: String,
    startHour: String,
    startMin: String,
    startSec: String,
    startMilli: String,
    onYrChange: (String) -> Unit,
    onMonthChange: (String) -> Unit,
    onDayChange: (String) -> Unit,
    onHourChange: (String) -> Unit,
    onMinChange: (String) -> Unit,
    onSecChange: (String) -> Unit,
    onMilliChange: (String) -> Unit,
    plusMinus: Boolean,
    onToggle: (Boolean) -> Unit
) {
    Column {
        Spacer(modifier = Modifier.height(8.dp))
        DateItemsInput(
            startYear = startYear,
            startMonth = startMonth,
            startDay = startDay,
            yearLabel = stringResource(id = R.string.year),
            monthLabel = stringResource(id = R.string.month),
            dayLabel = stringResource(id = R.string.day),
            onYrChange = onYrChange,
            onMonthChange = onMonthChange,
            onDayChange = onDayChange,
            modifier = Modifier
        )
        TimeItemsInput(
            startHour = startHour,
            startMin = startMin,
            startSec = startSec,
            startMilli = startMilli,
            hourLabel = stringResource(id = R.string.hour),
            minLabel = stringResource(id = R.string.minute),
            secLabel = stringResource(id = R.string.second),
            milliLabel = stringResource(id = R.string.Millis),
            onHrChange = onHourChange,
            onMinChange = onMinChange,
            onSecChange = onSecChange,
            onMilliChange = onMilliChange,
            showMillis = false,
            modifier = Modifier
        )
        DtcDivider()
        PlusMinusButton(
            addDate = plusMinus,
            onToggle = onToggle
        )
        DtcDivider()
    }
}