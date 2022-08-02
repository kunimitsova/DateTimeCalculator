package kunimitsova.valbee.datetimecalculator

import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kunimitsova.valbee.datetimecalculator.ui.theme.DateTimeCalculatorTheme
import java.util.regex.Pattern.matches

/**
 * utilities, plus some code testing
 */

fun leadingZero(num: Int, maxDigits: Int): String {
    val numString = num.toString()
    var len = numString.length
    var exitString = numString
    while (len < maxDigits) {
        exitString = "0" + exitString
        len++
    }
    return exitString
}

@Composable
fun properOlt() {
    var myText by remember { mutableStateOf("sample")}
    OutlinedTextField(
        value = myText,
        onValueChange = { myText = it},
        label = { Text("Example") },
        modifier = Modifier
            .padding(horizontal = 8.dp)
    )
}

@Composable
fun HelloScreen() {
    var name by rememberSaveable { mutableStateOf("2022") }
    var yr1 = "2022"
    Column {
        HelloContent(name = name, onNameChange = { name = it })
        if (name.matches(Regex("[0-9][0-9][0-9][0-9]"))) {
            yr1 = name
        } else {
            if (name.matches(Regex("[0-9][0-9]"))) {
                yr1 = "20" + name
            } else {
                yr1 = "Error"
            }
        }
        Text("Save the input: $yr1")
    }
}

@Composable
fun HelloContent(name: String, onNameChange: (String) -> Unit) {
    Column(modifier = Modifier.padding(16.dp)) {
//        Text(
//            text = "Hello, $name",
//            modifier = Modifier.padding(bottom = 8.dp),
//            style = MaterialTheme.typography.h5
//        )
        OutlinedTextField(
            value = name,
            onValueChange = onNameChange,
            label = { Text("Name") }
        )
    }
}
@Preview
@Composable
fun HelloPreview() {
    HelloScreen()
}

// code tests from the dev guides
//@Composable
//fun HelloContent() {
//    Column(modifier = Modifier.padding(16.dp)) {
//        var name by remember { mutableStateOf("") }
//        if (name.isNotEmpty()) {
//            Text(
//                text = "Hello, $name!",
//                modifier = Modifier.padding(bottom = 8.dp),
//                style = MaterialTheme.typography.h5
//            )
//        }
//        OutlinedTextField(
//            value = name,
//            onValueChange = { name = it },
//            label = { Text("Name") }
//        )
//    }
//}

//@Composable
//fun StyledTextField() {
//    var value by remember { mutableStateOf("Hello\nWorld\nInvisible") }
//
//    TextField(
//        value = value,
//        onValueChange = { value = it },
//        label = { Text("Enter text") },
//        maxLines = 2,
//        textStyle = TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold),
//        modifier = Modifier.padding(20.dp)
//    )
//}
//
//@Preview(widthDp = 320)
//@Composable
//fun HCPrev() {
//    DateTimeCalculatorTheme {
//        HelloContent()
//    }
//}