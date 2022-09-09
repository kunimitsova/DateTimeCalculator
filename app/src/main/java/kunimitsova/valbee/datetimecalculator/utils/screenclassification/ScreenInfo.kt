package kunimitsova.valbee.datetimecalculator.utils.screenclassification

import android.app.Presentation
import android.graphics.Rect
import android.icu.text.CaseMap.Fold
import android.icu.text.CaseMap.fold
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.window.layout.FoldingFeature
import androidx.window.layout.WindowLayoutInfo
import kunimitsova.valbee.datetimecalculator.ui.components.PreviewDropdown
import kotlin.contracts.contract

/**
 * Maybe will switch to the ScreenInfo3 later.
 */

//class ScreenInfo3 {
//    fun createClassifier(devicePosture: WindowLayoutInfo, windowDpSize: DpSize){
//        val screen3: ScreenClassifier3
//
//        val foldingFeature = devicePosture.displayFeatures.find {
//            it is FoldingFeature
//        } as? FoldingFeature
//
//        val windowHeightSizeClass = when {
//            windowDpSize.height < 480.dp -> WindowSizeClass.Compact
//            windowDpSize.height < 900.dp -> WindowSizeClass.Medium
//            else -> WindowSizeClass.Expanded
//        }
//        val windowWidthSizeClass = when {
//            windowDpSize.width < 600.dp -> WindowSizeClass.Compact
//            windowDpSize.width < 840.dp -> WindowSizeClass.Medium
//            else -> WindowSizeClass.Expanded
//        }
//
//        val pHeight = Dimension(windowDpSize.height, windowHeightSizeClass)
//        val pWidth = Dimension(windowDpSize.width, windowWidthSizeClass)
//
//        val modeSizeClass = getPresentationMode(windowWidthSizeClass, windowHeightSizeClass, foldingFeature)
//        val segments = getSegments(modeSizeClass, foldingFeature)
//
//        screen3 = ScreenClassifier3(
//            height = pHeight,
//            width = pWidth,
//            mode = modeSizeClass,
//            segments = segments,
//            foldingFeature = foldingFeature
//        )
//    }
//
//    private fun getSegments(
//        mode: PresentationSizeClass,
//        foldingFeature: FoldingFeature?
//    ): Int {
//        val hinge = if (foldingFeature == null) null else foldingFeature.bounds
//        var segments = 1
//         // TODO() figure out how many max segments there are
//
//        return segments
//    }
//
//    fun getPresentationMode(widthClass: WindowSizeClass,
//                            heightClass: WindowSizeClass,
//                            foldingFeature: FoldingFeature?,
//    ): PresentationSizeClass {
//        val sizeMode = when {
//            (widthClass == WindowSizeClass.Compact && heightClass == WindowSizeClass.Compact) -> PresentationSizeClass.Small
//            (widthClass != WindowSizeClass.Compact && heightClass == WindowSizeClass.Compact) -> PresentationSizeClass.Wide
//            (widthClass == WindowSizeClass.Compact && heightClass != WindowSizeClass.Compact) -> PresentationSizeClass.Tall
//            else -> PresentationSizeClass.Big
//        }
//        val foldMode = when {
//            (foldingFeature == null) -> sizeMode
//            (foldingFeature.state == FoldingFeature.State.HALF_OPENED &&
//                    foldingFeature.orientation == FoldingFeature.Orientation.HORIZONTAL) -> {
//                        if (widthClass != WindowSizeClass.Compact) PresentationSizeClass.TableBig else PresentationSizeClass.TableSmall
//                    }
//            (foldingFeature.state == FoldingFeature.State.HALF_OPENED &&
//                    foldingFeature.orientation == FoldingFeature.Orientation.VERTICAL) -> {
//                        if (heightClass != WindowSizeClass.Compact) PresentationSizeClass.BookBig else PresentationSizeClass.BookSmall
//                    }
//            else -> sizeMode // big size mode is always two columns unless it's half-open
//        }
//        return foldMode
//    }
//}

class ScreenInfo2{
    fun createClassifier(devicePosture: WindowLayoutInfo, windowDpSize: DpSize): ScreenClassifier2 {

        val screen2: ScreenClassifier2

        val foldingFeature = devicePosture.displayFeatures.find {
            it is FoldingFeature
        } as? FoldingFeature

        val windowHeightSizeClass = when {
            windowDpSize.height < 480.dp -> WindowSizeClass.Compact
            windowDpSize.height < 900.dp -> WindowSizeClass.Medium
            else -> WindowSizeClass.Expanded
        }
        val windowWidthSizeClass = when {
            windowDpSize.width < 600.dp -> WindowSizeClass.Compact
            windowDpSize.width < 840.dp -> WindowSizeClass.Medium
            else -> WindowSizeClass.Expanded
        }

        val pHeight = Dimension(windowDpSize.height, windowHeightSizeClass)
        val pWidth = Dimension(windowDpSize.width, windowWidthSizeClass)
        val halfOpen = foldingFeature?.state == FoldingFeature.State.HALF_OPENED
        val foldOrientation = foldingFeature?.orientation
        val bookMode = halfOpen && (foldOrientation == FoldingFeature.Orientation.VERTICAL )
        val tableMode = halfOpen && (foldOrientation == FoldingFeature.Orientation.HORIZONTAL)
        val hingePosition = foldingFeature?.bounds
        val separating = foldingFeature?.isSeparating
        val occlusionType = foldingFeature?.occlusionType

        val rect1 = if (bookMode) bookModeLeftRect(windowDpSize, hingePosition) else
            tableModeTopRect(windowDpSize, hingePosition)
        val rect2 = if (halfOpen) {
            if (bookMode) bookModeRightRect(windowDpSize, hingePosition) else
                tableModeBottomRect(windowDpSize, hingePosition)
        } else null

        val modeSizeClass = getPresentationClass(halfOpen, bookMode, tableMode,
            windowWidthSizeClass, windowHeightSizeClass)
        // determine if both screens will fit in the presentation mode
        val twoHalves = getTwoHalves(halfOpen, bookMode, tableMode, rect1, rect2, modeSizeClass)

        screen2 = ScreenClassifier2(
            height = pHeight,
            width = pWidth,
            mode = modeSizeClass,
            halfOpen = halfOpen,
            isBookMode = if (halfOpen) bookMode else null,
            isTableMode = if (halfOpen) tableMode else null,
            hingePosition = if (halfOpen) hingePosition else null,
            rect1 = rect1,
            // rect2 is only valid if there is a foldingFeature
            rect2 = if (halfOpen) rect2 else null,
            canDualScreen = twoHalves,
        )
        return screen2
    }

    private fun getTwoHalves(
        halfOpen: Boolean,
        bookMode: Boolean?,
        tableMode: Boolean?,
        rect1: Rect,
        rect2: Rect?,
        modeSizeClass: PresentationSizeClass
    ): Boolean {
        var b1 = false
        when (modeSizeClass) {
            PresentationSizeClass.Big -> {
                b1 = true
            }
            else -> {
               if (halfOpen) {
                   b1 = twoHalvesCanFit(rect1, rect2, bookMode, tableMode)
                } else {
                   b1 = false
                }
            }
        }
        return b1
    }

    fun getPresentationClass(
       halfOpen: Boolean,
       bookMode: Boolean,
       tableMode: Boolean,
       widthClass: WindowSizeClass,
       heightClass: WindowSizeClass,
   ): PresentationSizeClass {
       // default is med or exp X med or exp, no folding feature
       var class1: PresentationSizeClass = PresentationSizeClass.Big

       class1 = if (halfOpen) {
           // folding feature active
           if (tableMode) {
               if (widthClass == WindowSizeClass.Compact) {  // tall with hinge
                   PresentationSizeClass.TableSmall
               } else {
                   PresentationSizeClass.TableBig  // big with horiz hinge
               }
           } else {
               if (heightClass == WindowSizeClass.Compact) {
                   PresentationSizeClass.BookSmall  // wide with hinge
               } else {
                   PresentationSizeClass.BookBig // big with vert hinge
               }
           }
       } else {
           // no folding feature (flat)
           if (widthClass == WindowSizeClass.Compact) {
               // possible options are Small and Tall
               if (heightClass == WindowSizeClass.Compact) {
                   PresentationSizeClass.Small // compact x compact
               } else {
                   PresentationSizeClass.Tall // compact x med/exp
               }
           } else {
               if (heightClass == WindowSizeClass.Compact) {
                   PresentationSizeClass.Wide // med/exp x compact
               } else {
                   PresentationSizeClass.Big // med/exp x med/exp
               }
           }
       }
       return class1
   }
}

//class ScreenInfo {
//    fun createClassifier(devicePosture: WindowLayoutInfo, windowDpSize: DpSize): ScreenClassifier {
//        val foldingFeature = devicePosture.displayFeatures.find {
//            it is FoldingFeature
//        } as? FoldingFeature
//
//        return if (foldingFeature == null) {
//            createFullyOpenedDevice(windowDpSize)
//        } else if (isBookMode(foldingFeature)) {
//            createBookModeObject(foldingFeature)
//        } else if (isTabletopMode(foldingFeature)) {
//            createTableTopModeObject(foldingFeature)
//        } else {
//            createFullyOpenedDevice(windowDpSize)
//        }
//    }
//    private fun createTableTopModeObject(foldingFeature: FoldingFeature):
//            ScreenClassifier.HalfOpened.TabletopMode {
//        val screenHeight = foldingFeature.bounds.top + foldingFeature.bounds.bottom
//        val ratio = foldingFeature.bounds.top.toFloat() / screenHeight.toFloat()
//
//        return ScreenClassifier.HalfOpened.TabletopMode(
//            hingePosition = foldingFeature.bounds,
//            hingeSeparationRatio = ratio,
//            isSeparating = foldingFeature.isSeparating,
//            occlusionType = foldingFeature.occlusionType
//        )
//    }
//
//    private fun createBookModeObject(foldingFeature: FoldingFeature):
//            ScreenClassifier.HalfOpened.BookMode {
//        val screenWidth = foldingFeature.bounds.left + foldingFeature.bounds.right
//        val ratio = foldingFeature.bounds.left.toFloat() / screenWidth.toFloat()
//
//        return ScreenClassifier.HalfOpened.BookMode(
//            hingePosition = foldingFeature.bounds,
//            hingeSeparationRatio = ratio,
//            isSeparating = foldingFeature.isSeparating,
//            occlusionType = foldingFeature.occlusionType
//        )
//    }
//
//    fun createFullyOpenedDevice(windowDpSize: DpSize): ScreenClassifier {
//        val windowHeightSizeClass = when {
//                windowDpSize.height < 480.dp -> WindowSizeClass.Compact
//                windowDpSize.height < 900.dp -> WindowSizeClass.Medium
//                else -> WindowSizeClass.Expanded
//            }
//        val windowWidthSizeClass = when {
//                windowDpSize.width < 600.dp -> WindowSizeClass.Compact
//                windowDpSize.width < 840.dp -> WindowSizeClass.Medium
//                else -> WindowSizeClass.Expanded
//            }
//        val modeSizeClass = when (windowHeightSizeClass) {
//            WindowSizeClass.Compact -> {
//                when (windowWidthSizeClass) {
//                    WindowSizeClass.Compact -> PresentationSizeClass.Small
//                    else -> PresentationSizeClass.Wide } }
//            else -> {
//                when (windowWidthSizeClass) {
//                    WindowSizeClass.Compact -> PresentationSizeClass.Tall
//                    else -> PresentationSizeClass.Big } }
//        }
//
//        return ScreenClassifier (
//            height = Dimension(
//                dp = windowDpSize.height,
//                sizeClass = windowHeightSizeClass
//            ),
//            width = Dimension(
//                dp = windowDpSize.width,
//                sizeClass = windowWidthSizeClass
//            ),
//            mode = modeSizeClass
//        )
//    }
//
//    private fun isBookMode(foldingFeature: FoldingFeature) =
//        foldingFeature.state == FoldingFeature.State.HALF_OPENED &&
//                foldingFeature.orientation == FoldingFeature.Orientation.VERTICAL
//
//    private fun isTabletopMode(foldingFeature: FoldingFeature) =
//        foldingFeature.state == FoldingFeature.State.HALF_OPENED &&
//                foldingFeature.orientation == FoldingFeature.Orientation.HORIZONTAL
//}