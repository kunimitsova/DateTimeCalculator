package kunimitsova.valbee.datetimecalculator.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import kunimitsova.valbee.datetimecalculator.R
import kunimitsova.valbee.datetimecalculator.utils.DateTimeUnits
import java.time.LocalDateTime

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
    Column() {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(1f)
        ) {
            BigText(
                text = stringResource(id = R.string.how_many),
                modifier = Modifier.weight(1f)
            )
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                LittleText(text = stringResource(id = R.string.difference_units))
                UnitsSpinner(
                    selectedUnit = selectedUnit,
                    expanded = expanded,
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