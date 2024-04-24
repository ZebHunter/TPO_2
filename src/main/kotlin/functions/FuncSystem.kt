package com.zebhunter.functions

import com.zebhunter.MathFunc

class FuncSystem(private val trigFunc: TrigonomPart,
                 private val logFunc: LogarithmPart)
    : MathFunc {

    override fun calculate(x: Double) : Double{
        return if (x <= 0) {
            trigFunc.calculate(x)
        } else{
            logFunc.calculate(x)
        }
    }
}