package kunimitsova.valbee.datetimecalculator.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kunimitsova.valbee.datetimecalculator.ui.components.*
import kunimitsova.valbee.datetimecalculator.ui.theme.DateTimeCalculatorTheme
import kunimitsova.valbee.datetimecalculator.viewmodels.DateTimeAddSubViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kunimitsova.valbee.datetimecalculator.utils.*
import java.util.*

/**
 * is the expandedAst a state? should I hoist it? seems dumb...
 * it's OK that the screen has some states, right?
 */

@Composable
fun DateTimeScreen(modifier: Modifier = Modifier, vM: DateTimeAddSubViewModel = viewModel()
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

    Column(modifier = modifier
        .fillMaxHeight(1f)
        .background(color = MaterialTheme.colors.secondary)
        .pointerInput(Unit) {
            detectTapGestures(onTap = {
                localFocusManager.clearFocus()
            })
        }
    ) {
        Column (modifier = Modifier.padding(8.dp)) {
            DateItemsInput(
                startYear = vM.startYear.value,
                startMonth = vM.startMonth.value,
                startDay = vM.startDay.value,
                onYrChange = { vM.updateStartYear(it) },
                onMonthChange = { vM.updateStartMonth(it) },
                onDayChange = { vM.updateStartDay(it) },
                modifier = Modifier
            )
            TimeItemsInput(
                startHour = vM.startHour.value,
                startMin = vM.startMin.value,
                startSec = vM.startSec.value,
                startMilli = vM.startMilli.value,
                onHrChange = { vM.updateStartHour(it) },
                onMinChange = { vM.updateStartMin(it) },
                onSecChange = { vM.updateStartSec(it) },
                onMilliChange = { vM.updateStartMilli(it) },
                showMillis = false,
                modifier = Modifier
            )
        }
        DtcDivider()
        PlusMinusButton(addDate = vM.plusMinus.value,
            onToggle = onPlusMinus )
        DtcDivider()
        Spacer(modifier = Modifier.height(8.dp))
        AddOrSubtractThis(
            numToAdd = vM.numToAdd.value,
            onNumChange = { vM.updateNumToAdd(it) },
            selectedUnit = vM.selectedUnit.value,
            expanded = expandedAst,
            onBoxClick = onAstBoxClick,
            onDismissMenu = onDismissMenuAst,
            onClickUnits = onClickAst,
            modifier = Modifier
        )
        Spacer(modifier = Modifier.height(16.dp))
        CalculateButton(onCalculate = onCalculate,
            modifier = Modifier
                .fillMaxWidth(1f))
        Spacer(modifier = Modifier.height(16.dp))
        OutputDateTimeVert(dateTime = vM.endDateTime.value)
    }
}

@Preview
@Composable
fun APreview() {
    DateTimeCalculatorTheme {
        DateTimeScreen()
    }
}