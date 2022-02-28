package jefemayoneso.compi1prac1.UI

import android.content.Context
import android.graphics.Color
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import jefemayoneso.compi1prac1.Backend.ParserActions.ReportManager
import jefemayoneso.compi1prac1.Utilities.BarGraphic
import java.lang.Exception
import kotlin.collections.ArrayList

class BarGraphDrawer {

    private lateinit var barList: ArrayList<BarEntry>
    private lateinit var barDataSet: BarDataSet
    private lateinit var barData: BarData

    /**
     * this method draw a graphic once it is valid
     */
    fun drawBar(bar: BarGraphic, context: Context, reportManager: ReportManager):BarChart {
        val barChart = BarChart(context)
        try {
            barList = ArrayList() // start list of tablesz
            // add bars
            var yVal: Float
            var xVal:String
            for ((iterator, merge) in bar.mergeItems.withIndex()) {
                try {
                    yVal = bar.getyAxisItems()[merge[1]].toFloat() // the size
                    xVal = bar.getxAxisItems()[merge[0]] // data to represent
                    val barEntry = BarEntry(iterator.toFloat(),yVal, xVal) // save data and increase iterator
                    barList.add(barEntry) // save data and increase iterator
                } catch (ex: Exception) {
                    reportManager.addError(-1,-1,"UNIR","No se puede unir {" + merge[0] + "," + merge[1] + "}",3)
                }
            }
            // add the info to data set
            barDataSet = BarDataSet(barList, bar.title)// merge bars
            // customization
            barDataSet.setColors(ColorTemplate.MATERIAL_COLORS,250)
            barDataSet.valueTextColor = Color.BLACK
            barDataSet.valueTextSize = 16f
            // save data
            barData = BarData(barDataSet) // save data to chart
            barChart.setFitBars(true)
            barChart.data = barData// apply data to chart
            barChart.description.isEnabled = true
            // customization
            barChart.description.text = bar.title
            barChart.animateY(1200)
            // axis
            // TODO use axisIterators for custom x axis
        } catch (ex: Exception) {
            println("ERROR AL CREAR GRAFICO: $ex")
        }
        return barChart
    }

}