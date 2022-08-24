package kunimitsova.valbee.datetimecalculator.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kunimitsova.valbee.datetimecalculator.utils.DateTimeUnits

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