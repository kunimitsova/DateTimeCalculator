package kunimitsova.valbee.datetimecalculator

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

// is this like the stateless thing? I'm trying to make one...
@Composable
fun EntryText(
    text: String,
    label: String,
    onChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = text,
        onValueChange = onChanged,
        label = { Text(label) },
        modifier = modifier
    )
}

@Composable
fun BigText(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.h5
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