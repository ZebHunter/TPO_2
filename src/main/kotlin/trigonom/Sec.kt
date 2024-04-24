package com.zebhunter.trigonom

import com.zebhunter.MathFunc

class Sec(private val cosFunc: Cos) : MathFunc{
    //Дописать проверку на критические точки
    override fun calculate(x: Double): Double {
        val cosValue = cosFunc.calculate(x)
        require(cosValue != 0.0) {"secant isn`t defined, when cos == 0"}
        return 1 / cosFunc.calculate(x)
    }
}