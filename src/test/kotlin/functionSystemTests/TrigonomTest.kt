package functionSystemTests

import com.zebhunter.functions.TrigonomPart
import com.zebhunter.trigonom.Sec
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.Mockito

class TrigonomTest {
    companion object {
        private const val EPS = 0.01
        private val sec = Mockito.mock(Sec::class.java)


        private lateinit var trig: TrigonomPart

        @BeforeAll
        @JvmStatic
        fun setUp() {
            Mockito.`when`(sec.calculate(0.0)).thenReturn(1.0)
            Mockito.`when`(sec.calculate(-Math.PI / 6)).thenReturn(1.154700538)
            Mockito.`when`(sec.calculate(-Math.PI / 4)).thenReturn(1.414213562)
            Mockito.`when`(sec.calculate(-Math.PI / 3)).thenReturn(2.0)
            Mockito.`when`(sec.calculate(-Math.PI / 2)).thenThrow(IllegalArgumentException::class.java)
            Mockito.`when`(sec.calculate(-2 * Math.PI / 3)).thenReturn(-2.0)
            Mockito.`when`(sec.calculate(-3 * Math.PI / 4)).thenReturn(-1.414213562)
            Mockito.`when`(sec.calculate(-5 * Math.PI / 6)).thenReturn(-1.154700538)
            Mockito.`when`(sec.calculate(-Math.PI)).thenReturn(-1.0)
            Mockito.`when`(sec.calculate(-7 * Math.PI / 6)).thenReturn(-1.154700538)
            Mockito.`when`(sec.calculate(-5 * Math.PI / 4)).thenReturn(-1.414213562)
            Mockito.`when`(sec.calculate(-4 * Math.PI / 3)).thenReturn(-2.0)
            Mockito.`when`(sec.calculate(-3 * Math.PI / 2)).thenThrow(IllegalArgumentException::class.java)
            Mockito.`when`(sec.calculate(-5 * Math.PI / 3)).thenReturn(2.0)
            Mockito.`when`(sec.calculate(-7 * Math.PI / 4)).thenReturn(1.414213562)
            Mockito.`when`(sec.calculate(-11 * Math.PI / 6)).thenReturn(1.154700538)
            Mockito.`when`(sec.calculate(Double.NaN)).thenReturn(Double.NaN)
            Mockito.`when`(sec.calculate(Double.POSITIVE_INFINITY)).thenReturn(Double.NaN)
            Mockito.`when`(sec.calculate(Double.NEGATIVE_INFINITY)).thenReturn(Double.NaN)


            trig = TrigonomPart(sec)
        }

        @JvmStatic
        fun provideTestData() =
            listOf(
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
    fun `trig part test`(x: Double, excepted: Double) {
        Assertions.assertEquals(excepted, trig.calculate(x), EPS)
    }

    @Test
    fun throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException::class.java) {
            trig.calculate(-Math.PI / 2)
        }

        assertThrows(IllegalArgumentException::class.java) {
            trig.calculate(-3 * Math.PI / 2)
        }
    }
}