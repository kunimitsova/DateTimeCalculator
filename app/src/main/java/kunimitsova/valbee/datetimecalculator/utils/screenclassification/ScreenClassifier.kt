package kunimitsova.valbee.datetimecalculator.utils.screenclassification

import android.graphics.Rect
import androidx.window.layout.FoldingFeature

//data class ScreenClassifier3(
//    val height: Dimension,
//    val width: Dimension,
//    val mode: PresentationSizeClass,
//    val foldingFeature: FoldingFeature?,
//)

data class ScreenClassifier2(
    val height: Dimension,
    val width: Dimension,
    val mode: PresentationSizeClass,
    val halfOpen: Boolean,
    val rect1: Rect,
    val isBookMode: Boolean? = null,
    val isTableMode: Boolean? = null,
    val hingePosition: Rect? = null,
    val rect2: Rect? = null,
    val canDualScreen: Boolean,
)
// this is the old version that I decided not to use , from a tutorial
//sealed interface ScreenClassifier {
//        val height: Dimension
//        val width: Dimension
//        val mode: PresentationSizeClass
//
//    sealed interface HalfOpened: ScreenClassifier {
//        val hingePosition: Rect
//        val hingeSeparationRatio: Float
//        val isSeparating: Boolean
//        val occlusionType: FoldingFeature.OcclusionType
//
//        data class BookMode(
//            override val height: Dimension,
//            override val width: Dimension,
//            override val mode: PresentationSizeClass,
//            override val hingePosition: Rect,
//            override val hingeSeparationRatio: Float,
//            override val isSeparating: Boolean,
//            override val occlusionType: FoldingFeature.OcclusionType
//        ): HalfOpened
//
//        data class TabletopMode(
//            override val height: Dimension,
//            override val width: Dimension,
//            override val mode: PresentationSizeClass,
//            override val hingePosition: Rect,
//            override val hingeSeparationRatio: Float,
//            override val isSeparating: Boolean,
//            override val occlusionType: FoldingFeature.OcclusionType
//        ): HalfOpened
//    }
//}