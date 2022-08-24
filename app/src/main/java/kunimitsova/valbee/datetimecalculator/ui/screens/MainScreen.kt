package kunimitsova.valbee.datetimecalculator.ui.screens

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.window.layout.FoldingFeature
import kunimitsova.valbee.datetimecalculator.ui.theme.DateTimeCalculatorTheme
import kunimitsova.valbee.datetimecalculator.R
import kunimitsova.valbee.datetimecalculator.ui.components.BottomMenu
import kunimitsova.valbee.datetimecalculator.ui.components.TopBarFullNav
import kunimitsova.valbee.datetimecalculator.ui.components.TopBarWithOverflow
import kunimitsova.valbee.datetimecalculator.utils.*

@Composable
fun DateTimeMainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    windowSize: WindowWidthSizeClass,
    foldingDevicePosture: DevicePosture
) {
    DateTimeCalculatorTheme {
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination
        val navToHelp = { navController.navigatePopToHome(Screen.helpScreen.route)}

        val scaffoldState = rememberScaffoldState()
//        val scope = rememberCoroutineScope()

        Scaffold(
            scaffoldState = scaffoldState,
            backgroundColor = MaterialTheme.colors.secondary,
            topBar = {
                when (windowSize) {
                    // only two options right now
                    WindowWidthSizeClass.Compact ->
                        TopBarWithOverflow(
                            isBackStackBase = navController.previousBackStackEntry == null,
                            title = stringResource(id = R.string.app_name),
                            onNavigate = { navController.navigateUp() },
                            onNavToHelp = navToHelp
                        )
                    else ->
                        TopBarFullNav(
                            isBackStackBase = navController.previousBackStackEntry == null,
                            title = "",
                            onNavigate = { navController.navigateUp() },
                            onNavToHelp = navToHelp,
                            onNavToAdd = { navController.navigatePopToHome(Screen.addScreen.route) },
                            onNavToDiff = { navController.navigatePopToHome(Screen.dateDiff.route)}
                        )
                }
            },
            bottomBar = {
                when (windowSize) {
                    WindowWidthSizeClass.Medium -> {}
                    else -> BottomMenu(
                        navController = navController,
                        currentBackStack = currentBackStack,
                        currentDestination = currentDestination
                    )
                }
            },
         content = { innerPadding ->
             DtcNavHost(
                 navController = navController,
                 startDestination = Screen.addScreen.route,
                 windowSize = windowSize,
                 modifier = Modifier
                     .fillMaxSize(1f)
                     .padding(innerPadding)
             )
        }
        )
    }
}