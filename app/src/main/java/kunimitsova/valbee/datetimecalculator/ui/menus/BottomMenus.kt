package kunimitsova.valbee.datetimecalculator.ui.menus

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import kunimitsova.valbee.datetimecalculator.R
import kunimitsova.valbee.datetimecalculator.navigation.Screen
import kunimitsova.valbee.datetimecalculator.navigation.mainLocations
import kunimitsova.valbee.datetimecalculator.navigation.navigatePopToHome
import kunimitsova.valbee.datetimecalculator.ui.components.reusables.HeaderText


@Composable
fun BottomMenu(
    navController: NavHostController,
    currentDestination: NavDestination?
) {
    // we only need full navigation when it's in Tall mode
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.secondary,
        elevation = 0.dp,
        modifier = Modifier.padding(vertical = 4.dp)
        // horizontal padding are in the content
    ) {
        Spacer(modifier = Modifier.width(4.dp)) // starting external padding
        mainLocations.forEach { screen ->
            Surface(color = MaterialTheme.colors.primary,
                elevation = 1.dp,
                shape = MaterialTheme.shapes.large,
                border = BorderStroke(1.dp, MaterialTheme.colors.primaryVariant),
                modifier = Modifier.weight(0.8f)
            ) {
                BottomNavigationItem(
                    icon = { HeaderText(stringResource(id = screen.resourceId)) },
                    label = null,
                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                    onClick = {
                        navController.navigatePopToHome(route = screen.route)
                    },
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                )
            }
            Spacer(modifier = Modifier.width(4.dp)) // ending external padding, one per item.
        }
    }
}

@Composable
fun BottomMenuHelpOnly(
    onNavToHelp: () -> Unit,
    currentDestination: NavDestination?
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.fillMaxWidth(1f)
    ) {
        Row(modifier = Modifier.fillMaxWidth(0.5f),
            horizontalArrangement = Arrangement.End
        ) {
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_help_outline_24),
                        contentDescription = stringResource(
                            id = R.string.help
                        )
                    )
                },
                label = null,
                selected = currentDestination?.hierarchy?.any {
                    it.route == Screen.HelpScreen.route
                } == true,
                onClick = onNavToHelp,
                modifier = Modifier.weight(2f)
            )
        }
    }
}

//@Preview
//@Composable
//fun BottomMenuPreview() {
//    DateTimeCalculatorTheme {
//        BottomMenuHelpOnly(
//            onNavToHelp = {},
//            currentBackStack = null,
//            currentDestination = null
//        )
//    }
//}