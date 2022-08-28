package kunimitsova.valbee.datetimecalculator.ui.menus

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kunimitsova.valbee.datetimecalculator.navigation.mainLocations
import kunimitsova.valbee.datetimecalculator.navigation.navigatePopToHome
import kunimitsova.valbee.datetimecalculator.ui.components.reusables.HeaderText
import kunimitsova.valbee.datetimecalculator.ui.theme.DateTimeCalculatorTheme
import kunimitsova.valbee.datetimecalculator.utils.screenclassification.ScreenClassifier2


@Composable
fun BottomMenu(
    navController: NavHostController,
    currentBackStack: NavBackStackEntry?,
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
                    selected = currentDestination?.hierarchy?.any() { it.route == screen.route } == true,
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
    navController: NavHostController,
    currentBackStack: NavBackStackEntry?,
    currentDestination: NavDestination?
) {
    BottomNavigation(
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Row(horizontalArrangement = Arrangement.Center) {
            
        }
    }

}

@Preview
@Composable
fun BottomMenuPreview() {
    DateTimeCalculatorTheme {
        BottomMenu(
            navController = rememberNavController(),
            currentBackStack = null,
            currentDestination = null
        )
    }
}