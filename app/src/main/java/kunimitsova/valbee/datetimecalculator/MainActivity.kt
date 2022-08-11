package kunimitsova.valbee.datetimecalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import kunimitsova.valbee.datetimecalculator.ui.screens.DateTimeMainScreen
import kunimitsova.valbee.datetimecalculator.ui.theme.DateTimeCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DateTimeCalculatorTheme {
                    DateTimeMainScreen()
            }
        }
    }
}



//@Preview(name = "Main Screen")
//@Composable
//fun MainScreenPreview() {
//    DateTimeCalculatorTheme {
//        DateTimeMainScreen()
//    }
//}