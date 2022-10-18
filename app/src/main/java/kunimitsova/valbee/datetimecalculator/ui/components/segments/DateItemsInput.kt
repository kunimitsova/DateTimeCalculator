@file:OptIn(ExperimentalComposeUiApi::class, ExperimentalComposeUiApi::class)

package kunimitsova.valbee.datetimecalculator.ui.components.segments

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kunimitsova.valbee.datetimecalculator.R
import kunimitsova.valbee.datetimecalculator.ui.components.reusables.DateDivider
import kunimitsova.valbee.datetimecalculator.ui.components.reusables.EntryText

@Composable
fun DateItemsInput(
    startYear: String,  startMonth: String, startDay: String,
    yearLabel: String, monthLabel: String, dayLabel: String,
    onYrChange: (String) -> Unit,
    onMonthChange: (String) -> Unit,
    onDayChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    // starting date is current date
    // modifier is for EVERY SINGLE entryText
    Row(
        verticalAlignment = Alignment.Bottom,
        modifier = Modifier.padding(4.dp)
    ) {
        EntryText(
            text = startYear, label = yearLabel,
            onChanged = onYrChange ,
            modifier = modifier.weight(0.8f)
        )
        DateDivider()
        EntryText(
            text = startMonth, label = monthLabel,
            onChanged = onMonthChange,
            modifier = modifier.weight(0.6f)
        )
        DateDivider()
        EntryText(
            text = startDay, label = dayLabel,
            onChanged = onDayChange,
            modifier = modifier.weight(0.6f)
        )
    }
}
//@Preview
//@Composable
//fun DateItemsInputPreview() {
//    DateTimeCalculatorTheme {
//        DateItemsInput(
//            startYear = "2022",
//            startMonth = "08",
//            startDay = "07",
//            onYrChange = { },
//            onMonthChange = {  },
//            onDayChange = {  }
//        )
//    }
//}