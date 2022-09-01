package kunimitsova.valbee.datetimecalculator.ui.screens

import android.graphics.Rect
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import kunimitsova.valbee.datetimecalculator.R
import kunimitsova.valbee.datetimecalculator.ui.LayoutSetup
import kunimitsova.valbee.datetimecalculator.ui.components.*
import kunimitsova.valbee.datetimecalculator.ui.theme.DateTimeCalculatorTheme
import kunimitsova.valbee.datetimecalculator.utils.DateTimeUnits
import kunimitsova.valbee.datetimecalculator.utils.calculateDifference
import kunimitsova.valbee.datetimecalculator.utils.screenclassification.PresentationSizeClass
import kunimitsova.valbee.datetimecalculator.utils.screenclassification.ScreenClassifier2
import kunimitsova.valbee.datetimecalculator.utils.screenclassification.layoutStyle
import kunimitsova.valbee.datetimecalculator.viewmodels.DateDifferenceScreenViewModel

@Composable
fun DateDifferenceScreen(
    modifier: Modifier = Modifier,
    dateDiffViewModel: DateDifferenceScreenViewModel = viewModel(),
    screenClassifier: ScreenClassifier2
) {
    val localFocusManager = LocalFocusManager.current

    var expandedAst by remember { mutableStateOf(false) }
    val onExpandClick = { expandedAst = !expandedAst }
    val onDismissMenu = { expandedAst = false }
    val onClickSpinner = { it: DateTimeUnits ->
        dateDiffViewModel.updateSelectedUnit(it)
        expandedAst = false
    }
    val onCalculate = {
        localFocusManager.clearFocus()
        dateDiffViewModel.updateTwoDates()
        dateDiffViewModel.updateAnswer(dateDiffViewModel.getNewAnswer())
    }

    val layoutStyle = layoutStyle(
        when (screenClassifier.mode) {
            PresentationSizeClass.Big -> PresentationSizeClass.Tall
            PresentationSizeClass.BookBig -> PresentationSizeClass.Tall
            PresentationSizeClass.TableBig -> PresentationSizeClass.Wide
            else -> screenClassifier.mode
        } )

    Column(modifier = modifier
        .fillMaxHeight(1f)
        .background(color = MaterialTheme.colors.secondary)
        .pointerInput(Unit) {
            detectTapGestures(onTap = {
                localFocusManager.clearFocus()
            })
        }
        .padding(16.dp)
    ) {
       AdaptiveScreenLayout(
           compOne = {
                     DateDiffTopHalf(
                         year1 = dateDiffViewModel.year1.value,
                         month1 = dateDiffViewModel.month1.value,
                         day1 = dateDiffViewModel.day1.value,
                         year2 = dateDiffViewModel.year2.value,
                         month2 = dateDiffViewModel.month2.value,
                         day2 = dateDiffViewModel.day2.value,
                         onYr1Change = { dateDiffViewModel.updateYear1(it) },
                         onYr2Change = { dateDiffViewModel.updateYear2(it) },
                         onMonth1Change = { dateDiffViewModel.updateMonth1(it) },
                         onMonth2Change = { dateDiffViewModel.updateMonth2(it) },
                         onDay1Change = { dateDiffViewModel.updateDay1(it) },
                         onDay2Change = { dateDiffViewModel.updateDay2(it) }
                     )
                     },
           compTwo = {
                        DateDiffBottomHalf(
                            selectedUnit = dateDiffViewModel.selectedUnit.value,
                            expanded = expandedAst,
                            onExpandClick = onExpandClick,
                            onDismissMenu = onDismissMenu,
                            onClickSpinner = onClickSpinner,
                            onCalculate = onCalculate,
                            answer = dateDiffViewModel.answer.value
                        )
                        },
           layoutStyle,
           rect1 = screenClassifier.rect1,
           rect2 = screenClassifier.rect2,
           modifierVerticalScroll = Modifier.verticalScroll(rememberScrollState())
           )
    }
}

@Preview
@Composable
fun diPreview() {
    DateTimeCalculatorTheme {
        DateDifferenceScreen(screenClassifier = sampleClassifier2)
    }
}