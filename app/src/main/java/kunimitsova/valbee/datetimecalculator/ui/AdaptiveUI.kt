package kunimitsova.valbee.datetimecalculator.utils

import android.graphics.Rect
import androidx.compose.ui.unit.dp
import androidx.window.layout.FoldingFeature
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

/**
 * I don't think I need DateClassic anymore since I changed how I'm doing things.
 * I will implement the adaptive stuff here, should it all be in one thing?
 */
//
//sealed interface DevicePosture23 {
//    object NormalPosture : DevicePosture
//
//    data class BookPosture(
//        val hingePosition: Rect
//    ) : DevicePosture
//
//    data class Separating(
//        val hingePosition: Rect,
//        var orientation: FoldingFeature.Orientation
//    ) : DevicePosture
//}
//
//@OptIn(ExperimentalContracts::class)
//fun isBookPosture(foldFeature: FoldingFeature?): Boolean {
//    contract { returns(true) implies (foldFeature != null) }
//    return foldFeature?.state == FoldingFeature.State.HALF_OPENED &&
//            foldFeature.orientation == FoldingFeature.Orientation.VERTICAL
//}
//
//@OptIn(ExperimentalContracts::class)
//fun isSeparating(foldFeature: FoldingFeature?): Boolean {
//    contract { returns(true) implies (foldFeature != null) }
//    return foldFeature?.state == FoldingFeature.State.FLAT && foldFeature.isSeparating
//}
//
//enum class dtcDisplayType {
//    ONE_COLUMN, TWO_COLUMNS
//}
//
//enum class dtcNavigationType {
//     BOTTOM_AND_TOP, TOP_ONLY
//}


