package kunimitsova.valbee.datetimecalculator

data class DateClassic(val day: Int, val month: Int, val year: Int){
    override fun toString(): String {
        return "$year-${leadingZero(month, 2)}-${leadingZero(day, 2)}"
    }
}
