package kunimitsova.valbee.datetimecalculator.ui.components

import androidx.annotation.StyleableRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kunimitsova.valbee.datetimecalculator.ui.theme.DateTimeCalculatorTheme

// EntryText height on main page = 79dp
@Composable
fun EntryText(
    text: String,
    label: String,
    onChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        value = text,
        onValueChange = onChanged,
        textStyle = MaterialTheme.typography.h4,
        label = { Text(label) },
        modifier = modifier
    )
}

@Composable
fun ButtonText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.h4,
        modifier = Modifier
    )
}

@Composable
fun DateDivider(modifier: Modifier = Modifier){
    Text(text = "/",
        style = MaterialTheme.typography.h3,
        modifier = modifier.wrapContentWidth(Alignment.CenterHorizontally)
    )
}

@Composable
fun TimeDivider(modifier: Modifier = Modifier){
    Text(text = ":",
        style = MaterialTheme.typography.h3,
        modifier = modifier.wrapContentWidth(Alignment.CenterHorizontally)
    )
}

@Composable
fun DotDivider(modifier: Modifier = Modifier){
    Text(text = ".",
        style = MaterialTheme.typography.h3,
        modifier = modifier.wrapContentWidth(Alignment.CenterHorizontally)
    )
}

@Composable
fun DtcDivider(modifier: Modifier = Modifier) {
    Divider(color = MaterialTheme.colors.primary, thickness = 1.dp, modifier = modifier)
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HeaderText(
    text: String = "",
    width: Float = 0.5f,
    clickEvent: () -> Unit
) {
    Surface(elevation = 3.dp, onClick = clickEvent) {
        Text(
            text = text,
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth(width)
                .padding(vertical = 8.dp, horizontal = 4.dp)
        )
    }
}

@Preview
@Composable
fun showEntryTextSample() {
    DateTimeCalculatorTheme {
        EntryText(text = "Bloody Hell", label = "where", onChanged = {})
    }
}