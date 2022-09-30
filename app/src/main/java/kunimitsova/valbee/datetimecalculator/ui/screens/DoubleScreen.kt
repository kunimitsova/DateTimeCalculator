package kunimitsova.valbee.datetimecalculator.ui.screens

import android.graphics.Rect
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import kunimitsova.valbee.datetimecalculator.utils.screenclassification.PresentationSizeClass
import kunimitsova.valbee.datetimecalculator.utils.screenclassification.ScreenClassifier2
import kunimitsova.valbee.datetimecalculator.utils.screenclassification.layoutStyle

@Composable
fun DoubleScreen(screenClassifier: ScreenClassifier2) {

    // make changes if the two halves won't fit:
    // create a rect that shows where the app should fit
    // use that rect as the container for the column/row that has the screen.

    // right now size Big has two columns regardless of screen orientation
    val rect1 = if (screenClassifier.mode == PresentationSizeClass.Big) {
        getBigRect1(screenClassifier.rect1) } else screenClassifier.rect1
    val rect2 = if (screenClassifier.mode == PresentationSizeClass.Big) {
        getBigRect2(screenClassifier.rect1) } else screenClassifier.rect2

//    if (screenClassifier.twoHalves == true) {
    val comp1: @Composable () -> Unit = { DateTimeScreen(screenClassifier = screenClassifier) }
    val comp2: @Composable () -> Unit = { DateDifferenceScreen(screenClassifier = screenClassifier)}
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
        rect2 = rect2,
        modifier = Modifier
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
