package kunimitsova.valbee.datetimecalculator.ui.screens

import android.graphics.Rect
import androidx.compose.runtime.Composable
import kunimitsova.valbee.datetimecalculator.ui.LayoutSetup
import kunimitsova.valbee.datetimecalculator.ui.components.AdaptiveScreenLayout
import kunimitsova.valbee.datetimecalculator.utils.screenclassification.PresentationSizeClass
import kunimitsova.valbee.datetimecalculator.utils.screenclassification.ScreenClassifier2
import kunimitsova.valbee.datetimecalculator.utils.screenclassification.layoutStyle

@Composable
fun DoubleScreen(screenClassifier: ScreenClassifier2) {
    AdaptiveScreenLayout(
        compOne = {
                  DateTimeScreen(screenClassifier = screenClassifier)
                  },
        compTwo = { DateDifferenceScreen(screenClassifier = screenClassifier) },
        layoutStyle(PresentationSizeClass.Big),
        rect1 = screenClassifier.rect1,
        rect2 = screenClassifier.rect2
    )
}