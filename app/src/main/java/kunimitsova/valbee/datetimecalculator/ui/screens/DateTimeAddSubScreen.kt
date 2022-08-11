package kunimitsova.valbee.datetimecalculator.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.focusGroup
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import kunimitsova.valbee.datetimecalculator.R
import kunimitsova.valbee.datetimecalculator.ui.components.*
import kunimitsova.valbee.datetimecalculator.ui.theme.DateTimeCalculatorTheme
import kunimitsova.valbee.datetimecalculator.utils.DateTimeUnits
import kunimitsova.valbee.datetimecalculator.utils.leadingZero
import kunimitsova.valbee.datetimecalculator.viewmodels.DateTimeAddSubViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import java.util.*

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun DateTimeScreen(viewModel: DateTimeAddSubViewModel = viewModel(), modifier: Modifier = Modifier) {
        Column(modifier = modifier.fillMaxHeight(1f)
            .background(color = MaterialTheme.colors.secondary)
        ) {
            Column (modifier = Modifier.padding(8.dp)) {
                DateItemsInput(
                    startYear = viewModel.startYear,
                    startMonth = viewModel.startMonth,
                    startDay = viewModel.startDay,
                    onYrChange = viewModel.onYrChange,
                    onMonthChange = viewModel.onMoChange,
                    onDayChange = viewModel.onDayChange,
                )
                TimeItemsInput(
                    startHour = viewModel.startHour,
                    startMin = viewModel.startMin,
                    startSec = viewModel.startSec,
                    startMilli = viewModel.startMilli,
                    onHrChange = viewModel.onHoChange,
                    onMinChange = viewModel.onMiChange,
                    onSecChange = viewModel.onSchange,
                    onMilliChange = viewModel.onMillChange,
                    showMillis = false
                )
            }
            DtcDivider()
            PlusMinusButton(addDate = viewModel.plusMinus,
                onToggle = { addDate -> viewModel.plusMinus = !viewModel.plusMinus })
            DtcDivider()
            Spacer(modifier = Modifier.height(8.dp))
            AddOrSubtractThis(
                numToAdd = viewModel.numToAdd,
                onNumChange = viewModel.onNumChange,
                selectedUnit = viewModel.selectedUnit,
                expanded = viewModel.expanded,
                onBoxClick = viewModel.onBoxClick,
                onDismissMenu = viewModel.onDismissMenu,
                onClickUnits = viewModel.onItemClick
            )
            Spacer(modifier = Modifier.height(16.dp))
            CalculateButton(onCalculate = viewModel.onCalculate)
            Spacer(modifier = Modifier.height(16.dp))
            OutputDateTimeVert(dateTime = viewModel.endDateTime)
        }
}

@Preview
@Composable
fun APreview() {
    DateTimeCalculatorTheme {
        DateTimeScreen()
    }
}