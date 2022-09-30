package kunimitsova.valbee.datetimecalculator.ui.menus

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
import kunimitsova.valbee.datetimecalculator.R
import kunimitsova.valbee.datetimecalculator.ui.theme.DateTimeCalculatorTheme

@Composable
fun TopBarWithOverflow(isBackStackBase: Boolean, onNavigate: () -> Unit, onNavToHelp: () -> Unit) {
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

            // this will come in handy when we change from Help button to Overflow menu
//            DropdownMenu(
//                expanded = showMenu,
//                onDismissRequest = { showMenu = false }
//            ) {
//                DropdownMenuItem(onClick = {  }) {
//                    Icon(Icons.Filled.Coffee)
//                }
//            }
}

@Composable
fun TopBarFullNav(
    isBackStackBase: Boolean, onNavigate: () -> Unit, onNavToHelp: () -> Unit,
    onNavToAdd: () -> Unit, onNavToDiff: () -> Unit
) {
// for when the window width size class = Medium (phone in landscape)
    TopAppBar(
        title = {},
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
            ActionsNavRow(
                onNavToAdd = onNavToAdd,
                onNavToDiff = onNavToDiff,
                onNavToHelp = onNavToHelp
            )
        }
    )
    // this will come in handy when we change from Help button to Overflow menu
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
fun TopBarOverflowPreview() {
    TopBarWithOverflow(false, onNavigate = { }) {}
}

@Preview(widthDp = 600)
@Composable
fun TopBarNavAllPreview() {
    DateTimeCalculatorTheme()
    {
    TopBarFullNav(
        isBackStackBase = false,
        onNavigate = { },
        onNavToHelp = { },
        onNavToAdd = { }) {

    }
    }
}