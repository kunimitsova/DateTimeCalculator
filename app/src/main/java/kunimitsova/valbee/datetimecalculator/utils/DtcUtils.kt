package kunimitsova.valbee.datetimecalculator.utils

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



