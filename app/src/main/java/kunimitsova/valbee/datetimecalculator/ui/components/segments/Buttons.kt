package kunimitsova.valbee.datetimecalculator.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kunimitsova.valbee.datetimecalculator.R
import kunimitsova.valbee.datetimecalculator.ui.components.reusables.BodyText
import kunimitsova.valbee.datetimecalculator.ui.components.reusables.ButtonText
import kunimitsova.valbee.datetimecalculator.ui.components.reusables.ReusableTextButton
import kunimitsova.valbee.datetimecalculator.ui.theme.DateTimeCalculatorTheme

@Composable
fun PlusMinusButton(addDate: Boolean, onToggle: (Boolean) -> Unit) {
    Row(modifier = Modifier.padding(start = 30.dp, end = 16.dp)) {
        IconToggleButton(
            checked = addDate,
            onCheckedChange = onToggle,
            modifier = Modifier
                .height(75.dp)
                .width(75.dp)
                .padding(4.dp)
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

@Preview(heightDp = 100)
@Composable
fun buttonsPreview() {
    DateTimeCalculatorTheme {
       CalculateButton (modifier = Modifier, onCalculate = {})

    }
}