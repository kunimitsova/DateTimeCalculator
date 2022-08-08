package kunimitsova.valbee.datetimecalculator.ui.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kunimitsova.valbee.datetimecalculator.ui.theme.DateTimeCalculatorTheme
import kunimitsova.valbee.datetimecalculator.utils.DateTimeUnits
import kotlin.math.exp

@Composable
fun UnitsSpinner(){
    var expanded by rememberSaveable { mutableStateOf(false) }
    var selectedUnit by rememberSaveable{
        mutableStateOf(DateTimeUnits.DAY)
    }

    Box(modifier = Modifier.padding(16.dp), contentAlignment = Alignment.CenterStart) {
        Surface(elevation = 1.dp,
            shape = MaterialTheme.shapes.medium,
            color = MaterialTheme.colors.surface,
            contentColor = MaterialTheme.colors.onSurface,
            modifier = Modifier
                .width(193.dp)
                .clickable { expanded = !expanded }
        ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(8.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow)
                )
        ) {
            BigText(text = stringResource(selectedUnit.friendlyNameID))
            DropdownMenu(expanded = expanded, onDismissRequest = { expanded = false }) {
                enumValues<DateTimeUnits>().forEach { dtUnit ->
                    DropdownMenuItem(onClick = {
                        expanded = false
                        selectedUnit = dtUnit
                    }) {
                        BigText(text = stringResource(dtUnit.friendlyNameID))
                    }
                }
            }
          }
        }
    }
}

@Composable
fun ExpandedBoxSelector() {
    var expanded by rememberSaveable { mutableStateOf(false) }
    var selectedUnit by rememberSaveable{
        mutableStateOf(DateTimeUnits.DAY)
    }

}

//@Preview(showBackground = true)
@Composable
fun PreviewDropdown() {
    DateTimeCalculatorTheme {
        UnitsSpinner()

    }
}