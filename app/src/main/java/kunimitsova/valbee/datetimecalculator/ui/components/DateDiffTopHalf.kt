package kunimitsova.valbee.datetimecalculator.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kunimitsova.valbee.datetimecalculator.R
import kunimitsova.valbee.datetimecalculator.ui.components.reusables.HeaderText
import kunimitsova.valbee.datetimecalculator.ui.components.segments.DateItemsInput

@Composable
fun DateDiffTopHalf(
    year1: String,
    month1: String,
    day1: String,
    year2: String,
    month2: String,
    day2: String,
    onYr1Change: (String) -> Unit,
    onYr2Change: (String) -> Unit,
    onMonth1Change: (String) -> Unit,
    onMonth2Change: (String) -> Unit,
    onDay1Change: (String) -> Unit,
    onDay2Change: (String) -> Unit
) {
    Column {
        Row(modifier = Modifier.padding(vertical = 4.dp)) {
            HeaderText(text = "Date 1")
        }
        DateItemsInput(
            startYear = year1,
            startMonth = month1,
            startDay = day1,
            yearLabel = stringResource(id = R.string.year1),
            monthLabel = stringResource(id = R.string.month1),
            dayLabel = stringResource(id = R.string.day1),
            onYrChange = onYr1Change,
            onMonthChange = onMonth1Change,
            onDayChange = onDay1Change
        )

        Row(modifier = Modifier.padding(vertical = 4.dp)) {
            HeaderText(text = "Date 2")
        }
        DateItemsInput(
            startYear = year2,
            startMonth = month2,
            startDay = day2,
            yearLabel = stringResource(id = R.string.year2),
            monthLabel = stringResource(id = R.string.month2),
            dayLabel = stringResource(id = R.string.day2),
            onYrChange = onYr2Change,
            onMonthChange = onMonth2Change,
            onDayChange = onDay2Change
        )
    }
}