package kunimitsova.valbee.datetimecalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kunimitsova.valbee.datetimecalculator.ui.theme.DateTimeCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DateTimeCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    DateTimeMainScreen()
                }
            }
        }
    }
}

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
//                DtcDivider()
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HeaderText(
    text: String = "",
    width: Float = 0.5f,
    clickEvent: () -> Unit
) {
    Surface(elevation = 3.dp, onClick = clickEvent) {
        Text(
            text = text,
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth(width)
                .padding(vertical = 8.dp, horizontal = 4.dp)
        )
    }
}

@Composable
fun EntryText(
    text: String = "",
    width: Float = 0.5f
) {
        Text(text = "", )
}

@Composable
fun DtcDivider(modifier: Modifier = Modifier) {
    Divider(color = MaterialTheme.colors.primary, thickness = 1.dp, modifier = modifier)
}

@Preview(name = "Main Screen")
@Composable
fun MainScreenPreview() {
    DateTimeCalculatorTheme {
        DateTimeMainScreen()
    }
}