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
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kunimitsova.valbee.datetimecalculator.ui.screens.DateTimeMainScreen
import kunimitsova.valbee.datetimecalculator.ui.theme.DateTimeCalculatorTheme
import kunimitsova.valbee.datetimecalculator.utils.DevicePosture
import kunimitsova.valbee.datetimecalculator.utils.calculateWindowSizeClass
import kunimitsova.valbee.datetimecalculator.utils.isBookPosture
import kunimitsova.valbee.datetimecalculator.utils.isSeparating

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val devicePostureFlow =  WindowInfoTracker.getOrCreate(this).windowLayoutInfo(this)
            .flowWithLifecycle(this.lifecycle)
            .map { layoutInfo ->
                val foldingFeature =
                    layoutInfo.displayFeatures
                        .filterIsInstance<FoldingFeature>()
                        .firstOrNull()
                when {
                    isBookPosture(foldingFeature) ->
                        DevicePosture.BookPosture(foldingFeature.bounds)

                    isSeparating(foldingFeature) ->
                        DevicePosture.Separating(foldingFeature.bounds, foldingFeature.orientation)

                    else -> DevicePosture.NormalPosture
                }
            }
            .stateIn(
                scope = lifecycleScope,
                started = SharingStarted.Eagerly,
                initialValue = DevicePosture.NormalPosture
            )
        setContent {
            DateTimeCalculatorTheme {
                val windowSize = calculateWindowSizeClass(activity = this)
                val devicePosture = devicePostureFlow.collectAsState().value
                DateTimeMainScreen(
                    windowSize = windowSize.widthSizeClass,
                    foldingDevicePosture = devicePosture )
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