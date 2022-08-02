package kunimitsova.valbee.datetimecalculator.dialogs

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kunimitsova.valbee.datetimecalculator.DateClassic
import kunimitsova.valbee.datetimecalculator.R
import kunimitsova.valbee.datetimecalculator.ui.theme.DateTimeCalculatorTheme
import java.util.*

// I may not use this because I kindof don't like the interface.

@Composable
fun DateBlock(modifier: Modifier = Modifier ) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val startDay = calendar[Calendar.DAY_OF_MONTH]
    val startMonth = calendar[Calendar.MONTH]
    val startYear = calendar[Calendar.YEAR]
    val startDate = DateClassic(
        day = startDay,
        month = startMonth+1,
        year = startYear
    )
    var pickedDate by remember { mutableStateOf(startDate.toString()) }

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, sYear: Int, sMonth: Int, sDay: Int ->
            pickedDate = DateClassic(day = sDay,
                month = sMonth, year = sYear).toString()},
        startYear,
        startMonth,
        startDay
    )

    androidx.compose.material.Surface(elevation = 3.dp) {
        Row(modifier = Modifier
            .padding(8.dp)
            .clickable(enabled = true, onClickLabel = "select date") { datePickerDialog.show() }
        ) {
            Text(
                text = pickedDate,
                style = MaterialTheme.typography.h6
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_calendar_month_24),
                contentDescription = ""
            )
        }
    }

}

//@Preview
@Composable
fun DateDiaPreview() {
    DateTimeCalculatorTheme {
        DateBlock(modifier = Modifier.fillMaxWidth(1f))
    }
}