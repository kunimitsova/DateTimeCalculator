package kunimitsova.valbee.datetimecalculator.ui.menus

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kunimitsova.valbee.datetimecalculator.R
import kunimitsova.valbee.datetimecalculator.ui.components.reusables.HeaderText
import kunimitsova.valbee.datetimecalculator.ui.theme.DateTimeCalculatorTheme

@Composable
fun ActionsNavRow(onNavToAdd: () -> Unit, onNavToDiff: () -> Unit, onNavToHelp: () -> Unit,
                  modifier: Modifier = Modifier
) {
    // fits into the TopAppBar after the Navigation Icon
    // where Actions{} go, so if it's used somehwere else
    // make sure to put at least 48dp of something in front
    // so it's balanced.
    Row(modifier) {
        IconButton(
            onClick = onNavToAdd, modifier = Modifier
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colors.primaryVariant,
                    shape = MaterialTheme.shapes.large
                )
                .background(
                    color = MaterialTheme.colors.primary,
                    shape = MaterialTheme.shapes.large
                )
                .weight(1f)
        ) {
            HeaderText(text = stringResource(id = R.string.add_date))
        }
        Spacer(modifier = Modifier.width(4.dp))
        IconButton(
            onClick = onNavToDiff, modifier = Modifier
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colors.primaryVariant,
                    shape = MaterialTheme.shapes.large
                )
                .background(
                    color = MaterialTheme.colors.primary,
                    shape = MaterialTheme.shapes.large
                )
                .weight(1f)
        ) {
            HeaderText(text = stringResource(id = R.string.date_difference))
        }
        Spacer(modifier = Modifier.width(4.dp))
        IconButton(onClick = onNavToHelp, modifier = Modifier
            .width(48.dp)
            .border(
                width = 1.dp,
                color = MaterialTheme.colors.primaryVariant,
                shape = MaterialTheme.shapes.medium
            )
            .background(
                color = MaterialTheme.colors.primary,
                shape = MaterialTheme.shapes.medium
            )
        ) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_help_outline_24),
                contentDescription = stringResource(id = R.string.overflow_desc)
            )
        }
    }
}

//@Preview
//@Composable
//fun RowPreview() {
//    DateTimeCalculatorTheme {
//        ActionsNavRow(modifier = Modifier,onNavToAdd = { }, onNavToDiff = { },
//            onNavToHelp = {})
//    }
//}