package kunimitsova.valbee.datetimecalculator.ui.components.segments

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.selected
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.semantics.stateDescription
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kunimitsova.valbee.datetimecalculator.R
import kunimitsova.valbee.datetimecalculator.ui.components.reusables.BodyText
import kunimitsova.valbee.datetimecalculator.ui.components.reusables.ButtonText
import kunimitsova.valbee.datetimecalculator.ui.components.reusables.ReusableTextButton
import kunimitsova.valbee.datetimecalculator.ui.theme.DateTimeCalculatorTheme

@Composable
fun PlusMinusButton(addDate: Boolean, onToggle: (Boolean) -> Unit) {
    val stateAdd = stringResource(id = R.string.add_button)
    val stateSubtract = stringResource(id = R.string.subtract_button)
    Row(modifier = Modifier.padding(start = 30.dp, end = 16.dp)
        .semantics(mergeDescendants = true) {
            stateDescription = if (addDate) stateAdd else stateSubtract
        }
        .toggleable(
            value = addDate,
            onValueChange = onToggle,
            role = Role.Switch
        )
    ) {
        IconToggleButton(
            checked = addDate,
            onCheckedChange = onToggle,
            modifier = Modifier
                .height(75.dp)
                .width(75.dp)
                .padding(4.dp)
                .clearAndSetSemantics {  }
//                .background(
//                    color = MaterialTheme.colors.primary,
//                    shape = MaterialTheme.shapes.medium
//                )
        )
         {
             Surface(color = MaterialTheme.colors.primary,
                 elevation = 4.dp,
                 shape = MaterialTheme.shapes.medium
             ) {
            if (!addDate) {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_horizontal_rule_24),
                    contentDescription = "Subtract",
                    tint = MaterialTheme.colors.onPrimary,
                    modifier = Modifier
                        .height(50.dp)
                        .width(50.dp)
                )
            } else {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_add_24),
                    contentDescription = "Add",
                    tint = MaterialTheme.colors.onPrimary,
                    modifier = Modifier
                        .height(50.dp)
                        .width(50.dp)
                )
            }
        }}
        Spacer(modifier = Modifier.width(30.dp))
        BodyText(
            text = stringResource(id = R.string.toggle_plus_minus),
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun CalculateButton(modifier: Modifier = Modifier,onCalculate: () -> Unit) {
// the modifier param is for the Button not the row.
    Row(modifier = Modifier
        .fillMaxWidth(1f)
        .padding(horizontal = 12.dp)
    ) {
        ReusableTextButton(modifier = modifier, onClick = onCalculate) {
            ButtonText(text = stringResource(id = R.string.calculate))
        }
    }
}

@Composable
fun TestCrashButton(modifier: Modifier = Modifier) {
// the modifier param is for the Button not the row.
    val onClick = { throw RuntimeException("Test Crash")}
    Row(modifier = Modifier
        .fillMaxWidth(1f)
        .padding(horizontal = 12.dp)
    ) {
        ReusableTextButton(modifier = modifier, onClick = onClick) {
            ButtonText(text = "test crash")
        }
    }
}

@Preview(heightDp = 100)
@Composable
fun ButtonsPreview() {
    DateTimeCalculatorTheme {
       PlusMinusButton (
           addDate = true,
           onToggle = { addDate: Boolean -> !addDate }
               )

    }
}