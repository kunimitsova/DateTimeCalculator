package kunimitsova.valbee.datetimecalculator.utils.screenclassification

import android.app.Presentation
import android.graphics.Rect
import android.icu.text.CaseMap.Fold
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.window.layout.FoldingFeature
import androidx.window.layout.WindowLayoutInfo
import kunimitsova.valbee.datetimecalculator.ui.components.PreviewDropdown
import kotlin.contracts.contract

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
        val rect2 = if (bookMode) bookModeRightRect(windowDpSize, hingePosition) else
            tableModeBottomRect(windowDpSize, hingePosition)

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
        when (modeSizeClass) {
            PresentationSizeClass.Big -> {
                return true
            }
            else -> {
                return if (halfOpen) {
                    twoHalvesCanFit(rect1, rect2, bookMode, tableMode)
                } else {
                    false
                }
            }
        }
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