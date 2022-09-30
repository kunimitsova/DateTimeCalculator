package kunimitsova.valbee.datetimecalculator.utils.screenclassification

import android.graphics.Rect
import androidx.compose.ui.unit.*
import androidx.window.layout.FoldingFeature
//import androidx.core.graphics.minus
import kunimitsova.valbee.datetimecalculator.ui.LayoutSetup
import kotlin.math.abs

/**
 * Functions for getting info about the screen size and hinge placement
 * and how the existing comps can fit into them.
 */

const val MIN_COLUMN_WIDTH = 320
const val MIN_ROW_HEIGHT = 320
const val MIN_SPACER = 16


fun getSegments(
    mode: PresentationSizeClass,
    foldingFeature: FoldingFeature?
): Int {
    val hinge = foldingFeature?.bounds
    var segments = 1
    // TODO() figure out how many max segments there are

    return segments
}

fun twoHalvesCanFit(rect1: Rect, rect2: Rect?, tableMode: Boolean?): Boolean {
    // two halves can only fit if there are two full size presentation rects
    val minSize = if (tableMode == true) MIN_ROW_HEIGHT else MIN_COLUMN_WIDTH
    val comp1 = if (tableMode == true) rect1.height() else rect1.width()
    val comp2 = if (rect2 == null) 0 else {
        if (tableMode == true) rect2.height() else rect2.width()
    }
    return (comp1 >= minSize) && (comp2 >= minSize)
}

fun bookModeLeftRect(dpSize: DpSize, hinge: Rect?): Rect {
    val left = 0
    val top = 0
    val right = hinge?.left ?: dpSize.width.value.toInt()
    val bottom = dpSize.height.value.toInt()
    return Rect(left, top, right, bottom)
}

fun bookModeRightRect(dpSize: DpSize, hinge: Rect?): Rect {
    val left = hinge?.right ?: 0
    val top = 0
    val right = dpSize.width.value.toInt()
    val bottom = dpSize.height.value.toInt()
    return Rect(left, top, right, bottom)
}

fun tableModeTopRect(dpSize: DpSize, hinge: Rect?): Rect {
    val left = 0
    val top = 0
    val right = dpSize.width.value.toInt()
    val bottom = hinge?.top ?: dpSize.height.value.toInt()
    return Rect(left, top, right, bottom)
}

fun tableModeBottomRect(dpSize: DpSize, hinge: Rect?): Rect {
    val left = 0
    val top = hinge?.bottom ?: 0
    val right = dpSize.width.value.toInt()
    val bottom = dpSize.height.value.toInt()
    return Rect(left, top, right, bottom)
}

fun diffOrNull(num1: Int, num2: Int?): Int {
  return abs(num1 -  (num2 ?: 0))
}

fun layoutStyle(mode: PresentationSizeClass): LayoutSetup {
    return when (mode) {
        PresentationSizeClass.Small -> LayoutSetup.ONE_ROW
        PresentationSizeClass.Tall -> LayoutSetup.ONE_COLUMN
        PresentationSizeClass.Wide -> LayoutSetup.ONE_ROW
        PresentationSizeClass.Big -> LayoutSetup.ONE_ROW
        PresentationSizeClass.BookBig -> LayoutSetup.ONE_ROW
        PresentationSizeClass.BookSmall -> LayoutSetup.ONE_ROW
        PresentationSizeClass.TableBig -> LayoutSetup.ONE_COLUMN
        PresentationSizeClass.TableSmall -> LayoutSetup.ONE_COLUMN
    }
}

fun getBigHalfTall(rect: Rect) : Rect {
    val width = rect.width() / 2
    val newRect = Rect(rect.left, rect.top, width, rect.bottom)
    return if (width >= MIN_COLUMN_WIDTH) {
        newRect
    } else {
        rect
    }
}

fun getBiggestRect (rect1: Rect, rect2: Rect?): Rect {
    // for when there is a big folding feature but there is not enough
    // room for dual screen. find the big rect to use for the
    // single screen presentation.
    val rectA = rect1
    val rectB = rect2 ?: Rect(0,0,0,0)

    val area1 = rectA.height() * rectA.width()
    val area2 = rectB.height() * rectB.width()
    return if (area2 > area1) rectB else rectA
}

fun middleSpacer(rect1: Rect, rect2: Rect?): Int {
    // get the spacer that matches the hinge area
    val rect2bang = rect2 ?: rect1

    // if both rects are the same, then there's no hinge
    return if (rect1 == rect2bang) {
        MIN_SPACER
    } else if (rect1.right == rect2bang.right)  {
        diffOrNull( rect2bang.top , rect1.bottom)
    } else {
        diffOrNull(rect1.right, rect2bang.left)
    }
}