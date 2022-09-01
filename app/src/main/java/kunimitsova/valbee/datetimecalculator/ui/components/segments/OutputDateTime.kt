package kunimitsova.valbee.datetimecalculator.ui.components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kunimitsova.valbee.datetimecalculator.ui.components.reusables.BigText
import kunimitsova.valbee.datetimecalculator.ui.theme.DateTimeCalculatorTheme
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@SuppressLint("NewApi")
@Composable
fun OutputDate(date: LocalDateTime) {
    BigText(text = date.format(DateTimeFormatter.ofPattern("EEE, MMM dd, yyyy", java.util.Locale.getDefault())) )
}
@SuppressLint("NewApi")
@Composable
fun OutputTime(time: LocalDateTime) {
    BigText(text = time.format(DateTimeFormatter.ofLocalizedTime(FormatStyle.MEDIUM)))
}
@SuppressLint("NewApi")
@Composable
fun OutputBigTime(time: LocalDateTime) {
    BigText(text = time.format(DateTimeFormatter.ISO_LOCAL_TIME))
}

@Composable
fun OutputDateTimeHoriz(dateTime: LocalDateTime) {
    Row() {
        Column(horizontalAlignment = Alignment.Start
            ,modifier = Modifier
            .weight(1f)) {
            OutputDate(date = dateTime)
        }
        Column(horizontalAlignment = Alignment.End,modifier = Modifier
            .weight(1f)) {
            OutputTime(time = dateTime)
        }
    }
}

@Composable
fun OutputDateTimeVert(dateTime: LocalDateTime) {
    Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier
        .padding(16.dp)
        .fillMaxWidth(1f)
    ) {
        Row(modifier = Modifier
            .padding(horizontal = 4.dp)){
            OutputDate(date = dateTime)
        }
        Row(modifier = Modifier
            .padding(horizontal = 4.dp)) {
            OutputTime(time = dateTime)
        }
    }
}

@Composable
fun OutputDateTimeVertWithMillis(dateTime: LocalDateTime) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Row(modifier = Modifier
            .padding(horizontal = 4.dp)){
            OutputDate(date = dateTime)
        }
        Row(modifier = Modifier
            .padding(horizontal = 4.dp)) {
            OutputBigTime(time = dateTime)
        }
    }
}

@SuppressLint("NewApi")
@Preview
@Composable
fun PreviewOutputDateTime() {
    DateTimeCalculatorTheme {
        OutputDateTimeVert(dateTime = LocalDateTime.now())
    }
}

//@SuppressLint("NewApi")
//@Preview
//@Composable
//fun previewOutputDate() {
//    DateTimeCalculatorTheme {
//        outputDate(date = LocalDateTime.now())
//    }
//}
//@SuppressLint("NewApi")
//@Preview
//@Composable
//fun previewOutputTime() {
//    DateTimeCalculatorTheme {
//        outputTime(time = LocalDateTime.now())
//    }
//}
//@SuppressLint("NewApi")
//@Preview
//@Composable
//fun previewOutputBigTime() {
//    DateTimeCalculatorTheme {
//        outputBigTime(time = LocalDateTime.now())
//    }
//}