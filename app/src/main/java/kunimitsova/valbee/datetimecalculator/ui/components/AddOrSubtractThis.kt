package kunimitsova.valbee.datetimecalculator.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kunimitsova.valbee.datetimecalculator.R
import kunimitsova.valbee.datetimecalculator.ui.theme.DateTimeCalculatorTheme

@Composable
fun AddOrSubtractThis(numToAdd: String, onNumChange: (String) -> Unit ) {
    Row(modifier = Modifier.padding(start = 24.dp)) {
        EntryText(text = numToAdd ,
            label = stringResource(id = R.string.numToCalculate),
            onChanged = onNumChange)
        Spacer(modifier = Modifier.width(16.dp))

    }
}

@Preview
@Composable
fun AstPreview() {
    DateTimeCalculatorTheme {
        AddOrSubtractThis(numToAdd = "123", onNumChange = {})
    }
}