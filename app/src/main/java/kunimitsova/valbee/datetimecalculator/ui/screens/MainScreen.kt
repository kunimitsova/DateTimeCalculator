package kunimitsova.valbee.datetimecalculator.ui.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import kunimitsova.valbee.datetimecalculator.ui.theme.DateTimeCalculatorTheme
import kunimitsova.valbee.datetimecalculator.R
import kunimitsova.valbee.datetimecalculator.ui.components.BottomMenu
import kunimitsova.valbee.datetimecalculator.ui.components.TopBarWithOverflow
import kunimitsova.valbee.datetimecalculator.utils.*

@Composable
fun DateTimeMainScreen(modifier: Modifier = Modifier, navController: NavHostController = rememberNavController()) {
    DateTimeCalculatorTheme {
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination

        val scaffoldState = rememberScaffoldState()
//        val scope = rememberCoroutineScope()

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { TopBarWithOverflow(
                isBackStackBase = navController.previousBackStackEntry == null,
                title = stringResource(id = R.string.app_name),
                onNavigate = { navController.navigateUp() },
                // the help screen is weird so I'm trying to explicitly determine where it came from
                // in order to make it work right.
                onNavToHelp = { navController.navigatePopToHome(Screen.helpScreen.route) }
            )
                     },
            bottomBar = {
                BottomMenu(
                    navController = navController,
                    currentBackStack = currentBackStack,
                    currentDestination = currentDestination
                )
            },
         content = { innerPadding ->
             DtcNavHost(
                 navController = navController,
                 startDestination = Screen.addScreen.route,
                 modifier = Modifier
                     .fillMaxSize(1f)
                     .padding(innerPadding)
             )
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