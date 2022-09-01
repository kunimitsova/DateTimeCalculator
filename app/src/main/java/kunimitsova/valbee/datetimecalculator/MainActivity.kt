package kunimitsova.valbee.datetimecalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.window.layout.FoldingFeature
import androidx.window.layout.WindowInfoTracker
import androidx.window.layout.WindowLayoutInfo
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kunimitsova.valbee.datetimecalculator.ui.screens.DateTimeMainScreen
import kunimitsova.valbee.datetimecalculator.ui.theme.DateTimeCalculatorTheme
import kunimitsova.valbee.datetimecalculator.utils.screenclassification.rememberWindowDpSize

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val devicePosture = WindowInfoTracker
            .getOrCreate(this)
            .windowLayoutInfo(this)
            .stateIn(
                scope = lifecycleScope,
                started = SharingStarted.Eagerly,
                initialValue = WindowLayoutInfo(emptyList())
            )

        setContent {
            DateTimeCalculatorTheme {
                val windowDpSize = rememberWindowDpSize(activity = this)
                DateTimeMainScreen(
                    devicePosture = devicePosture,
                    windowDpSize = windowDpSize )
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