package kunimitsova.valbee.datetimecalculator.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpSize
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.window.layout.WindowLayoutInfo
import kotlinx.coroutines.flow.StateFlow
import kunimitsova.valbee.datetimecalculator.ui.theme.DateTimeCalculatorTheme
import kunimitsova.valbee.datetimecalculator.R
import kunimitsova.valbee.datetimecalculator.navigation.DtcNavHost
import kunimitsova.valbee.datetimecalculator.navigation.MainNavHost
import kunimitsova.valbee.datetimecalculator.navigation.Screen
import kunimitsova.valbee.datetimecalculator.navigation.navigatePopToHome
import kunimitsova.valbee.datetimecalculator.ui.components.TopBarFullNav
import kunimitsova.valbee.datetimecalculator.ui.components.TopBarWithOverflow
import kunimitsova.valbee.datetimecalculator.ui.menus.BottomMenu
import kunimitsova.valbee.datetimecalculator.ui.menus.BottomMenuHelpOnly
import kunimitsova.valbee.datetimecalculator.utils.*
import kunimitsova.valbee.datetimecalculator.utils.screenclassification.PresentationSizeClass
import kunimitsova.valbee.datetimecalculator.utils.screenclassification.ScreenInfo2

@Composable
fun DateTimeMainScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    windowDpSize: DpSize,
    devicePosture: StateFlow<WindowLayoutInfo>
) {
    DateTimeCalculatorTheme {
        val currentBackStack by navController.currentBackStackEntryAsState()
        val currentDestination = currentBackStack?.destination
        val navToHelp = { navController.navigatePopToHome(Screen.helpScreen.route)}

        val devicePostureValue by devicePosture.collectAsState()
        val screenClassifier by remember { mutableStateOf( ScreenInfo2().createClassifier(
            devicePostureValue,
            windowDpSize)) }

        val startScreen = if (screenClassifier.canDualScreen) Screen.dualScreen.route else Screen.singleScreen.route

        val scaffoldState = rememberScaffoldState()
//        val scope = rememberCoroutineScope()

        Scaffold(
            scaffoldState = scaffoldState,
            backgroundColor = MaterialTheme.colors.secondary,
            topBar = {
                when (screenClassifier.mode) {
                    // only two options right now
                    PresentationSizeClass.Small, PresentationSizeClass.Wide ->
                        // when the size is compact x compact or compact x [bigger]
                        TopBarFullNav(
                            isBackStackBase = navController.previousBackStackEntry == null,
                            title = "",
                            onNavigate = { navController.navigateUp() },
                            onNavToHelp = navToHelp,
                            onNavToAdd = { navController.navigatePopToHome(Screen.addScreen.route) },
                            onNavToDiff = { navController.navigatePopToHome(Screen.dateDiff.route)}
                        )
                    else ->
                        TopBarWithOverflow(
                            isBackStackBase = navController.previousBackStackEntry == null,
                            title = stringResource(id = R.string.app_name),
                            onNavigate = { navController.navigateUp() },
                            onNavToHelp = navToHelp
                        )
                }
            },
            bottomBar = {
                when (screenClassifier.mode) {
                    PresentationSizeClass.Wide, PresentationSizeClass.Small -> {}
                    PresentationSizeClass.Big, PresentationSizeClass.TableBig,
                        PresentationSizeClass.BookBig -> if (screenClassifier.canDualScreen) {
                            BottomMenuHelpOnly(
                            onNavToHelp = navToHelp,
                            currentBackStack = currentBackStack,
                            currentDestination = currentDestination
                        )
                    }
                    else -> BottomMenu(
                        navController = navController,
                        currentBackStack = currentBackStack,
                        currentDestination = currentDestination
                    )
                }
            },
         content = { innerPadding ->
             MainNavHost(
                 navController = navController,
                 startDestination = startScreen,
                 screenClassifier = screenClassifier,
                 modifier = modifier
                     .fillMaxSize(1f)
                     .padding(innerPadding)
             )
        }
        )
    }
}