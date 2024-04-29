package functionSystemTests

import com.zebhunter.functions.FuncSystem
import com.zebhunter.functions.LogarithmPart
import com.zebhunter.functions.TrigonomPart
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.Mockito

class FunctionSystemTest {
    companion object {
        private const val eps = 0.001
        private val logPart = Mockito.mock(LogarithmPart::class.java)
        private val trigPart = Mockito.mock(TrigonomPart::class.java)
        private lateinit var res: FuncSystem

        @BeforeAll
        @JvmStatic
        fun setUp() {
            Mockito.`when`(logPart.calculate(0.5)).thenReturn(-0.153858662)
            Mockito.`when`(logPart.calculate(1.0)).thenThrow(java.lang.IllegalArgumentException::class.java)
            Mockito.`when`(logPart.calculate(2.0)).thenReturn(0.1542784457)
            Mockito.`when`(logPart.calculate(3.0)).thenReturn(0.2855972)
            Mockito.`when`(logPart.calculate(4.0)).thenReturn(0.208765583)
            Mockito.`when`(logPart.calculate(5.0)).thenReturn(0.079885068)
            Mockito.`when`(logPart.calculate(10.0)).thenReturn(-0.685266086)
            Mockito.`when`(logPart.calculate(Double.NaN)).thenReturn(Double.NaN)
            Mockito.`when`(logPart.calculate(Double.POSITIVE_INFINITY)).thenReturn(Double.NaN)

            Mockito.`when`(trigPart.calculate(0.0)).thenReturn(1.0)
            Mockito.`when`(trigPart.calculate(-Math.PI / 6)).thenReturn(1.0)
            Mockito.`when`(trigPart.calculate(-Math.PI / 4)).thenReturn(1.0)
            Mockito.`when`(trigPart.calculate(-Math.PI / 3)).thenReturn(1.0)
            Mockito.`when`(trigPart.calculate(-Math.PI / 2)).thenThrow(java.lang.IllegalArgumentException::class.java)
            Mockito.`when`(trigPart.calculate(-2 * Math.PI / 3)).thenReturn(1.0)
            Mockito.`when`(trigPart.calculate(-3 * Math.PI / 4)).thenReturn(1.0)
            Mockito.`when`(trigPart.calculate(-5 * Math.PI / 6)).thenReturn(1.0)
            Mockito.`when`(trigPart.calculate(Double.NaN)).thenReturn(Double.NaN)
            Mockito.`when`(trigPart.calculate(Double.POSITIVE_INFINITY)).thenReturn(Double.NaN)
            Mockito.`when`(trigPart.calculate(Double.NEGATIVE_INFINITY)).thenReturn(Double.NaN)

            res = FuncSystem(trigPart, logPart)
        }

        @JvmStatic
        fun provideTestData() =
            listOf(
                Arguments.of(0.5, -0.153858662),
                Arguments.of(2.0, 0.1542784457),
                Arguments.of(3.0, 0.2855972),
                Arguments.of(4.0, 0.208765583),
                Arguments.of(5.0, 0.079885068),
                Arguments.of(10.0, -0.685266086),

                Arguments.of(-Math.PI / 6, 1.0),
                Arguments.of(-Math.PI / 4, 1.0),
                Arguments.of(-Math.PI / 3, 1.0),
                Arguments.of(-2 * Math.PI / 3, 1.0),
                Arguments.of(-3 * Math.PI / 4, 1.0),
                Arguments.of(-5 * Math.PI / 6, 1.0),
                

                Arguments.of(Double.NaN, Double.NaN),
                Arguments.of(Double.POSITIVE_INFINITY, Double.NaN),
                Arguments.of(Double.NEGATIVE_INFINITY, Double.NaN),
            )

    }

    @ParameterizedTest
    @MethodSource("provideTestData")
    fun `full test`(x: Double, expected: Double) {
        assertEquals(expected, res.calculate(x), eps)
    }

    @Test
    fun throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException::class.java) {
            res.calculate(1.0)
        }

        assertThrows(IllegalArgumentException::class.java) {
            res.calculate(-Math.PI / 2)
        }


    }
}