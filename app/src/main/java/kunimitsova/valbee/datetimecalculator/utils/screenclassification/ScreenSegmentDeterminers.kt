package kunimitsova.valbee.datetimecalculator.utils.screenclassification

import android.graphics.Rect
import androidx.compose.ui.unit.*
import androidx.core.graphics.minus
import kunimitsova.valbee.datetimecalculator.ui.LayoutSetup
import kotlin.math.abs

const val MIN_COLUMN_WIDTH = 320
const val MIN_ROW_HEIGHT = 320
const val MIN_SPACER = 16

fun twoColsCanFit(rect1: DpRect, rect2: DpRect) : Boolean {
    // widths to make sure two halves can fit on each side of the hinge
    val width1 = rect1.width.value.toInt()
    val width2 = rect2.width.value.toInt()
    // are two columns going to fit
    return (width1 >= MIN_COLUMN_WIDTH) && (width2 >= MIN_COLUMN_WIDTH)
}

fun twoRowsCanFit(rect1: DpRect, rect2: DpRect): Boolean {
    // heights to make sure two halves can fit on each side of the hinge
    val height1 = rect1.height.value.toInt()
    val height2 = rect2.height.value.toInt()

    return (height1 >= MIN_ROW_HEIGHT) && (height2 >= MIN_ROW_HEIGHT)
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

fun rectToDpRect(rect: Rect): DpRect {
    return DpRect(
        left = Dp(rect.left.toFloat()),
        top = Dp(rect.top.toFloat()),
        right = Dp(rect.right.toFloat()),
        bottom = Dp(rect.bottom.toFloat())
    )
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

fun middleSpacer(rect1: Rect, rect2: Rect?): Int {
    // get the spacer that matches the hinge area
    val rect2bang = if (rect2 == null) rect1 else rect2

    // if both rects are the same, then there's no hinge
    return if (rect1 == rect2bang) {
        16
    } else if (rect1.right == rect2bang.right)  {
        diffOrNull( rect2bang.top , rect1.bottom)
    } else {
        diffOrNull(rect1.right, rect2bang.left)
    }
}