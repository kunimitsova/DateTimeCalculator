package kunimitsova.valbee.datetimecalculator.ui.screens

import android.graphics.Rect
import androidx.compose.runtime.Composable
import kunimitsova.valbee.datetimecalculator.ui.LayoutSetup
import kunimitsova.valbee.datetimecalculator.ui.components.AdaptiveScreenLayout
import kunimitsova.valbee.datetimecalculator.utils.screenclassification.PresentationSizeClass
import kunimitsova.valbee.datetimecalculator.utils.screenclassification.ScreenClassifier2
import kunimitsova.valbee.datetimecalculator.utils.screenclassification.layoutStyle
import kunimitsova.valbee.datetimecalculator.utils.screenclassification.tableModeBottomRect

@Composable
fun DoubleScreen(screenClassifier: ScreenClassifier2) {
    var comp1: @Composable () -> Unit = {}
    var comp2: @Composable () -> Unit = {}

    // make changes if the two halves won't fit:
    // create a rect that shows where the app should fit
    // use that rect as the container for the column/row that has the screen.

    // right now size Big has two columns regardless of screen orientation
    val rect1 = if (screenClassifier.mode == PresentationSizeClass.Big) {
        getBigRect1(screenClassifier.rect1) } else screenClassifier.rect1
    val rect2 = if (screenClassifier.mode == PresentationSizeClass.Big) {
        getBigRect2(screenClassifier.rect1) } else screenClassifier.rect2

//    if (screenClassifier.twoHalves == true) {
        comp1 = { DateTimeScreen(screenClassifier = screenClassifier) }
        comp2 = { DateDifferenceScreen(screenClassifier = screenClassifier)}
//    } else {
//        when {
//            // when rect1 is too small and rect2 is a good size, use rect2 for the NavHost
//            // otherwise use rect1 as the NavHost.
//        }
//    }
    AdaptiveScreenLayout(
        compOne = comp1,
        compTwo = comp2,
        layoutStyle(screenClassifier.mode),
        rect1 = rect1,
        rect2 = rect2
    )
}

fun getBigRect2(rect: Rect): Rect {
    val left = (rect.right / 2) + 8
    val top  = rect.top
    val right = rect.right
    val bottom = rect.bottom

    return Rect(left, top, right, bottom)
}

fun getBigRect1(rect: Rect): Rect {
    val left = rect.left
    val top = rect.top
    val right = (rect.right / 2) - 8
    val bottom = rect.bottom

    return  Rect(left, top, right, bottom)
}
