package kunimitsova.valbee.datetimecalculator

import kunimitsova.valbee.datetimecalculator.utils.*
import org.junit.Test

import org.junit.Assert.*
import java.time.LocalDateTime

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        val myDate = LocalDateTime.now()
        val myNum = 123L
        val myUnit = DateTimeUnits.DAY
        val expected = myDate.plusDays(123)
        val actual = calculatePlus(myDate, myNum, myUnit)
        assertEquals(expected, actual)
    }

    @Test
    fun subtraction_isCorrect() {
        val myDate = LocalDateTime.now()
        val myNum = 120L
        val myUnit = DateTimeUnits.DAY
        val expected = myDate.minusDays(120)
        val actual = calculateMinus(myDate, myNum, myUnit)
        val expectedString = expected.toString()
        val actualString = actual.toString()
        println(expectedString)
        assertEquals(expectedString, actualString)
    }

    @Test
    fun parse_date_test() {
        val expected = "2022-08-01T00:00"
        val actual = testParseThingy()
        assertEquals(expected, actual)
    }

    @Test
    fun validFloat_iscorrect() {
        val myStr = "123.123.123.123"
        val expStr = "123"
        assertEquals(expStr, validFloat(myStr))
    }

    @Test
    fun leadingzeros_onlyaddswhenmissing() {
        val myStr = "012"
        val expected = "12"
        val actual = leadingZero(myStr, 2)
        assertEquals(expected, actual)
    }
}