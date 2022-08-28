package kunimitsova.valbee.datetimecalculator.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import kunimitsova.valbee.datetimecalculator.R
import kunimitsova.valbee.datetimecalculator.ui.components.reusables.BigText
import kunimitsova.valbee.datetimecalculator.utils.DateTimeUnits

@Composable
fun OutputDiff(num: Long, units: DateTimeUnits) {
    val unitText = stringResource(id = units.friendlyNameID)
    val diffText = stringResource(id = R.string.output_difference, unitText, num)
    Column(horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth(1f)
    ) {
        BigText(text = diffText)
    }
}

@Preview
@Composable
fun outputPreview() {
    OutputDiff(num = 4L, units = DateTimeUnits.MONTH)
}