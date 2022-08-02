package kunimitsova.valbee.datetimecalculator.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kunimitsova.valbee.datetimecalculator.DtcDivider
import kunimitsova.valbee.datetimecalculator.EntryText
import kunimitsova.valbee.datetimecalculator.HeaderText
import kunimitsova.valbee.datetimecalculator.ui.theme.DateTimeCalculatorTheme

@Composable
fun DateTimeMainScreen(modifier: Modifier = Modifier) {
    DateTimeCalculatorTheme {
        // add nav controller and currentbackstack (see Navigation codelab for
        // exmpale) and current destination
        Scaffold(modifier) {
            // set the top nav bar in the scaffold parentheses ()
            // eventually this will be where the NavHost goes (but it's not ready yet)
            Column(modifier = Modifier.padding(vertical = 16.dp)) {
                Row(modifier = Modifier
                    .fillMaxWidth(1f)
                    .padding(horizontal = 16.dp)
                ) {
                    HeaderText(text = "Date", width = 0.5f, clickEvent = {})
                    Spacer(modifier = Modifier.width(4.dp))
                    HeaderText(text = "Time", width = 1f, clickEvent = {})
                }
                Spacer(Modifier.height(4.dp))
                DtcDivider()
                EntryText(text = "MM-DD-YYYY", label = "Date", onChanged = {})
            }
        }
    }
}