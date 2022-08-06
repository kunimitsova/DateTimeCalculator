package kunimitsova.valbee.datetimecalculator.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconToggleButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kunimitsova.valbee.datetimecalculator.R

@Composable
fun PlusMinusButton(addDate: Boolean, onToggle: (Boolean) -> Unit) {
    Row(modifier = Modifier.padding(start = 30.dp)) {
        IconToggleButton(
            checked = addDate,
            onCheckedChange = onToggle,
            modifier = Modifier.height(79.dp)
        ) {
            if (!addDate) {
                Icon(painter = painterResource(id = R.drawable.baseline_horizontal_rule_24),
                    contentDescription = "Subtract" )
            } else {
                Icon(painter = painterResource(id = R.drawable.baseline_add_24),
                    contentDescription = "Add")
            }
        }
        Text(text = "click to toggle addition/subtraction")
    }
}

@Composable
fun CalculateButton(onCalculate: () -> Unit) {
    Row(modifier = Modifier
        .fillMaxWidth(1f)
        .padding(horizontal = 16.dp)) {
        Button(
            onClick = onCalculate,
            modifier = Modifier.fillMaxWidth(1f)
        ) {
            ButtonText(text = stringResource(id = R.string.calculate))
        }
    }
}

@Preview
@Composable
fun buttonPreview() {

}