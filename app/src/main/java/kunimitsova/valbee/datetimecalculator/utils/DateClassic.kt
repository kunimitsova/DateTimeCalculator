package kunimitsova.valbee.datetimecalculator.utils

import kunimitsova.valbee.datetimecalculator.leadingZero

data class DateClassic(val day: Int, val month: Int, val year: Int){
    override fun toString(): String {
        return "$year-${leadingZero(month, 2)}-${leadingZero(day, 2)}"
    }
}
