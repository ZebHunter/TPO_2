package trigonomTests

import com.zebhunter.trigonom.Cos
import com.zebhunter.trigonom.Sec
import com.zebhunter.trigonom.Sin
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.mockito.Mockito

class SecTest {
    companion object{
        private const val eps = 0.01
        private val cos = Mockito.mock(Cos::class.java)
        private lateinit var sec : Sec

        @BeforeAll
        @JvmStatic
        fun setUp(){
            Mockito.`when`(SecTest.cos.calculate(Math.PI / 2)).thenReturn(1.0)
            Mockito.`when`(SecTest.cos.calculate(Math.PI)).thenReturn(0.0)
            Mockito.`when`(SecTest.cos.calculate(3 * Math.PI / 2)).thenReturn(-1.0)
            Mockito.`when`(SecTest.cos.calculate(2 * Math.PI)).thenReturn(0.0)
            Mockito.`when`(SecTest.cos.calculate(5 * Math.PI / 2)).thenReturn(1.0)
            Mockito.`when`(SecTest.cos.calculate(0.0)).thenReturn(0.0)
            Mockito.`when`(SecTest.cos.calculate(-Math.PI / 2)).thenReturn(-1.0)
            Mockito.`when`(SecTest.cos.calculate(Double.NaN)).thenReturn(Double.NaN)
            Mockito.`when`(SecTest.cos.calculate(Double.POSITIVE_INFINITY)).thenReturn(Double.NaN)
            Mockito.`when`(SecTest.cos.calculate(Double.NEGATIVE_INFINITY)).thenReturn(Double.NaN)

            Mockito.`when`(SecTest.cos.calculate(Math.PI / 3 + Math.PI / 2)).thenReturn(0.5)
            Mockito.`when`(SecTest.cos.calculate(Math.PI / 4 + Math.PI / 2)).thenReturn(0.70711)
            Mockito.`when`(SecTest.cos.calculate(-Math.PI / 4 + Math.PI / 2)).thenReturn(0.70711)
            Mockito.`when`(SecTest.cos.calculate(-Math.PI / 3 + Math.PI / 2)).thenReturn(0.5)

            Mockito.`when`(SecTest.cos.calculate(8 * Math.PI + Math.PI / 2)).thenReturn(1.0)
            Mockito.`when`(SecTest.cos.calculate(-8 * Math.PI + Math.PI / 2)).thenReturn(1.0)

            SecTest.sec = Sec(SecTest.cos)
        }

        @JvmStatic
        fun provideTestData() =
            listOf(
                Arguments.of(0.0, 1),
                Arguments.of(Math.PI / 2, 0.0),
                Arguments.of(Math.PI, -1.0),
                Arguments.of(3 * Math.PI / 2, 0.0),
                Arguments.of(2 * Math.PI, 1.0),
                Arguments.of(-Math.PI / 2, 0.0),
                Arguments.of(-Math.PI, -1.0),
                Arguments.of(Double.NaN, Double.NaN),
                Arguments.of(Double.POSITIVE_INFINITY, Double.NaN),
                Arguments.of(Double.NEGATIVE_INFINITY, Double.NaN),

                Arguments.of(Math.PI / 3, 0.5),
                Arguments.of(Math.PI / 4, 0.70711),
                Arguments.of(-Math.PI / 4, 0.70711),
                Arguments.of(-Math.PI / 3, 0.5),

                Arguments.of(8 * Math.PI, 1.0),
                Arguments.of(-8 * Math.PI, 1.0),
            )

    }

    @ParameterizedTest
    @MethodSource("provideTestData")
    fun `sec test`(x: Double, excepted: Double){
        Assertions.assertEquals(excepted, SecTest.sec.calculate(x), SecTest.eps)
    }
}