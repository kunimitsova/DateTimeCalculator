package kunimitsova.valbee.datetimecalculator.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kunimitsova.valbee.datetimecalculator.R
import kunimitsova.valbee.datetimecalculator.ui.theme.DateTimeCalculatorTheme
import kunimitsova.valbee.datetimecalculator.utils.DateTimeUnits

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
            text = startHour, label = stringResource(id = R.string.hour),
            onChanged = onHrChange,
            modifier = Modifier.weight(1f)
        )
        TimeDivider()
        EntryText(
            text = startMin, label = stringResource(id = R.string.minute),
            onChanged = onMinChange,
            modifier = Modifier.weight(1f)
        )
        TimeDivider()
        EntryText(
            text = startSec, label = stringResource(id = R.string.second),
            onChanged = onSecChange,
            modifier = Modifier.weight(1f)
        )
        if (showMillis) {
            EntryText(
                text = startMilli,
                label = stringResource(id = R.string.Millis),
                onChanged = onMilliChange,
                modifier = Modifier.weight(1f)
            )
        }
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