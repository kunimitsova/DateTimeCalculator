@file:OptIn(ExperimentalComposeUiApi::class)

package kunimitsova.valbee.datetimecalculator.ui.components.segments

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kunimitsova.valbee.datetimecalculator.R
import kunimitsova.valbee.datetimecalculator.ui.components.reusables.EntryText
import kunimitsova.valbee.datetimecalculator.ui.components.reusables.TimeDivider
import kunimitsova.valbee.datetimecalculator.ui.theme.DateTimeCalculatorTheme

@Composable
fun TimeItemsInput(
    startHour: String, startMin: String, startSec: String, startMilli: String,
    hourLabel: String, minLabel: String, secLabel: String, milliLabel: String,
    onHrChange: (String) -> Unit,
    onMinChange: (String) -> Unit,
    onSecChange: (String) -> Unit,
    onMilliChange: (String) -> Unit,
    showMillis: Boolean,
    modifier: Modifier = Modifier
) {
    // Milliseconds will be implemented later.
    Row(verticalAlignment = Alignment.Bottom,
        modifier = Modifier.padding(4.dp)) {
        EntryText(
            text = startHour, label = hourLabel,
            onChanged = onHrChange,
            modifier = modifier.weight(1f)
        )
        TimeDivider()
        EntryText(
            text = startMin, label = minLabel,
            onChanged = onMinChange,
            modifier = modifier.weight(1f)
        )
        TimeDivider()
        EntryText(
            text = startSec, label = secLabel,
            onChanged = onSecChange,
            modifier = modifier.weight(1f)
        )
        if (showMillis) {
            EntryText(
                text = startMilli,
                label = milliLabel,
                onChanged = onMilliChange,
                modifier = modifier.weight(1f)
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
            hourLabel = "Hour",
            minLabel = "Minute",
            secLabel = "Second",
            milliLabel = "Millis",
            onHrChange = {},
            onMinChange = {},
            onSecChange = {},
            onMilliChange = {},
            showMillis = false
        )
    }
}