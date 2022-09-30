package kunimitsova.valbee.datetimecalculator.ui.components.segments

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kunimitsova.valbee.datetimecalculator.R
import kunimitsova.valbee.datetimecalculator.ui.components.reusables.EntryText
import kunimitsova.valbee.datetimecalculator.ui.components.reusables.LittleText
import kunimitsova.valbee.datetimecalculator.ui.theme.DateTimeCalculatorTheme
import kunimitsova.valbee.datetimecalculator.utils.DateTimeUnits

@Composable
fun AddOrSubtractThis(numToAdd: String, onNumChange: (String) -> Unit ,
                      selectedUnit: DateTimeUnits,
                      expanded: Boolean,
                      onBoxClick: () -> Unit,
                      onDismissMenu: () -> Unit,
                      onClickUnits: (DateTimeUnits) -> Unit,
                      modifier: Modifier = Modifier
) {
    // modifier is for the EntryText
    Row(verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(start = 8.dp)
            .fillMaxWidth(1f)) {
        EntryText(text = numToAdd ,
            label = stringResource(id = R.string.numToCalculate),
            onChanged = onNumChange,
            modifier = modifier.weight(1f)
        )
        Spacer(modifier = Modifier.width(4.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 4.dp)
        ) {
            LittleText(text = stringResource(id = R.string.add_or_subtract_units),
                modifier = Modifier
            )
            UnitsSpinner(
                selectedUnit = selectedUnit,
                expanded = expanded,
                onBoxClick = onBoxClick,
                onDismissMenu = onDismissMenu,
                onClickUnits = onClickUnits
            )
        }
    }
}

@Preview
@Composable
fun AstPreview() {
    var selectedUnit by remember { mutableStateOf(DateTimeUnits.DAY) }
    var expanded by remember { mutableStateOf(false) }
    val onBoxClick = { expanded = !expanded }
    val onDismissMenu = { expanded = false }
    val onItemClick = { it: DateTimeUnits ->
        selectedUnit = it
        expanded = false
    }
    DateTimeCalculatorTheme {
        AddOrSubtractThis(numToAdd = "123", onNumChange = {it},
            selectedUnit = selectedUnit,
            expanded = expanded,
            onBoxClick = onBoxClick,
            onDismissMenu = onDismissMenu,
            onClickUnits = onItemClick
        )
    }
}