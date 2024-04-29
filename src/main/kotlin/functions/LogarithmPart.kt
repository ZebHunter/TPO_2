package com.zebhunter.functions

import com.zebhunter.MathFunc
import com.zebhunter.logarithm.Ln
import com.zebhunter.logarithm.Log
import kotlin.math.pow

class LogarithmPart(private val log5: Log,
                    private val log10: Log,
                    private val ln: Ln)
    : MathFunc {

    override fun calculate(x: Double): Double {
       require(x != 0.0) {"Unresolved value for logarithm"}
       require(x != 1.0) {"Divide by zero"}
       return ((((ln.calculate(x) / ln.calculate(x)) / log10.calculate(x)) - log5.calculate(x)) * log5.calculate(x).pow(2)).pow(3)
    }
}