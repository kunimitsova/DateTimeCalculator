package kunimitsova.valbee.datetimecalculator.ui.components.reusables

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kunimitsova.valbee.datetimecalculator.BuildConfig
import kunimitsova.valbee.datetimecalculator.ui.theme.DateTimeCalculatorTheme

class Ref(var value: Int)
// Note the inline function below which ensures that this function is essentially
// copied at the call site to ensure that its logging only recompositions from the
// original call site.
@Composable
inline fun LogCompositions(tag: String, msg: String) {
    if (BuildConfig.DEBUG) {
        val ref = remember { Ref(0) }
        SideEffect { ref.value++ }
        Log.d(tag, "Compositions: $msg ${ref.value}")
    }
}

// EntryText height on main page = 79dp
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun EntryText(
    text: String,
    label: String,
    onChanged: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    TextField(
        value = text,
        singleLine = true,
        colors = dtcTextFieldColors(),
        shape = MaterialTheme.shapes.medium,
        onValueChange = onChanged,
        textStyle = MaterialTheme.typography.h5,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done,
            keyboardType = KeyboardType.Number
        ),
        keyboardActions = KeyboardActions(
            onDone = { keyboardController?.hide() }
        ),
        label = { Text(label) },
        modifier = modifier
    )
}

@Composable
fun BigText(text: String, modifier: Modifier = Modifier, textAlign: TextAlign = TextAlign.Center){
    Text(
        text = text,
        textAlign = textAlign,
        style = MaterialTheme.typography.h5,
        modifier = modifier
    )
}

@Composable
fun LittleText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.caption,
        textAlign = TextAlign.End,
        modifier = modifier
    )
}

@Composable
fun BodyText(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.body1,
        textAlign = TextAlign.Start,
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
        style = MaterialTheme.typography.h5,
        modifier = modifier
    )
}

@Composable
fun DateDivider(modifier: Modifier = Modifier){
    Text(text = "/",
        style = MaterialTheme.typography.h4,
        modifier = modifier.wrapContentWidth(Alignment.CenterHorizontally)
    )
}

@Composable
fun TimeDivider(modifier: Modifier = Modifier){
    Text(text = ":",
        style = MaterialTheme.typography.h4,
        modifier = modifier.wrapContentWidth(Alignment.CenterHorizontally)
    )
}

@Composable
fun DotDivider(modifier: Modifier = Modifier){
    Text(text = ".",
        style = MaterialTheme.typography.h4,
        modifier = modifier.wrapContentWidth(Alignment.CenterHorizontally)
    )
}

@Composable
fun DtcDivider(modifier: Modifier = Modifier) {
    Divider(color = MaterialTheme.colors.primary, thickness = 1.dp, modifier = modifier)
}

@Composable
fun HeaderText(
    text: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.h6,
        modifier = modifier
    )
}

@Composable
fun dtcTextFieldColors(
    textColor: Color = MaterialTheme.colors.onBackground,
    backgroundColor: Color = MaterialTheme.colors.background,
    cursorColor: Color = MaterialTheme.colors.onSurface,
    focusedIndicatorColor: Color = MaterialTheme.colors.primary,
    focusedLabelColor: Color = MaterialTheme.colors.secondaryVariant
) = TextFieldDefaults.textFieldColors(
    textColor = textColor,
    backgroundColor = backgroundColor,
    cursorColor = cursorColor,
    focusedIndicatorColor = focusedIndicatorColor,
    focusedLabelColor = focusedLabelColor
)

@Composable
fun ReusableTextButton(modifier: Modifier = Modifier,
                       onClick: () -> Unit,
                       content: @Composable (RowScope.() -> Unit)
) {
    Surface(
        elevation = 4.dp,
        color = MaterialTheme.colors.primary,
        shape = MaterialTheme.shapes.large,
        modifier = modifier.defaultMinSize(minHeight = 48.dp)
    ){
        Button(onClick = onClick, content = content)
    }
}

@Composable
fun ReusableIconButton(modifier: Modifier = Modifier, onClick: () -> Unit, content: @Composable (RowScope.() -> Unit)
) {
    Surface(
        elevation = 4.dp,
        color = MaterialTheme.colors.primary,
        shape = MaterialTheme.shapes.medium,
        modifier = modifier.defaultMinSize(minHeight = 48.dp)
    ){
        Button(onClick = onClick, content = content)
    }
}

@Preview
@Composable
fun reusablesPreview() {
    DateTimeCalculatorTheme {
        HeaderText(text = "Bloody Hell")
    }
}