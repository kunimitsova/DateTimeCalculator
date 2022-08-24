package kunimitsova.valbee.datetimecalculator.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kunimitsova.valbee.datetimecalculator.utils.WindowWidthSizeClass

@Composable
fun AdaptiveScreenLayout(
    topHalf: @Composable () -> Unit,
    bottomHalf: @Composable () -> Unit,
    windowSize: WindowWidthSizeClass
) {
   // Layout the two parts of the Add number (units) to Date screen
    // for the given WindowWidthSizeClass
    // Keep all scrolling in the Layout composables, not in the Components or Screens!

    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                topHalf()
                Spacer(modifier = Modifier.height(16.dp))
                bottomHalf()
            }
        }
        else -> {
            Row(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Column(modifier = Modifier.weight(1f)) {
                    topHalf()
                }
                Column(modifier = Modifier.weight(1f)) {
                    Spacer(modifier = Modifier.height(16.dp))
                    bottomHalf()
                }
            }
        }
    }
}