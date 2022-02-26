package jefemayoneso.compi1prac1.UI

import android.content.Context
import android.graphics.Color
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.utils.ColorTemplate
import jefemayoneso.compi1prac1.Utilities.BarGraphic
import java.util.*
import kotlin.collections.ArrayList

public class BarGraphDrawer {

    private lateinit var barList: ArrayList<BarEntry>
    private lateinit var barDataSet: BarDataSet
    private lateinit var barData: BarData

    /**
     * this method draw a graphic once it is valid
     */
    public fun drawBar(bar: BarGraphic, context: Context):BarChart {

        // x indicators

        val barChart: BarChart = BarChart(context)
        try {
            barList = ArrayList() // start list of tablesz
            // add bars
            var yVal: Float
            var xVal:String
            for ((iterator, merge) in bar.mergeItems.withIndex()) {
                yVal = bar.getyAxisItems()[merge[1].toInt()].toFloat() // the size
                xVal = bar.getxAxisItems()[merge[0].toInt()] // data to represent
                val barEntry = BarEntry(iterator.toFloat(),yVal, xVal) // save data and increase iterator
                barEntry.describeContents()
                barList.add(barEntry) // save data and increase iterator
            }
            // add the info to data set
            barDataSet = BarDataSet(barList, bar.title)// merge bars
            // customization
            barDataSet.setColors(ColorTemplate.MATERIAL_COLORS,250)
            barDataSet.valueTextColor = Color.BLACK
            barDataSet.valueTextSize = 16f
            barData = BarData(barDataSet) // save data to chart
            // save data
            barChart.setFitBars(true)
            barChart.data = barData// apply data to chart
            // customization
            barChart.minimumHeight = 1000
            barChart.description.text = bar.title
            barChart.animateY(1200)
            barChart.setDrawValueAboveBar(true)
            barChart.setDrawGridBackground(true)
            // axis
        } catch (ex: java.lang.Exception) {
            println("ERROR AL CREAR GRAFICO: $ex")
        }
        return barChart;
    }
}