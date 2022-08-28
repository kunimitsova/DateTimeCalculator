package kunimitsova.valbee.datetimecalculator.ui.components

import android.graphics.Rect
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kunimitsova.valbee.datetimecalculator.ui.LayoutSetup
import kunimitsova.valbee.datetimecalculator.utils.screenclassification.middleSpacer
import kotlin.math.abs

@Composable
fun AdaptiveScreenLayout(
    compOne: @Composable () -> Unit,
    compTwo: @Composable () -> Unit,
    layoutSetup: LayoutSetup,
    rect1: Rect,
    rect2: Rect?
) {
    // Layout receiving any order of the composables.
    // For anything in the "compact" area, or weird hinging,
    // only one screen shows at a time, and the
    // navigation will show (top or bottom depending)
    // For anything bigger, both screens will show at the same time.
    // Keep all scrolling in the Layouts, not in the Components or Screens.
    // As in, all scrolling behavior should be in this page.
    // It is the caller's responsibility to know which pieces go with which
    // parameters.

    val middleSpacer = middleSpacer(rect1, rect2)
    val modifierBoxOne: Modifier = if (rect2 != null) Modifier
        .height(rect1.height().dp)
        .width(rect1.width().dp) else Modifier
    val modifierBoxTwo: Modifier = if (rect2 != null) Modifier
        .height(rect2.height().dp)
        .width(rect2.width().dp)  else Modifier

    when (layoutSetup) {
        LayoutSetup.ONE_COLUMN -> {
            // scroll the entire height of the column as one
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Box(modifier = modifierBoxOne) {
                    compOne()
                }
                Spacer(modifier = Modifier.height(middleSpacer.dp))
                Box(modifier = modifierBoxTwo) {
                    compTwo()
                }
            }
        }
        LayoutSetup.ONE_ROW -> {
            Row(modifier = Modifier) {
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .weight(1f)
                ) {
                    Box(modifier = modifierBoxOne) {
                        compOne()
                    }
                }
                Column(
                    modifier = Modifier
                        .verticalScroll(rememberScrollState())
                        .weight(1f)
                ) {
                    Spacer(modifier = Modifier.height(middleSpacer.dp))
                    Box(modifier = modifierBoxTwo) {
                        compTwo()
                    }
                }
            }
        }
        else -> {
            // scroll the entire height of the column as one
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                Box(modifier = modifierBoxOne) {
                    compOne()
                }
                Spacer(modifier = Modifier.height(middleSpacer.dp))
                Box(modifier = modifierBoxTwo) {
                    compTwo()
                }
            }
        }
    }
}