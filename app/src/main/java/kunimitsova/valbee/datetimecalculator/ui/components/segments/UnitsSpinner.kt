package kunimitsova.valbee.datetimecalculator.ui.components.segments

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kunimitsova.valbee.datetimecalculator.R
import kunimitsova.valbee.datetimecalculator.ui.components.reusables.BigText
import kunimitsova.valbee.datetimecalculator.ui.components.reusables.LittleText
import kunimitsova.valbee.datetimecalculator.ui.theme.DateTimeCalculatorTheme
import kunimitsova.valbee.datetimecalculator.utils.DateTimeUnits

@Composable
fun UnitsSpinner(
    selectedUnit: DateTimeUnits,
    expanded: Boolean,
    textAbove: String,
    onBoxClick: () -> Unit,
    onDismissMenu: () -> Unit,
    onClickUnits: (DateTimeUnits) -> Unit
){
    val popupDescription = stringResource(id = R.string.unitlist)
    Column(modifier = Modifier
        .semantics(mergeDescendants = true) {}
        .clickable(
            onClick = onBoxClick,
            onClickLabel = stringResource(id = R.string.select_time_units),
            role = Role.Button
        )
    ) {
        LittleText(text = textAbove, modifier = Modifier)
        Box() {
            Surface(
                elevation = 1.dp,
                shape = MaterialTheme.shapes.large,
                color = MaterialTheme.colors.surface,
                contentColor = MaterialTheme.colors.onSurface,
                modifier = Modifier
                    .requiredWidth(220.dp)
            ) {
                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(10.dp)
                        .animateContentSize(
                            animationSpec = spring(
                                dampingRatio = Spring.DampingRatioMediumBouncy,
                                stiffness = Spring.StiffnessLow
                            )
                        )
                ) {
                    BigText(text = stringResource(selectedUnit.friendlyNameID))
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = onDismissMenu,
                        modifier = Modifier.semantics { if (expanded) contentDescription = popupDescription else null }
                    ) {
                        enumValues<DateTimeUnits>().forEach {
                            DropdownMenuItem(
                                onClick = { onClickUnits(it) }
                            ) {
                                BigText(text = stringResource(it.friendlyNameID))
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun PreviewDropdown() {
    var selectedUnit by rememberSaveable { mutableStateOf(DateTimeUnits.DAY) }
    var expanded by rememberSaveable { mutableStateOf(false) }
    val onBoxClick = { expanded = !expanded }
    val onDismissMenu = { expanded = false }
    val onClickUnits = { it: DateTimeUnits ->
        selectedUnit = it
        expanded = false
    }
    DateTimeCalculatorTheme {
        UnitsSpinner(
            selectedUnit = selectedUnit,
            expanded = expanded,
            textAbove = "Sample text",
            onBoxClick = onBoxClick,
            onDismissMenu = onDismissMenu,
            onClickUnits = onClickUnits
        )
    }
}