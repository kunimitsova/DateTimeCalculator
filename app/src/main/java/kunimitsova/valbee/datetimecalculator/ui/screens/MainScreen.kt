package kunimitsova.valbee.datetimecalculator.ui.screens


import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.DpSize
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.window.layout.WindowLayoutInfo
import kotlinx.coroutines.flow.StateFlow
import kunimitsova.valbee.datetimecalculator.ui.theme.DateTimeCalculatorTheme
import kunimitsova.valbee.datetimecalculator.navigation.DtcNavHost
import kunimitsova.valbee.datetimecalculator.navigation.Screen
import kunimitsova.valbee.datetimecalculator.navigation.navigatePopToHome
import kunimitsova.valbee.datetimecalculator.ui.menus.TopBarFullNav
import kunimitsova.valbee.datetimecalculator.ui.menus.TopBarWithOverflow
import kunimitsova.valbee.datetimecalculator.ui.menus.BottomMenu
import kunimitsova.valbee.datetimecalculator.ui.menus.BottomMenuHelpOnly
import kunimitsova.valbee.datetimecalculator.utils.screenclassification.PresentationSizeClass
import kunimitsova.valbee.datetimecalculator.utils.screenclassification.ScreenInfo2
import kunimitsova.valbee.datetimecalculator.utils.screenclassification.ScreenInfo3

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
        val navToHelp = { navController.navigatePopToHome(Screen.HelpScreen.route)}

        val devicePostureValue by devicePosture.collectAsState()
        val screenClassifier by remember { mutableStateOf( ScreenInfo2().createClassifier(
            devicePostureValue,
            windowDpSize)) }

        // not sure this is how to do this:
        val startScreen = remember { mutableStateOf( if (screenClassifier.canDualScreen) Screen.DualScreen.route else Screen.AddScreen.route) }

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
                            onNavigate = { navController.navigateUp() },
                            onNavToHelp = navToHelp,
                            onNavToAdd = { navController.navigatePopToHome(Screen.AddScreen.route) }
                        ) { navController.navigatePopToHome(Screen.DateDiff.route) }

                    else ->
                        TopBarWithOverflow(
                            isBackStackBase = navController.previousBackStackEntry == null,
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
                                currentDestination = currentDestination
                            )
                    }
                    else -> BottomMenu(
                        navController = navController,
                        currentDestination = currentDestination
                    )
                }
            },
         content = { innerPadding ->
             DtcNavHost(
                 navController = navController,
                 startDestination = startScreen.value,
                 screenClassifier = screenClassifier,
                 modifier = modifier
                     .fillMaxSize(1f)
                     .padding(innerPadding)
             )
        }
        )
    }
}