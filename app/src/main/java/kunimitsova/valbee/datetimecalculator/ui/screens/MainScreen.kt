package kunimitsova.valbee.datetimecalculator.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kunimitsova.valbee.datetimecalculator.ui.components.DtcDivider
import kunimitsova.valbee.datetimecalculator.ui.components.HeaderText
import kunimitsova.valbee.datetimecalculator.ui.theme.DateTimeCalculatorTheme

@Composable
fun DateTimeMainScreen(modifier: Modifier = Modifier) {
    DateTimeCalculatorTheme {
        // add nav controller and currentbackstack (see Navigation codelab for
        // exmpale) and current destination
        DateTimeScreen()
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    DateTimeCalculatorTheme {
        DateTimeMainScreen()
    }
}