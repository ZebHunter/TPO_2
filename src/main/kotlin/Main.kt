package com.zebhunter

import com.zebhunter.functions.FuncSystem
import com.zebhunter.functions.LogarithmPart
import com.zebhunter.functions.TrigonomPart
import com.zebhunter.logarithm.Ln
import com.zebhunter.logarithm.Log
import com.zebhunter.trigonom.Cos
import com.zebhunter.trigonom.Sec
import com.zebhunter.trigonom.Sin
import java.io.FileWriter
import java.io.IOException

fun writeToCsv(
    computeFunction: MathFunc,
    start: Double,
    end: Double,
    step: Double,
    path: String,
    funName: String,
) {
    require(start < end) { "Start must be less than End" }
    var x = start
    val data = mutableListOf<Pair<Double, Double>>()
    while (x <= end) {
        val y = computeFunction.calculate(x)
        data.add(x to y)
        x += step
    }

    try {
        FileWriter(path).use { writer ->
            writer.append("X,$funName\n")

            data.forEach { (value1, value2) ->
                writer.append("$value1,$value2\n")
            }

            println("Data writes to: $path")
        }
    } catch (e: IOException) {
        println("Write error: ${e.message}")
    }
}

fun main() {
    val eps = 0.001
    val sin = Sin(eps)
    val cos = Cos(sin)
    val sec = Sec(cos)
    val trigFunc = TrigonomPart(sec)
    val ln = Ln(eps)
    val log5 = Log(ln, 5.0)
    val log10 = Log(ln, 10.0)
    val logFunc = LogarithmPart(log5, log10, ln)
    val system = FuncSystem(trigFunc, logFunc)

    writeToCsv(ln, 0.0001, 101.0, 0.1, "src/main/resources/ln.csv", "ln")
    writeToCsv(sin, -8.0, 1.0, 0.1, "src/main/resources/sin.csv", "sin")
    writeToCsv(cos, -8.0, 1.0, 0.1, "src/main/resources/cos.csv", "cos")
    writeToCsv(sec, -8.0, 1.0, 0.1, "src/main/resources/sec.csv", "sec")
    writeToCsv(log5, 0.0001, 101.0, 0.1, "src/main/resources/log5.csv", "log5")
    writeToCsv(log10, 0.0001, 101.0, 0.1, "src/main/resources/log10.csv", "log10")
    writeToCsv(logFunc, 0.0001, 101.0, 0.1, "src/main/resources/logFunc.csv", "logFunc")
    writeToCsv(trigFunc, -8.0, 1.0, 0.1, "src/main/resources/trigFunc.csv", "trigFun")
    writeToCsv(system, -8.0, 101.0, 0.1, "src/main/resources/system.csv", "system")
}