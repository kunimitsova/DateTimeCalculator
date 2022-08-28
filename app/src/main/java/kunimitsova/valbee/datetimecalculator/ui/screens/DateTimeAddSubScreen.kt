package kunimitsova.valbee.datetimecalculator.ui.screens

import android.graphics.Rect
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kunimitsova.valbee.datetimecalculator.ui.components.*
import kunimitsova.valbee.datetimecalculator.ui.theme.DateTimeCalculatorTheme
import kunimitsova.valbee.datetimecalculator.viewmodels.DateTimeAddSubViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kunimitsova.valbee.datetimecalculator.ui.LayoutSetup
import kunimitsova.valbee.datetimecalculator.utils.*
import kunimitsova.valbee.datetimecalculator.utils.screenclassification.*

/**
 * is the expandedAst a state? should I hoist it? seems dumb...
 * it's OK that the screen has some states, right?
 */

@Composable
fun DateTimeScreen(
    modifier: Modifier = Modifier,
    vM: DateTimeAddSubViewModel = viewModel(),
    screenClassifier: ScreenClassifier2
) {
    // modifier is for the Column
    val localFocusManager = LocalFocusManager.current

    var expandedAst by remember { mutableStateOf(false) }
    val onAstBoxClick = { expandedAst = !expandedAst }
    val onDismissMenuAst = { expandedAst = false }
    val onClickAst = { it: DateTimeUnits ->
        vM.updateSelectedUnit(it)
        expandedAst = false
    }
    val onPlusMinus = { addDate: Boolean -> vM.updatePlusMinus(addDate) }
    val onCalculate = {
        localFocusManager.clearFocus()
        vM.updateDateTimeFields(vM.formatAsDateTime())
        vM.updateEndDateTime(vM.calculatedDate())
    }

    // when it's a big device, position this screen for half of the
    val layoutStyle = layoutStyle(
        when (screenClassifier.mode) {
            PresentationSizeClass.Big -> PresentationSizeClass.Tall
            PresentationSizeClass.BookBig -> PresentationSizeClass.Tall
            PresentationSizeClass.TableBig -> PresentationSizeClass.Wide
            else -> screenClassifier.mode
        } )

    val rect1 = addScreenRect1(screenClassifier.mode, screenClassifier.rect1, screenClassifier.rect2)
    val rect2 = addScreenRect2(screenClassifier.mode, screenClassifier.rect1, screenClassifier.rect2)

    Column(modifier = modifier
        .fillMaxHeight(1f)
        .background(color = MaterialTheme.colors.secondary)
        .pointerInput(Unit) {
            detectTapGestures(onTap = {
                localFocusManager.clearFocus()
            })
        }
    ) {
        AdaptiveScreenLayout( compOne = {
            AddDateTopHalf(
                startYear = vM.startYear.value,
                startMonth = vM.startMonth.value,
                startDay = vM.startDay.value,
                startHour = vM.startHour.value,
                startMin = vM.startMin.value,
                startSec = vM.startSec.value,
                startMilli = vM.startMilli.value,
                onYrChange = { vM.updateStartYear(it) },
                onMonthChange = { vM.updateStartMonth(it) },
                onDayChange = { vM.updateStartDay(it) },
                onHourChange = { vM.updateStartHour(it) },
                onMinChange = { vM.updateStartMin(it) },
                onSecChange = { vM.updateStartSec(it) },
                onMilliChange = { vM.updateStartMilli(it) },
                plusMinus = vM.plusMinus.value,
                onToggle = onPlusMinus
            )
            },
            compTwo = {
                AddDateBottomHalf(
                    numToAdd = vM.numToAdd.value,
                    onNumChange = { vM.updateNumToAdd(it) },
                    selectedUnit = vM.selectedUnit.value,
                    expanded = expandedAst,
                    onBoxClick = onAstBoxClick,
                    onDismissMenu = onDismissMenuAst,
                    onClickUnits = onClickAst,
                    onCalculate = onCalculate,
                    endDateTime = vM.endDateTime.value)
            },
            layoutStyle,
            rect1 = rect1,
            rect2 = rect2
        )
    }
}


fun addScreenRect1(mode: PresentationSizeClass, rect1: Rect, rect2: Rect?): Rect {
    return when (mode) {
        PresentationSizeClass.Small -> rect1
        PresentationSizeClass.Tall -> rect1
        PresentationSizeClass.Wide -> rect1
        PresentationSizeClass.Big -> rect1
        PresentationSizeClass.BookBig -> rect1
        PresentationSizeClass.BookSmall -> rect1
        PresentationSizeClass.TableBig -> rect1
        PresentationSizeClass.TableSmall -> rect1
    }
}

fun addScreenRect2(mode: PresentationSizeClass, rect1: Rect, rect2: Rect?): Rect? {
    return when (mode) {
        PresentationSizeClass.Small -> rect1
        PresentationSizeClass.Tall -> rect1
        PresentationSizeClass.Wide -> rect1
        PresentationSizeClass.Big -> rect1
        PresentationSizeClass.BookBig -> rect1
        PresentationSizeClass.BookSmall -> rect2
        PresentationSizeClass.TableBig -> rect1
        PresentationSizeClass.TableSmall -> rect2
    }
}

@Preview
@Composable
fun APreview() {
    DateTimeCalculatorTheme {
        DateTimeScreen(screenClassifier = sampleClassifier2)
    }
}

val sampleClassifier2 = ScreenClassifier2(
    height = Dimension(800.dp, WindowSizeClass.Medium),
    width = Dimension(440.dp, WindowSizeClass.Compact),
    mode = PresentationSizeClass.TableSmall,
    halfOpen = false,
    isBookMode = false,
    isTableMode = true,
    hingePosition = Rect(0,400,420,420),
    rect1 = Rect(0,0,420,400),
    rect2 = Rect(0,420,420,800),
    isSeparating = true,
    occlusionType = null
)