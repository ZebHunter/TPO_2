package functionSystemTests

import com.zebhunter.functions.LogarithmPart
import com.zebhunter.logarithm.Ln
import com.zebhunter.logarithm.Log
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.Mockito

class LogaritmTest {
    companion object{
        private const val eps = 0.01
        private val ln = Mockito.mock(Ln::class.java)
        private val log5 = Mockito.mock(Log::class.java)
        private val log10 = Mockito.mock(Log::class.java)
        private lateinit var log: LogarithmPart
        @BeforeAll
        @JvmStatic
        fun setUp(){
            Mockito.`when`(ln.calculate(0.5)).thenReturn(-0.693147)
            Mockito.`when`(ln.calculate(1.0)).thenReturn(0.0)
            Mockito.`when`(ln.calculate(2.0)).thenReturn(0.693147)
            Mockito.`when`(ln.calculate(3.0)).thenReturn(1.098612)
            Mockito.`when`(ln.calculate(4.0)).thenReturn(1.386294361)
            Mockito.`when`(ln.calculate(5.0)).thenReturn(1.609)
            Mockito.`when`(ln.calculate(10.0)).thenReturn(2.302585)
            Mockito.`when`(ln.calculate(Double.NaN)).thenReturn(Double.NaN)
            Mockito.`when`(ln.calculate(Double.POSITIVE_INFINITY)).thenReturn(Double.POSITIVE_INFINITY)
            Mockito.`when`(ln.calculate(-1.0)).thenThrow(IllegalArgumentException::class.java)

            Mockito.`when`(log5.calculate(0.5)).thenReturn(-0.43098)
            Mockito.`when`(log5.calculate(1.0)).thenReturn(0.0)
            Mockito.`when`(log5.calculate(2.0)).thenReturn(0.43098)
            Mockito.`when`(log5.calculate(3.0)).thenReturn(0.68281)
            Mockito.`when`(log5.calculate(4.0)).thenReturn(0.861353116)
            Mockito.`when`(log5.calculate(5.0)).thenReturn(1.0)
            Mockito.`when`(log5.calculate(10.0)).thenReturn(1.430676558)
            Mockito.`when`(log5.calculate(Double.NaN)).thenReturn(Double.NaN)
            Mockito.`when`(log5.calculate(Double.POSITIVE_INFINITY)).thenReturn(Double.POSITIVE_INFINITY)
            Mockito.`when`(log5.calculate(-1.0)).thenThrow(IllegalArgumentException::class.java)

            Mockito.`when`(log10.calculate(0.5)).thenReturn(-0.301029)
            Mockito.`when`(log10.calculate(1.0)).thenReturn(0.0)
            Mockito.`when`(log10.calculate(2.0)).thenReturn(0.301029)
            Mockito.`when`(log10.calculate(3.0)).thenReturn(0.4771212)
            Mockito.`when`(log10.calculate(4.0)).thenReturn(0.602059991)
            Mockito.`when`(log10.calculate(5.0)).thenReturn(0.698970004)
            Mockito.`when`(log10.calculate(10.0)).thenReturn(1.0)
            Mockito.`when`(log10.calculate(Double.NaN)).thenReturn(Double.NaN)
            Mockito.`when`(log10.calculate(Double.POSITIVE_INFINITY)).thenReturn(Double.POSITIVE_INFINITY)
            Mockito.`when`(log10.calculate(-1.0)).thenThrow(IllegalArgumentException::class.java)

            log = LogarithmPart(log5, log10, ln)
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
                Arguments.of(Double.NaN, Double.NaN),
                Arguments.of(Double.POSITIVE_INFINITY, Double.NaN),
            )

    }

    @ParameterizedTest
    @MethodSource("provideTestData")
    fun `log test`(x: Double, expected: Double){
        assertEquals(expected, log.calculate(x), eps)
    }

    @Test
    fun throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException::class.java) {
            log.calculate(1.0)
        }
    }
}