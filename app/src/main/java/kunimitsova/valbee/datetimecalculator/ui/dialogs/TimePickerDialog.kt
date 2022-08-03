package kunimitsova.valbee.datetimecalculator.ui.dialogs

import android.app.TimePickerDialog
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kunimitsova.valbee.datetimecalculator.R
import kunimitsova.valbee.datetimecalculator.ui.theme.DateTimeCalculatorTheme
import java.util.*

@Composable
fun TimeBlock(modifier: Modifier = Modifier ) {
    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    val startHour = calendar[Calendar.HOUR_OF_DAY]
    val startMin = calendar[Calendar.MINUTE]
    val pickedTime = remember { mutableStateOf("--:--") }

    val timePickerDialog = TimePickerDialog(
        context,
        {_, startHour: Int, startMin: Int ->
            pickedTime.value = "$startHour:$startMin"
        },
        startHour,
        startMin,
        true
    )
    androidx.compose.material.Surface(elevation = 3.dp) {
        Row(modifier = Modifier
            .padding(8.dp)
            .clickable(enabled = true, onClickLabel = "select time") { timePickerDialog.show() }
        ) {
            Text(
                text = pickedTime.value,
                style = MaterialTheme.typography.h6
            )
            Spacer(modifier = Modifier.width(8.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_baseline_access_time_filled_24),
                contentDescription = ""
            )
        }
    }

}

@Preview
@Composable
fun TimeDiaPreview() {
    DateTimeCalculatorTheme {
        TimeBlock(modifier = Modifier.fillMaxWidth(1f))
    }
}