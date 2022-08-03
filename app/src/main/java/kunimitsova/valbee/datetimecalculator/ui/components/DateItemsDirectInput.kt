package kunimitsova.valbee.datetimecalculator.ui.dialogs

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kunimitsova.valbee.datetimecalculator.EntryText
import kunimitsova.valbee.datetimecalculator.ui.theme.DateTimeCalculatorTheme
import java.util.*

@Composable
fun DateItemsDirectInput(){
    // starting date is current date
    // allow direct input of the date in question.
    // later we will put MMM with the month field and
    val calendar = Calendar.getInstance()
    var startYear by rememberSaveable { mutableStateOf(calendar[Calendar.YEAR].toString()) }
    var startMonth by remember { mutableStateOf((calendar[Calendar.MONTH] + 1).toString()) }
    var startDay by remember { mutableStateOf(calendar[Calendar.DAY_OF_MONTH].toString()) }

    Row(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(1f)
    ) {

        EntryText(
            text = startYear, label = "Year", onChanged = { startYear = it },
            modifier = Modifier.weight(0.8f)
        )
        EntryText(
            onChanged = { startMonth = it }, text = startMonth, label = "Month",
            modifier = Modifier.weight(1f)
        )
        EntryText(
            onChanged = { startDay = it }, text = startDay, label = "Day",
            modifier = Modifier.weight(0.5f)
        )
    }
}

@Preview
@Composable
fun DirectInputPreview() {
    DateTimeCalculatorTheme {
        DateItemsDirectInput()
    }
}