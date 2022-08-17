package kunimitsova.valbee.datetimecalculator.ui.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kunimitsova.valbee.datetimecalculator.ui.theme.DateTimeCalculatorTheme
import kunimitsova.valbee.datetimecalculator.R

@Composable
fun DateTimeMainScreen(modifier: Modifier = Modifier) {
    DateTimeCalculatorTheme {
        Scaffold(
            topBar = { TopAppBar(title = {
            Text(stringResource(id = R.string.app_name)) },
            navigationIcon = {
                IconButton(onClick = { }) {
                    Icon(Icons.Filled.ArrowBack, "back icon")
                }
            }
        ) },
         content = { innerPadding ->
            // HelpScreen( modifier = Modifier.padding(innerPadding))
            DateTimeScreen(modifier = Modifier.padding(innerPadding).fillMaxSize(1f))
        }
        )
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    DateTimeCalculatorTheme {
        DateTimeMainScreen()
    }
}