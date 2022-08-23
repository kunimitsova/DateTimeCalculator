package kunimitsova.valbee.datetimecalculator.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kunimitsova.valbee.datetimecalculator.R
import kunimitsova.valbee.datetimecalculator.ui.theme.DateTimeCalculatorTheme
import kunimitsova.valbee.datetimecalculator.utils.mainLocations
import kunimitsova.valbee.datetimecalculator.utils.navigatePopToHome

@Composable
fun BottomMenu(
    navController: NavHostController,
    currentBackStack: NavBackStackEntry?,
    currentDestination: NavDestination?
) {
    BottomNavigation {
        mainLocations.forEach {screen ->
            BottomNavigationItem(
                icon = {HeaderText(stringResource(id = screen.resourceId ))},
                label = null,
                selected = currentDestination?.hierarchy?.any() { it.route == screen.route }  == true,
                onClick = {
                    navController.navigatePopToHome(route = screen.route)
                },
                modifier = Modifier.padding(horizontal = 10.dp)
            )
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

@Composable
fun TopBarWithOverflow(isBackStackBase: Boolean, title: String, onNavigate: () -> Unit, onNavToHelp: () -> Unit) {
//    var showMenu by remember { mutableStateOf(false) }
    // isBackStackBase = navController.previousBackStackEntry == null
    TopAppBar(
        title = {Text(text= stringResource(id = R.string.app_name))},
        navigationIcon = if (!isBackStackBase) { // put in {} since it expects a lambda
            { IconButton(onClick = onNavigate) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                } }
        } else {
            { Spacer(modifier = Modifier.width(48.dp)) }
        },
        actions = {
            IconButton(onClick = onNavToHelp) {
                Icon(painter = painterResource(id = R.drawable.baseline_help_outline_24),
                    contentDescription = stringResource(id = R.string.overflow_desc))
            }
        }
    )

            // this will come in handy when we change from Help button to OVerflow menu
//            DropdownMenu(
//                expanded = showMenu,
//                onDismissRequest = { showMenu = false }
//            ) {
//                DropdownMenuItem(onClick = {  }) {
//                    Icon(Icons.Filled.Coffee)
//                }
//            }
}

@Preview
@Composable
fun topBarOverflowPreview() {
    TopBarWithOverflow(false,stringResource(id = R.string.app_name),onNavigate = { }, onNavToHelp = {})
}

/**
 * the next two functions are probably not going into the final thing.
 */

//@Composable
//fun twoTabs(
//    onTabSelected: () -> Unit,
//    currentScreen: String
//) {
//    Surface(
//        modifier = Modifier
//            .height(48.dp)
//            .fillMaxWidth()
//    ) {
//        Row(Modifier.selectableGroup()) {
//            oneTab(
//                text = stringResource( Screen.addScreen.resourceId),
//                onSelected = onTabSelected,
//                selected = (currentScreen == Screen.addScreen.route)
//            )
//            oneTab(
//                text = stringResource(id = Screen.dateDiff.resourceId),
//                onSelected = onTabSelected,
//                selected = (currentScreen == Screen.dateDiff.route)
//            )
//        }
//    }
//}
//
//@Composable
//private fun oneTab(
//    text: String,
//    onSelected: () -> Unit,
//    selected: Boolean
//) {
//    Row(
//        modifier = Modifier
//            .padding(16.dp)
//            .height(48.dp)
//            .selectable(
//                selected = selected,
//                onClick = onSelected,
//                role = Role.Tab,
////                interactionSource = remember { MutableInteractionSource() }
//            )
////            .clearAndSetSemantics { contentDescription = text }
//    ) {
//        val alpha = if (selected) 1f else 0.5f
//        val width = if (selected) 0.8f else 0.5f
//        BigText(text = text,
//            modifier = Modifier
//                .alpha(alpha)
//                .fillMaxWidth(width)
//        )
//    }
//}