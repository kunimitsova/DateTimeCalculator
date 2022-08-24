package kunimitsova.valbee.datetimecalculator.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
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
import kunimitsova.valbee.datetimecalculator.ui.components.*
import kunimitsova.valbee.datetimecalculator.ui.theme.DateTimeCalculatorTheme
import kunimitsova.valbee.datetimecalculator.utils.DateTimeUnits
import kunimitsova.valbee.datetimecalculator.utils.WindowWidthSizeClass
import kunimitsova.valbee.datetimecalculator.utils.calculateDifference
import kunimitsova.valbee.datetimecalculator.viewmodels.DateDifferenceScreenViewModel

@Composable
fun DateDifferenceScreen(
    modifier: Modifier = Modifier,
    dateDiffViewModel: DateDifferenceScreenViewModel = viewModel(),
    windowSize: WindowWidthSizeClass
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
           topHalf = {
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
           bottomHalf = {
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
           windowSize = windowSize)
//        Row (modifier = Modifier.padding(vertical = 4.dp)) {
//            HeaderText(text = "Date 1")
//        }
//        DateItemsInput(
//            startYear = dateDiffViewModel.year1.value,
//            startMonth = dateDiffViewModel.month1.value,
//            startDay = dateDiffViewModel.day1.value,
//            onYrChange = { dateDiffViewModel.updateYear1(it) },
//            onMonthChange = { dateDiffViewModel.updateMonth1(it) },
//            onDayChange = { dateDiffViewModel.updateDay1(it) }
//        )
//
//        Row (modifier = Modifier.padding(vertical = 4.dp)) {
//            HeaderText(text = "Date 2")
//        }
//        DateItemsInput(
//            startYear = dateDiffViewModel.year2.value,
//            startMonth = dateDiffViewModel.month2.value,
//            startDay = dateDiffViewModel.day2.value,
//            onYrChange = { dateDiffViewModel.updateYear2(it) },
//            onMonthChange = { dateDiffViewModel.updateMonth2(it) },
//            onDayChange = { dateDiffViewModel.updateDay2(it) }
//        )
//        DtcDivider()
//        Row(verticalAlignment = Alignment.CenterVertically,
//            modifier = Modifier
//                .padding(16.dp)
//                .fillMaxWidth(1f)
//        ) {
//            BigText(text = stringResource(id = R.string.how_many),
//                modifier = Modifier.weight(1f)
//            )
//            Column(horizontalAlignment = Alignment.CenterHorizontally) {
//                LittleText(text = stringResource(id = R.string.difference_units))
//                UnitsSpinner(
//                    selectedUnit = dateDiffViewModel.selectedUnit.value,
//                    expanded = expandedAst,
//                    onBoxClick = onExpandClick,
//                    onDismissMenu = onDismissMenu,
//                    onClickUnits = onClickSpinner
//                )
//            }
//        }
//        CalculateButton(modifier = Modifier.fillMaxWidth(1f), onCalculate = onCalculate)
//        Spacer(modifier = Modifier.height(24.dp))
//        OutputDiff(dateDiffViewModel.answer.value, dateDiffViewModel.selectedUnit.value)
    }
}

@Preview(widthDp = 700)
@Composable
fun diPreview() {
    DateTimeCalculatorTheme {
        DateDifferenceScreen(windowSize = WindowWidthSizeClass.Medium)
    }
}