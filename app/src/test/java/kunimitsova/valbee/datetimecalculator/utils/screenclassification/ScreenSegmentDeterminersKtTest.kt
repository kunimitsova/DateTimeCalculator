package kunimitsova.valbee.datetimecalculator.utils.screenclassification

import android.graphics.Rect
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.internal.invocation.MockitoMethod
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ScreenSegmentDeterminersUnitTest {
    @Test
    fun rectTest() {
        val mockRect = mock(Rect::class.java)
        mockRect.left = 0
        mockRect.top = 0
        mockRect.right = 400
        mockRect.bottom = 800
        val mockRect2 = mock(Rect::class.java)
        mockRect2.top = 0
        mockRect2.left = 420
        mockRect2.right = 800
        mockRect2.bottom = 800
//        val rect1 = Rect(0,0,400,800)
  //      val rect2 = Rect(420, 0,800,800)

        val expected = 20
        val actual = middleSpacer(mockRect, mockRect2)

        assertEquals(expected, actual)
    }
}