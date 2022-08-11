package kunimitsova.valbee.datetimecalculator.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.modifier.modifierLocalConsumer
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
fun DateTimeScreen(viewModel: DateTimeAddSubViewModel = viewModel()) {
    Scaffold(
        topBar = { TopAppBar(title = {
            Row(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .selectableGroup()
            ) {
                Surface(elevation = 1.dp, color = MaterialTheme.colors.primary,
                ) {
                    Text(
                        "Add/Subtract from Date",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .weight(1f)
                    )
                }
                Surface(elevation = 1.dp, color = MaterialTheme.colors.primary) {
                    Text(
                        "Difference between Dates",
                        style = MaterialTheme.typography.h6,
                        color = MaterialTheme.colors.onPrimary,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .weight(1f)
                            .alpha(0.5f)
                    )
                }
            }
        }) }
    ) {
        Column {
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
                onHrChange =  viewModel.onHoChange,
                onMinChange = viewModel.onMiChange,
                onSecChange = viewModel.onSchange,
                onMilliChange = viewModel.onMillChange,
                showMillis = false
            )
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
}

@Preview
@Composable
fun APreview() {
    DateTimeCalculatorTheme {
        DateTimeScreen()
    }
}