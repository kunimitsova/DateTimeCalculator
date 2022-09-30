package kunimitsova.valbee.datetimecalculator.navigation

import androidx.annotation.StringRes
import kunimitsova.valbee.datetimecalculator.R

sealed class Screen( val route: String, @StringRes val resourceId: Int) {
    object AddScreen: Screen("addscreen", R.string.add_date)
    object DateDiff: Screen("datediff", R.string.date_difference)
    object HelpScreen: Screen("help", R.string.help)
    object DualScreen: Screen("dualscreen", R.string.double_screen)
}
