package kunimitsova.valbee.datetimecalculator.ui.components

import android.graphics.Paint.Align
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
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import kunimitsova.valbee.datetimecalculator.ui.theme.DateTimeCalculatorTheme
import kunimitsova.valbee.datetimecalculator.utils.DateTimeUnits
import kotlin.math.exp

@Composable
fun UnitsSpinner(
    selectedUnit: DateTimeUnits,
    expanded: Boolean,
    onBoxClick: () -> Unit,
    onDismissMenu: () -> Unit,
    onClickUnits: (DateTimeUnits) -> Unit
){


    Box(modifier = Modifier.padding(8.dp)) {
        Surface(elevation = 1.dp,
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
                .padding(8.dp)
                .clickable(onClick =  onBoxClick)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow))
        ) {
            BigText(text = stringResource(selectedUnit.friendlyNameID))
            DropdownMenu(expanded = expanded,
                onDismissRequest =  onDismissMenu  ) {
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

@Preview(showBackground = true, widthDp = 300)
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
            onBoxClick = onBoxClick,
            onDismissMenu = onDismissMenu,
            onClickUnits = onClickUnits
        )
    }
}