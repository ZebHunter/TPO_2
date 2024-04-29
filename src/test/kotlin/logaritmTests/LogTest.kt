package logaritmTests

import com.zebhunter.logarithm.Ln
import com.zebhunter.logarithm.Log
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.Mockito

class LogTest {
    companion object{
        private const val eps = 0.001
        private val ln = Mockito.mock(Ln::class.java)
        private lateinit var log: Log
        @BeforeAll
        @JvmStatic
        fun setUp(){
            Mockito.`when`(ln.calculate(0.5)).thenReturn(-0.693147)
            Mockito.`when`(ln.calculate(1.0)).thenReturn(0.0)
            Mockito.`when`(ln.calculate(2.0)).thenReturn(0.693147)
            Mockito.`when`(ln.calculate(3.0)).thenReturn(1.098612)
            Mockito.`when`(ln.calculate(100.0)).thenReturn(4.60517)
            Mockito.`when`(ln.calculate(5.0)).thenReturn(1.609)
            Mockito.`when`(ln.calculate(10.0)).thenReturn(2.302585)
            Mockito.`when`(ln.calculate(Double.NaN)).thenReturn(Double.NaN)
            Mockito.`when`(ln.calculate(Double.POSITIVE_INFINITY)).thenReturn(Double.POSITIVE_INFINITY)
            Mockito.`when`(ln.calculate(-1.0)).thenThrow(IllegalArgumentException::class.java)

        }

        @JvmStatic
        fun provideTestData() =
            listOf(
                Arguments.of(5.0, 0.5, -0.43098),
                Arguments.of(5.0, 1.0, 0.0),
                Arguments.of(5.0, 2.0, 0.43098),
                Arguments.of(5.0, 3.0, 0.68281),
                Arguments.of(5.0, 100.0, 2.8621),
                Arguments.of(10.0, 0.5, -0.301029),
                Arguments.of(10.0, 1.0, 0.0),
                Arguments.of(10.0, 2.0, 0.301029),
                Arguments.of(10.0, 3.0, 0.4771212),
                Arguments.of(10.0, 100.0, 2.0),
                Arguments.of(5.0, Double.NaN, Double.NaN),
                Arguments.of(5.0, Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY),
            )

    }
    
    @ParameterizedTest
    @MethodSource("provideTestData")
    fun `log test`(base: Double, x: Double, expected: Double){
        log = Log(ln, base)
        assertEquals(expected, log.calculate(x), eps)
    }


}