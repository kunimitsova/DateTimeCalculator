package kunimitsova.valbee.datetimecalculator.navigation

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kunimitsova.valbee.datetimecalculator.R
import kunimitsova.valbee.datetimecalculator.ui.screens.DateDifferenceScreen
import kunimitsova.valbee.datetimecalculator.ui.screens.DateTimeScreen
import kunimitsova.valbee.datetimecalculator.ui.screens.DoubleScreen
import kunimitsova.valbee.datetimecalculator.ui.screens.HelpScreen
import kunimitsova.valbee.datetimecalculator.utils.screenclassification.ScreenClassifier2

val mainLocations = listOf(
    Screen.addScreen,
    Screen.dateDiff
)

@Composable
fun DtcNavHost(
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
        composable(Screen.addScreen.route) {
            DateTimeScreen(screenClassifier = screenClassifier)
        }
        composable(Screen.dateDiff.route ) {
            DateDifferenceScreen(screenClassifier = screenClassifier)
        }
        composable(Screen.helpScreen.route ) {
            HelpScreen()
        }
    }
}

fun NavHostController.navigatePopToHome(route: String) =
    this.navigate(route) {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        if (this@navigatePopToHome.previousBackStackEntry?.destination?.route == Screen.helpScreen.route) {
            this@navigatePopToHome.backQueue.removeLastOrNull()
        }
        popUpTo(
            this@navigatePopToHome.graph.findStartDestination().id
        ) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }
fun NavHostController.navigateSingleTopTo(route:String) {
    // navigate so that only one of any given screen is in the stack
        this.navigate(route) {
            launchSingleTop = true
        }
}

fun NavHostController.navigateWithPopUp(
    toRoute: String,  // route name where you want to navigate
    fromRoute: String // route you want from popUpTo.
) {
    this.navigate(toRoute) {
        popUpTo(fromRoute) {
            inclusive = true
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}