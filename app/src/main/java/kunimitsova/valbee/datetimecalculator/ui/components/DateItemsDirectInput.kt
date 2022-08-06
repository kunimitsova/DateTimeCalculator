package kunimitsova.valbee.datetimecalculator.ui.components

import android.content.res.Configuration.UI_MODE_NIGHT_NO
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.graphics.Color
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kunimitsova.valbee.datetimecalculator.ui.components.EntryText
import kunimitsova.valbee.datetimecalculator.ui.theme.DateTimeCalculatorTheme
import kunimitsova.valbee.datetimecalculator.utils.DateTimeUnits
import java.util.*

@Composable
fun DateItemsInput(
    startYear: String,  startMonth: String, startDay: String,
    onYrChange: (String) -> Unit,
    onMonthChange: (String) -> Unit,
    onDayChange: (String) -> Unit,
) {
    // starting date is current date
    Row(
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier.padding(16.dp)
    ) {
        EntryText(
            text = startYear, label = DateTimeUnits.YEAR.friendlyName,
            onChanged = onYrChange,
            modifier = Modifier.weight(1f)
        )
        DateDivider()
        EntryText(
            text = startMonth, label = DateTimeUnits.MONTH.friendlyName,
            onChanged = onMonthChange,
            modifier = Modifier.weight(0.8f)
        )
        DateDivider()
        EntryText(
            text = startDay, label = DateTimeUnits.DAY.friendlyName,
            onChanged = onDayChange,
            modifier = Modifier.weight(0.8f)
        )
    }
}

@Composable
fun TimeItemsInput(
    startHour: String, startMin: String, startSec: String, startMilli: String,
    onHrChange: (String) -> Unit,
    onMinChange: (String) -> Unit,
    onSecChange: (String) -> Unit,
    onMilliChange: (String) -> Unit,
    showMillis: Boolean,
) {
    // Milliseconds will be implemented later.
    Row(verticalAlignment = Alignment.Bottom,
        modifier = Modifier.padding(16.dp)) {
        EntryText(
            text = startHour, label = DateTimeUnits.HOUR.friendlyName,
            onChanged = onHrChange,
            modifier = Modifier.weight(1f)
        )
        TimeDivider()
        EntryText(
            text = startMin, label = DateTimeUnits.MINUTE.friendlyName,
            onChanged = onMinChange,
            modifier = Modifier.weight(1f)
        )
        TimeDivider()
        EntryText(
            text = startSec, label = DateTimeUnits.SECOND.friendlyName,
            onChanged = onSecChange,
            modifier = Modifier.weight(1f)
        )
        if (showMillis) {
            EntryText(
                text = startMilli,
                label = DateTimeUnits.MILLISECOND.friendlyName,
                onChanged = onMilliChange,
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview
@Composable
fun DateItemsInputPreview() {
    DateTimeCalculatorTheme {
        DateItemsInput(
            startYear = "2022",
            startMonth = "08",
            startDay = "22",
            onYrChange = {},
            onMonthChange = {},
            onDayChange = {}
        )
    }
}

@Preview
@Composable
fun TimeItemsInputPreview() {
    DateTimeCalculatorTheme {
        TimeItemsInput(
            startHour = "04",
            startMin = "33",
            startSec = "38",
            startMilli = "125",
            onHrChange = {},
            onMinChange = {},
            onSecChange = {},
            onMilliChange = {},
            showMillis = false
        )
    }
}