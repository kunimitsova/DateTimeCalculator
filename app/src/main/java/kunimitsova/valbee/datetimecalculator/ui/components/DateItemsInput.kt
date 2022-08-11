package kunimitsova.valbee.datetimecalculator.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kunimitsova.valbee.datetimecalculator.ui.theme.DateTimeCalculatorTheme
import kunimitsova.valbee.datetimecalculator.R
import kunimitsova.valbee.datetimecalculator.utils.leadingZero
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
            text = startYear, label = stringResource(id = R.string.year),
            onChanged = onYrChange ,
            modifier = Modifier.weight(1f)
        )
        DateDivider()
        EntryText(
            text = startMonth, label = stringResource(id = R.string.month),
            onChanged = onMonthChange,
            modifier = Modifier.weight(0.8f)
        )
        DateDivider()
        EntryText(
            text = startDay, label = stringResource(id = R.string.day),
            onChanged = onDayChange,
            modifier = Modifier.weight(0.8f)
        )
    }
}
@Preview
@Composable
fun DateItemsInputPreview() {
    DateTimeCalculatorTheme {
        DateItemsInput(
            startYear = "2022",
            startMonth = "08",
            startDay = "07",
            onYrChange = { },
            onMonthChange = {  },
            onDayChange = {  }
        )
    }
}