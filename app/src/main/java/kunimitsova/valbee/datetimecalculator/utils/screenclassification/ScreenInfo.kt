package kunimitsova.valbee.datetimecalculator.utils.screenclassification

import android.graphics.Rect
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.window.layout.FoldingFeature
import androidx.window.layout.WindowLayoutInfo

/**
 * The commented out stuff is the original code that this is based
 * on and additional me figuring out stuff. I'm leaving it here in
 * case I need to remember what I was thinking or in case I need
 * a function that the original screenInfo's were providing.
 *
 * I don't know if an "occlusion type" is a thing that shows up if
 * the device is unfolded and flat.
 */

//class ScreenInfo3 {
//    fun createClassifier(devicePosture: WindowLayoutInfo, windowDpSize: DpSize): ScreenClassifier3 {
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
//        val modeSizeClass =
//            getPresentationMode(windowWidthSizeClass, windowHeightSizeClass, foldingFeature)
//
//        return ScreenClassifier3(
//            height = pHeight,
//            width = pWidth,
//            mode = modeSizeClass,
//            foldingFeature = foldingFeature
//        )
//    }
//    private fun getPresentationMode(widthClass: WindowSizeClass,
//                                    heightClass: WindowSizeClass,
//                                    foldingFeature: FoldingFeature?,
//    ): PresentationSizeClass {
//        // basic size class regardless of folding feature:
//        val sizeMode = when {
//            (widthClass == WindowSizeClass.Compact && heightClass == WindowSizeClass.Compact) -> PresentationSizeClass.Small
//            (widthClass != WindowSizeClass.Compact && heightClass == WindowSizeClass.Compact) -> PresentationSizeClass.Wide
//            (widthClass == WindowSizeClass.Compact && heightClass != WindowSizeClass.Compact) -> PresentationSizeClass.Tall
//            else -> PresentationSizeClass.Big
//        }
//        // if there's a folding feature, determine if the fold is horizontal or vertical
//        // if there is no folding feature, no more calculations need to be done
//        val foldMode = when {
//            (foldingFeature == null) -> sizeMode
//            (foldingFeature.state == FoldingFeature.State.HALF_OPENED &&
//                    foldingFeature.orientation == FoldingFeature.Orientation.HORIZONTAL) -> {
//                if (widthClass != WindowSizeClass.Compact) PresentationSizeClass.TableBig else PresentationSizeClass.TableSmall
//            } // the logic is this: if the fold is horizontal  but the width class is compact,
//            // that means it's not an unfolded tablet size but something phone-sized with a fold.
//            // If the orientation of the fold is vertical and the height class is compact, that
//            // means it is a phone-sized device with a fold. Remember the fold orientation
//            // for a phone-shaped device will be perpendicular to the orientation of the device
//            // itself. It's a bit confusing.
//
//            (foldingFeature.state == FoldingFeature.State.HALF_OPENED &&
//                    foldingFeature.orientation == FoldingFeature.Orientation.VERTICAL) -> {
//                if (heightClass != WindowSizeClass.Compact) PresentationSizeClass.BookBig else PresentationSizeClass.BookSmall
//            }
//
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
      //  val separating = foldingFeature?.isSeparating
      //  val occlusionType = foldingFeature?.occlusionType

        val rect1 = if (bookMode) bookModeLeftRect(windowDpSize, hingePosition) else
            tableModeTopRect(windowDpSize, hingePosition)
        val rect2 = if (halfOpen) {
            if (bookMode) bookModeRightRect(windowDpSize, hingePosition) else
                tableModeBottomRect(windowDpSize, hingePosition)
        } else null

        val modeSizeClass = getPresentationClass(
            halfOpen, tableMode, windowWidthSizeClass,
            windowHeightSizeClass
        )
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
        val b1: Boolean = when (modeSizeClass) {
            PresentationSizeClass.Big -> {
                true
            }

            else -> {
                if (halfOpen) {
                    twoHalvesCanFit(rect1, rect2, tableMode)
                } else {
                    false
                }
            }
        }
        return b1
    }

    private fun getPresentationClass(
        halfOpen: Boolean,
        tableMode: Boolean,
        widthClass: WindowSizeClass,
        heightClass: WindowSizeClass,
   ): PresentationSizeClass {
       // default is med or exp X med or exp, no folding feature

        val class1: PresentationSizeClass = if (halfOpen) {
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