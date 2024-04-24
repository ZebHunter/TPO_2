package com.zebhunter.logarithm

import com.zebhunter.MathFunc

class Log(private val lnFunc: Ln,
          private val ground: Double)
    : MathFunc {

    override fun calculate(x: Double): Double {
        return lnFunc.calculate(x) / lnFunc.calculate(ground)
    }

}