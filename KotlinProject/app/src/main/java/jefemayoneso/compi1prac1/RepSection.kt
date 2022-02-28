package jefemayoneso.compi1prac1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import jefemayoneso.compi1prac1.Backend.ParserActions.ReportManager
import jefemayoneso.compi1prac1.UI.DinamicTable
import jefemayoneso.compi1prac1.Utilities.CommonError
import java.lang.Exception

class RepSection : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rep_section)

        val dataReceived: Bundle? = intent.extras
        val reportManager: ReportManager
        if(dataReceived != null) {
            reportManager = dataReceived.getSerializable("reportManager") as ReportManager
            setDeclaredGraphs(reportManager.barGraphicsCounter, reportManager.pieGraphicsCounter)
            addMathReport(reportManager.mathOperators)
            showErrorReport(reportManager.errors)
        }
    }

    private fun setDeclaredGraphs(barDeclared: Int, pieDeclared: Int) {
        findViewById<TextView>(R.id.txtBarGraphicResult).text = barDeclared.toString()
        findViewById<TextView>(R.id.txtPieGraphResult).text = pieDeclared.toString()
    }

//    private fun addMathReport(rows: ArrayList<CharArray>) {
//        try {
//            val llMathReport= findViewById<TableLayout>(R.id.mathResults)
//            val headers = arrayOf("PREVIO","ACTUAL","SIGUIENTE")
//            val dinamicTable = DinamicTable(llMathReport,applicationContext)
//            dinamicTable.addHeader(headers)
//            dinamicTable.addData(getArrayOfStrings(rows))
//        } catch (ex: Exception) {
//            println("ERRRO: $ex")
//            ex.printStackTrace()
//        }
//    }

//    private fun getArrayOfStrings(toCast: ArrayList<CharArray>): ArrayList<Array<String>> {
//        val items = ArrayList<Array<String>>()
//        for (item in toCast) {
//            var row = Array<String>(item.size){""}
//            for ((iterator, subItem) in item.withIndex()) {
//                row[iterator] = subItem + ""
//            }
//            items.add(row)
//        }
//        return items
//    }

    private fun addMathReport(rows: ArrayList<CharArray>) {
        try {
            val llMathLayout = findViewById<LinearLayout>(R.id.mathResults)
            for (row in rows) {
                val rowLt = LinearLayout(llMathLayout.context)
                for(item in row) {
                    val cell = TextView(rowLt.context)
                    cell.text = item.toString()
                    rowLt.addView(cell)
                }
                llMathLayout.addView(rowLt)
            }


        } catch (ex: Exception) {
            println("ERRRO: $ex")
            ex.printStackTrace()
        }
    }

    private fun showErrorReport(errors: ArrayList<CommonError>) {
        try {
            val tableLayout = findViewById<TableLayout>(R.id.errorLayout) as TableLayout
            val tableDinamic = DinamicTable(tableLayout,applicationContext)
            val headers = arrayOf("Linea","Columna","Tipo","Lexema","Mensaje")
            tableDinamic.addHeader(headers)
            tableDinamic.addData(getStringArray(errors))

        } catch (ex: Exception) {

        }
    }

    private fun getStringArray(errors: ArrayList<CommonError>): ArrayList<Array<String>> {
        val errorCasted = java.util.ArrayList<Array<String>>()
        for (error in errors) {
            errorCasted.add(arrayOf(error.line.toString(), error.col.toString(), error.errorType, error.lexeme, error.message))
        }
        return errorCasted
    }
}