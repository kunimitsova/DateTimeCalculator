package kunimitsova.valbee.datetimecalculator.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import kunimitsova.valbee.datetimecalculator.ui.screens.DateDifferenceScreen
import kunimitsova.valbee.datetimecalculator.ui.screens.DateTimeScreen
import kunimitsova.valbee.datetimecalculator.ui.screens.DoubleScreen
import kunimitsova.valbee.datetimecalculator.ui.screens.HelpScreen
import kunimitsova.valbee.datetimecalculator.utils.screenclassification.ScreenClassifier2

@Composable
fun MainNavHost(
    navController: NavHostController,
    startDestination: String,
    screenClassifier: ScreenClassifier2,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Screen.dualScreen.route) {
            DoubleScreen(screenClassifier = screenClassifier)
        }
        composable(Screen.singleScreen.route ) {
            DtcNavHost(
                navController = rememberNavController(),
                startDestination = Screen.addScreen.route,
                screenClassifier = screenClassifier
            )
        }
        composable(Screen.helpScreen.route ) {
            HelpScreen()
        }
    }
}