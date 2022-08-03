package kunimitsova.valbee.datetimecalculator

import androidx.compose.foundation.layout.padding
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

//import androidx.annotation.Sampled
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText

//@Sampled
fun passwordFilter(text: AnnotatedString): TransformedText {
    return TransformedText(
        AnnotatedString("*".repeat(text.text.length)),

        /**
         * [OffsetMapping.Identity] is a predefined [OffsetMapping] that can be used for the
         * transformation that does not change the character count.
         */
        OffsetMapping.Identity
    )
}

//@Sampled
fun creditCardFilter(text: AnnotatedString): TransformedText {

    // Making XXXX-XXXX-XXXX-XXXX string.
    val trimmed = if (text.text.length >= 16) text.text.substring(0..15) else text.text
    var out = ""
    for (i in trimmed.indices) {
        out += trimmed[i]
        if (i % 4 == 3 && i != 15) out += "-"
    }

    /**
     * The offset translator should ignore the hyphen characters, so conversion from
     *  original offset to transformed text works like
     *  - The 4th char of the original text is 5th char in the transformed text.
     *  - The 13th char of the original text is 15th char in the transformed text.
     *  Similarly, the reverse conversion works like
     *  - The 5th char of the transformed text is 4th char in the original text.
     *  - The 12th char of the transformed text is 10th char in the original text.
     */
    val creditCardOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 3) return offset
            if (offset <= 7) return offset + 1
            if (offset <= 11) return offset + 2
            if (offset <= 16) return offset + 3
            return 19
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 4) return offset
            if (offset <= 9) return offset - 1
            if (offset <= 14) return offset - 2
            if (offset <= 19) return offset - 3
            return 16
        }
    }

    return TransformedText(AnnotatedString(out), creditCardOffsetTranslator)
}

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