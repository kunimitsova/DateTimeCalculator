package kunimitsova.valbee.datetimecalculator.navigation

import androidx.annotation.StringRes
import kunimitsova.valbee.datetimecalculator.R

sealed class Screen( val route: String, @StringRes val resourceId: Int) {
    object addScreen: Screen("addscreen", R.string.add_date)
    object dateDiff: Screen("datediff", R.string.date_difference)
    object helpScreen: Screen("help", R.string.help)
    object dualScreen: Screen("dualscreen", R.string.double_screen)
}
