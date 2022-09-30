package kunimitsova.valbee.datetimecalculator.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kunimitsova.valbee.datetimecalculator.ui.components.reusables.DtcDivider
import kunimitsova.valbee.datetimecalculator.ui.components.segments.AddOrSubtractThis
import kunimitsova.valbee.datetimecalculator.ui.components.segments.CalculateButton
import kunimitsova.valbee.datetimecalculator.ui.components.segments.OutputDateTimeVert
import kunimitsova.valbee.datetimecalculator.utils.DateTimeUnits
import java.time.LocalDateTime

@Composable
fun AddDateBottomHalf(
    numToAdd: String,
    onNumChange: (String) -> Unit,
    selectedUnit: DateTimeUnits,
    expanded: Boolean,
    onBoxClick: () -> Unit,
    onDismissMenu: () -> Unit,
    onClickUnits: (DateTimeUnits) -> Unit,
    onCalculate: () -> Unit,
    endDateTime: LocalDateTime
) {
    Column{
        Spacer(Modifier.height(12.dp))
        AddOrSubtractThis(
            numToAdd = numToAdd,
            onNumChange = onNumChange,
            selectedUnit = selectedUnit,
            expanded = expanded,
            onBoxClick = onBoxClick,
            onDismissMenu = onDismissMenu,
            onClickUnits = onClickUnits,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(8.dp))
        DtcDivider()
        Spacer(modifier = Modifier.height(8.dp))
        CalculateButton(onCalculate = onCalculate,
            modifier = Modifier
                .fillMaxWidth(1f))
   //     TestCrashButton(modifier = Modifier.fillMaxWidth(1f))
        Spacer(modifier = Modifier.height(16.dp))
        OutputDateTimeVert(dateTime = endDateTime)
    }
}