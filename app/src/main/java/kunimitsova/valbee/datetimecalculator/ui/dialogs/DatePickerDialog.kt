package kunimitsova.valbee.datetimecalculator.ui.dialogs

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
import androidx.compose.ui.unit.dp
import kunimitsova.valbee.datetimecalculator.R
import kunimitsova.valbee.datetimecalculator.ui.theme.DateTimeCalculatorTheme
import java.util.*

// tbd - later add option for user to use the dialogs rather than direct number entry.

//@Composable
//fun DateBlock(modifier: Modifier = Modifier ) {
//    val context = LocalContext.current
//    val calendar = Calendar.getInstance()
//    val startDay = calendar[Calendar.DAY_OF_MONTH]
//    val startMonth = calendar[Calendar.MONTH]
//    val startYear = calendar[Calendar.YEAR]
//    val startHour = calendar[Calendar.HOUR_OF_DAY]
//    val startMin = calendar[Calendar.MINUTE]
//    val startSec = calendar[Calendar.SECOND]
//    val startMilli = calendar[Calendar.MILLISECOND]
//    val startDate = DateClassic(
//        day = startDay,
//        month = startMonth+1,
//        year = startYear,
//        hour = startHour,
//        minute = startMin,
//        second = startSec,
//        millis = startMilli
//    )
//    var pickedDate by remember { mutableStateOf(startDate.toString()) }
//
//    val datePickerDialog = DatePickerDialog(
//        context,
//        { _: DatePicker, sYear: Int, sMonth: Int, sDay: Int ->
//            pickedDate = DateClassic(day = sDay,
//                month = sMonth, year = sYear).toString()},
//        startYear,
//        startMonth,
//        startDay
//    )
//
//    androidx.compose.material.Surface(elevation = 3.dp) {
//        Row(modifier = Modifier
//            .padding(8.dp)
//            .clickable(enabled = true, onClickLabel = "select date") { datePickerDialog.show() }
//        ) {
//            Text(
//                text = pickedDate,
//                style = MaterialTheme.typography.h6
//            )
//            Spacer(modifier = Modifier.width(8.dp))
//            Icon(
//                painter = painterResource(id = R.drawable.ic_baseline_calendar_month_24),
//                contentDescription = ""
//            )
//        }
//    }
//
//}

//@Preview
//@Composable
//fun DateDiaPreview() {
//    DateTimeCalculatorTheme {
//        DateBlock(modifier = Modifier.fillMaxWidth(1f))
//    }
//}