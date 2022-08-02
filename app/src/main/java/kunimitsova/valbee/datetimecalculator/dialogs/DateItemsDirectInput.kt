package kunimitsova.valbee.datetimecalculator.dialogs

import androidx.compose.foundation.layout.*
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kunimitsova.valbee.datetimecalculator.EntryText
import kunimitsova.valbee.datetimecalculator.ui.theme.DateTimeCalculatorTheme
import java.util.*

@Composable
fun dateItemsDirectInput(){
    val calendar = Calendar.getInstance()
    var startYear by remember { mutableStateOf("") }
    var startMonth by remember { mutableStateOf((calendar[Calendar.MONTH] + 1).toString()) }
    var startDay by remember { mutableStateOf(calendar[Calendar.DAY_OF_MONTH].toString()) }

    startYear = calendar[Calendar.YEAR].toString()

    Row(modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth(1f)) {

        EntryText(text = startYear, label = "Year", onChanged = { startYear = it },
            modifier = Modifier.weight(1f))
        OutlinedTextField(
            value = startMonth,
            onValueChange = { startMonth = it },
            label = { Text("Month") },
            modifier = Modifier.weight(1f)
        )
//        EntryText(onChanged = { startMonth = it }, text = startMonth, label = "Month",
//            modifier = Modifier.weight(1f))
//        EntryText(onChanged = { startDay = it }, text = startDay, label = "Day",
//            modifier = Modifier.weight(1f))
        }
}

@Preview
@Composable
fun directInputPreview() {
    DateTimeCalculatorTheme {
        dateItemsDirectInput()
    }
}