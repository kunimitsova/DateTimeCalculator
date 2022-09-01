package kunimitsova.valbee.datetimecalculator.ui.components

import android.graphics.Rect
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kunimitsova.valbee.datetimecalculator.ui.LayoutSetup
import kunimitsova.valbee.datetimecalculator.ui.components.reusables.VerticalDivider
import kunimitsova.valbee.datetimecalculator.utils.screenclassification.middleSpacer
import kotlin.math.abs

@Composable
fun AdaptiveScreenLayout(
    compOne: @Composable () -> Unit,
    compTwo: @Composable () -> Unit,
    layoutSetup: LayoutSetup,
    rect1: Rect,
    rect2: Rect?,
    modifierVerticalScroll: Modifier = Modifier
) {
    /**
     * Layout receiving any order of the composables.
     * For anything in the "compact" area, or weird hinging,
     * only one screen shows at a time, and the
     * navigation will show (top or bottom depending)
     * (I assume, this has been hell to test)
     * For anything bigger, both screens will show at the same time.
     * To keep scrolling from nesting, the vertical scroll modifier
     * is only added for the lower-level comps. (i.e., not doubleScreen)
     * It is the caller's responsibility to know which pieces go with which
     * parameters.
      */


    val middleSpacer = middleSpacer(rect1, rect2)

    // if rect2 is null then there is no reason to use the modifier on the boxes
    val modifierBoxOne: Modifier = if (rect2 != null) Modifier
        .height(rect1.height().dp)
        .width(rect1.width().dp) else Modifier
    val modifierBoxTwo: Modifier = if (rect2 != null) Modifier
        .height(rect2.height().dp)
        .width(rect2.width().dp)  else Modifier

    when (layoutSetup) {
        LayoutSetup.ONE_COLUMN -> {
            // scroll the entire height of the column as one
            Column(modifier = modifierVerticalScroll) {
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
                    modifier = modifierVerticalScroll
                        .weight(1f)
                ) {
                    Box(modifier = modifierBoxOne) {
                        compOne()
                    }
                }
                Spacer(modifier = Modifier.width(middleSpacer.dp))
                VerticalDivider()
                Column(
                    modifier = modifierVerticalScroll
                        .weight(1f)
                ) {
                    Box(modifier = modifierBoxTwo) {
                        compTwo()
                    }
                }
            }
        }
        else -> {
            // scroll the entire height of the column as one
            Column(modifier = modifierVerticalScroll) {
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