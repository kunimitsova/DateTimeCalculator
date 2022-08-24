package kunimitsova.valbee.datetimecalculator.utils

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
import kunimitsova.valbee.datetimecalculator.ui.screens.HelpScreen

sealed class Screen( val route: String, @StringRes val resourceId: Int) {
    object addScreen: Screen("addscreen", R.string.add_date)
    object dateDiff: Screen("datediff", R.string.date_difference)
    object helpScreen: Screen("help", R.string.help)
}

val mainLocations = listOf(
    Screen.addScreen,
    Screen.dateDiff
)

@Composable
fun DtcNavHost(
    navController: NavHostController,
    startDestination: String,
    windowSize: WindowWidthSizeClass,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(Screen.addScreen.route) {
            DateTimeScreen(windowSize = windowSize)
        }
        composable(Screen.dateDiff.route ) {
            DateDifferenceScreen(windowSize = windowSize)
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