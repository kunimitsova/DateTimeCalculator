package kunimitsova.valbee.datetimecalculator.utils.screenclassification

import android.graphics.Rect
import androidx.compose.ui.unit.*
import androidx.core.graphics.minus
import kunimitsova.valbee.datetimecalculator.ui.LayoutSetup
import kotlin.math.abs

const val MIN_COLUMN_WIDTH = 320
const val MIN_ROW_HEIGHT = 320
const val MIN_SPACER = 16


fun twoHalvesCanFit(rect1: Rect, rect2: Rect?,
                    bookMode: Boolean?, tableMode: Boolean?): Boolean {
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
    val right = if (hinge == null) dpSize.width.value.toInt() else hinge.left
    val bottom = dpSize.height.value.toInt()
    return Rect(left, top, right, bottom)
}

fun bookModeRightRect(dpSize: DpSize, hinge: Rect?): Rect {
    val left = if (hinge == null) 0 else hinge.right
    val top = 0
    val right = dpSize.width.value.toInt()
    val bottom = dpSize.height.value.toInt()
    return Rect(left, top, right, bottom)
}

fun tableModeTopRect(dpSize: DpSize, hinge: Rect?): Rect {
    val left = 0
    val top = 0
    val right = dpSize.width.value.toInt()
    val bottom = if (hinge == null) dpSize.height.value.toInt() else hinge.top
    return Rect(left, top, right, bottom)
}

fun tableModeBottomRect(dpSize: DpSize, hinge: Rect?): Rect {
    val left = 0
    val top = if (hinge == null) 0 else hinge.bottom
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
    if (width >= MIN_COLUMN_WIDTH) {
        return newRect
    } else {
        return rect
    }
}

fun getBiggestRect (rect1: Rect, rect2: Rect): Rect {
    // for when there is a big folding feature but there is not enough
    // room for dual screen. find the big rect to use for the
    // single screen presentation.

    val area1 = rect1.height() * rect1.width()
    val area2 = rect2.height() * rect2.width()
    return if (area2 > area1) rect2 else rect1
}

fun middleSpacer(rect1: Rect, rect2: Rect?): Int {
    // get the spacer that matches the hinge area
    val rect2bang = if (rect2 == null) rect1 else rect2

    // if both rects are the same, then there's no hinge
    return if (rect1 == rect2bang) {
        MIN_SPACER
    } else if (rect1.right == rect2bang.right)  {
        diffOrNull( rect2bang.top , rect1.bottom)
    } else {
        diffOrNull(rect1.right, rect2bang.left)
    }
}