package kunimitsova.valbee.datetimecalculator.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import kunimitsova.valbee.datetimecalculator.R
import kunimitsova.valbee.datetimecalculator.ui.components.reusables.BigText
import kunimitsova.valbee.datetimecalculator.ui.components.reusables.LittleText
import kunimitsova.valbee.datetimecalculator.ui.components.segments.CalculateButton
import kunimitsova.valbee.datetimecalculator.ui.components.segments.OutputDiff
import kunimitsova.valbee.datetimecalculator.ui.components.segments.UnitsSpinner
import kunimitsova.valbee.datetimecalculator.utils.DateTimeUnits

@Composable
fun DateDiffBottomHalf(
    selectedUnit: DateTimeUnits,
    expanded: Boolean,
    onExpandClick: () -> Unit,
    onDismissMenu: () -> Unit,
    onClickSpinner: (DateTimeUnits) -> Unit,
    onCalculate: () -> Unit,
    answer: Long
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth(1f)
        ) {
            BigText(
                text = stringResource(id = R.string.how_many),
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.End
            )
            Spacer(modifier = Modifier.width(4.dp))
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                LittleText(text = stringResource(id = R.string.difference_units))
                UnitsSpinner(
                    selectedUnit = selectedUnit,
                    expanded = expanded,
                    textAbove = stringResource(id = R.string.add_or_subtract_units),
                    onBoxClick = onExpandClick,
                    onDismissMenu = onDismissMenu,
                    onClickUnits = onClickSpinner
                )
            }
        }
        CalculateButton(modifier = Modifier.fillMaxWidth(1f), onCalculate = onCalculate)
        Spacer(modifier = Modifier.height(24.dp))
        OutputDiff(answer, selectedUnit)
    }
}