package kunimitsova.valbee.datetimecalculator.ui.screens

import android.content.Context
import android.os.Build
import android.text.Html
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.HtmlCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import kunimitsova.valbee.datetimecalculator.R
import kunimitsova.valbee.datetimecalculator.ui.components.reusables.BodyText
import kunimitsova.valbee.datetimecalculator.ui.components.reusables.HeaderText
import kunimitsova.valbee.datetimecalculator.viewmodels.HelpPageViewModel
import java.io.InputStream

// ok it doesn't look pretty but I'll get back to it later.

@Composable
fun HelpScreen (modifier: Modifier = Modifier, helpPageViewModel: HelpPageViewModel = viewModel()) {

    helpPageViewModel.loadHelpData(LocalContext.current)
    val uiString: String = if (helpPageViewModel.uiState.value.howToUseString == null) {
        ""
    } else {
        helpPageViewModel.uiState.value.howToUseString.toString()
    }
    val spannedString = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
        Html.fromHtml(uiString, Html.FROM_HTML_SEPARATOR_LINE_BREAK_LIST_ITEM)
    } else {
        HtmlCompat.fromHtml(uiString, HtmlCompat.FROM_HTML_SEPARATOR_LINE_BREAK_LIST_ITEM)
    }
    SelectionContainer {
        Column(
            modifier
                .padding(16.dp)
                .fillMaxSize(1f)
                .scrollable(rememberScrollState(), orientation = Orientation.Vertical)
        ) {
            HeaderText(text = stringResource(id = R.string.help))
            Spacer(modifier = Modifier.height(16.dp))
            Divider(Modifier.fillMaxWidth(1f))
            BodyText(text = spannedString.toString(), modifier = Modifier)
        }
    }
}

// I don't know if I'm going to use this.
//@Composable
//fun ReadDataFile() {
//    var dataText by remember {
//        mutableStateOf("E")
//    }
//    println("Read Data File")
//    Column {
//        Text("Read Data File")
//        Text(dataText)
//    }
//    val context = LocalContext.current
//    LaunchedEffect(true) {
//        kotlin.runCatching {
//            val inputStream: InputStream = context.assets.open("HowToUse.txt")
//            val size: Int = inputStream.available()
//            val buffer = ByteArray(size)
//            inputStream.read(buffer)
//            String(buffer)
//        }.onSuccess {
//            dataText = it
//        }.onFailure {
//            dataText = "error"
//        }
//
//    }
//}

@Preview
@Composable
fun HelpScreenPreview() {
    HelpScreen()
}