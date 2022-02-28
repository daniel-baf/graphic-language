package jefemayoneso.compi1prac1.UI

import android.content.Context
import android.graphics.Color
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate
import jefemayoneso.compi1prac1.Utilities.PieGraphic
import java.lang.Exception

class PieGraphDrawer {

    lateinit var pieDataSet: PieDataSet
    lateinit var pieData: PieData
    lateinit var pieList: ArrayList<PieEntry>

    fun drawPie(context: Context, pie: PieGraphic): PieChart {
        // validate the type
        switchDataGraph(pie)
        // variables
        val pieChart = PieChart(context)
        pieList = ArrayList()
        try {
            // add data
            var value: Float
            for (entry in pie.mergeItems) {
                value = pie.values[entry[0]].toFloat() // get the value
                pieList.add(PieEntry(value,pie.tags[entry[1]]))
            }
            pieDataSet = PieDataSet(pieList,pie.title) // add the data to model
            // customization
            pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS,150)
            pieDataSet.valueTextColor = Color.BLACK
            pieDataSet.valueTextSize = 16f
            // add data to chart
            pieData = PieData(pieDataSet)
            pieChart.data = pieData
            pieChart.description.isEnabled = true
            // customization
            pieChart.centerText = pie.title
            pieChart.description.text = "Res en: " + pie.barGraphicType + " Extra: " + pie.extra
            pieChart.animate()
            if(pie.barGraphicType.equals("Porcentaje")) {
                pieChart.setUsePercentValues(true)
            }
        } catch (ex: Exception) {
            println("Error: $ex")
        }
        return  pieChart
    }

    private fun switchDataGraph(pie: PieGraphic) {
        if(pie.barGraphicType.equals("Porcentaje")) {
            pie.total = 360.0
        }
        // switch for Porcentage
        if(pie.barGraphicType.equals("Porcentaje")) {
            for((iterator, parcTotal) in pie.values.withIndex()) {
                pie.values[iterator] = pie.total * parcTotal / 360
            }
        }
    }

}