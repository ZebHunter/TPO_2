package com.zebhunter.functions

import com.zebhunter.MathFunc
import com.zebhunter.trigonom.Sec

class TrigonomPart(private val secFunc: Sec) : MathFunc {

    override fun calculate(x: Double): Double {
        return secFunc.calculate(x) / secFunc.calculate(x)
    }

}