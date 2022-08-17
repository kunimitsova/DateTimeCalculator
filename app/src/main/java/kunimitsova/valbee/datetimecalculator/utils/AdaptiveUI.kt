package kunimitsova.valbee.datetimecalculator.utils

import android.graphics.Rect
import androidx.window.layout.FoldingFeature
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

/**
 * I don't think I need DateClassic anymore since I changed how I'm doing things.
 * I will implement the adaptive stuff here, should it all be in one thing?
 */

data class UiState(
    val isLoading: Boolean,
    val isError: Boolean,
    val howToUseString: String?
)

sealed interface DevicePosture {
    object NormalPosture : DevicePosture

    data class BookPosture(
        val hingePosition: Rect
    ) : DevicePosture

    data class Separating(
        val hingePosition: Rect,
        var orientation: FoldingFeature.Orientation
    ) : DevicePosture
}

@OptIn(ExperimentalContracts::class)
fun isBookPosture(foldFeature: FoldingFeature?): Boolean {
    contract { returns(true) implies (foldFeature != null) }
    return foldFeature?.state == FoldingFeature.State.HALF_OPENED &&
            foldFeature.orientation == FoldingFeature.Orientation.VERTICAL
}

@OptIn(ExperimentalContracts::class)
fun isSeparating(foldFeature: FoldingFeature?): Boolean {
    contract { returns(true) implies (foldFeature != null) }
    return foldFeature?.state == FoldingFeature.State.FLAT && foldFeature.isSeparating
}

enum class dtcDisplayType {
    ONE_TAB, TWO_TABS
}

enum class dtcNavigationType {
    RAIL, TOP_TABS
}

//data class DateClassic(val year: Int,  val month: Int, val day: Int, val hour: Int,
//                       val minute: Int, val second: Int, val millis: Int
//) {
//    override fun toString(): String {
//        var str = "$year-${leadingZero(month, 2)}-${leadingZero(day, 2)}"
//        str = str + "T${leadingZero(hour, 2)}:${leadingZero(minute, 2)}"
//        str = str + ":${leadingZero(second, 2)}.${leadingZero(millis, 3)}"
//        return str
//    }
//    companion object {
//        @SuppressLint("NewApi")
//        fun fromString(dateString: String): DateClassic {
//            val dateTime = LocalDateTime.parse(dateString)
//            return DateClassic(dateTime.year, dateTime.monthValue, dateTime.dayOfMonth,
//                dateTime.hour, dateTime.minute, dateTime.second, (dateTime.nano / 1000000)
//            )
//            // divide by 1mil because DateClassic holds millis, not nanos.
//        }
//    }
//}


