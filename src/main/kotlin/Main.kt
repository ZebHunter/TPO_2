package com.zebhunter

import com.zebhunter.functions.FuncSystem
import com.zebhunter.functions.LogarithmPart
import com.zebhunter.functions.TrigonomPart
import com.zebhunter.logarithm.Ln
import com.zebhunter.logarithm.Log
import com.zebhunter.trigonom.Cos
import com.zebhunter.trigonom.Sec
import com.zebhunter.trigonom.Sin
import java.io.BufferedReader
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException
import org.jfree.chart.ChartFactory
import org.jfree.chart.ChartPanel
import org.jfree.chart.JFreeChart
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer
import org.jfree.data.xy.XYSeries
import org.jfree.data.xy.XYSeriesCollection
import java.awt.Color
import javax.swing.JFrame

fun drawGraph(csvFilePath: String) {
    val dataset = XYSeriesCollection()
    val series = XYSeries("Series 1")

    val reader = BufferedReader(FileReader(csvFilePath))

    var isFirstLine = true
    reader.useLines { lines ->
        lines.forEach { line ->
            if (isFirstLine) {
                isFirstLine = false
                return@forEach
            }
            val parts = line.split(",")
            if (parts.size >= 2) {
                val xValue = parts[0].trim().toDouble()
                val yValue = parts[1].trim().toDouble()
                series.add(xValue, yValue)
            }
        }
    }

    dataset.addSeries(series)

    val chart: JFreeChart = ChartFactory.createXYLineChart(
        "График из CSV",
        "X",
        "Y",
        dataset
    )

    // Настройка видимости линий и форм
    val renderer = XYLineAndShapeRenderer(true, true) // Линии и формы видимы
    renderer.setSeriesPaint(0, Color.RED) // Цвет линии красный
    renderer.setSeriesShape(0, java.awt.geom.Ellipse2D.Double(-3.0, -3.0, 6.0, 6.0)) // Круглые точки
    renderer.setSeriesShapesVisible(0, true) // Точки видимы
    renderer.setSeriesLinesVisible(0, true) // Линии видимы
    chart.xyPlot.renderer = renderer

    val frame = JFrame("График")
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.add(ChartPanel(chart))
    frame.pack()
    frame.setLocationRelativeTo(null)
    frame.isVisible = true
}
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

    drawGraph("src/main/resources/system.csv")
}