package jefemayoneso.compi1prac1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
import jefemayoneso.compi1prac1.Backend.ParserActions.ReportManager
import jefemayoneso.compi1prac1.Lexer.GraphLangLexer
import jefemayoneso.compi1prac1.Parser.GraphLangParser
import jefemayoneso.compi1prac1.UI.BarGraphDrawer
import jefemayoneso.compi1prac1.UI.PieGraphDrawer
import jefemayoneso.compi1prac1.Utilities.BarGraphic
import jefemayoneso.compi1prac1.Utilities.PieGraphic
import java.io.StringReader

class GraphSection : AppCompatActivity() {

    private lateinit var barDrawer: BarGraphDrawer
    private lateinit var pieDrawer: PieGraphDrawer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph_section)
        val data =  intent.getStringExtra("data")
        barDrawer = BarGraphDrawer()
        pieDrawer = PieGraphDrawer()
        val lexer = GraphLangLexer(StringReader(data))
        val parser = GraphLangParser(lexer)
        addListeners(parser.actioner.reportManager)
        // analyze
        startAnalysis(parser,lexer)
        // validate graphics
    }

    private fun addListeners(reportManager: ReportManager) {
        findViewById<Button>(R.id.btnReturnToCode).setOnClickListener { returnTypingSection() }
        findViewById<Button>(R.id.btnViewReports).setOnClickListener { goReportSection(reportManager) }
    }

    // analysis
    // event listeners
    private fun startAnalysis(parser: GraphLangParser, lexer: GraphLangLexer) {
        // execute
        try {
            parser.parse() // start syntax analysis
            // save data of numbers in lexer
            drawGraphics(parser.actioner.barGraphics,parser.actioner.graphsToExec, parser.actioner.pieGraphics) // exec graphic
            parser.actioner.reportManager.mathOperators = lexer.mathSymTknsPos
            parser.actioner.reportManager.addError(lexer.errors)
        } catch (ex: Exception) {
            // TODO SHOW ERROR MESSAGE
            println("ERROR ON CODE PRINT: $ex")
            ex.printStackTrace()
        }
    }

    private fun drawGraphics(bars: ArrayList<BarGraphic>, toDraw: ArrayList<String>, pies: ArrayList<PieGraphic>) {
        // get liens
        val llGraphics = findViewById<LinearLayout>(R.id.infoLayout)
        // create multiple divs for pie graphic
        for(title in toDraw) {// look into all graphics values
            try {
                for (bar in bars) {// look for bar instance
                    // at this point, we assume each possible error is already declared, so report manager = null
                    if(bar.isValidGraph(null) && bar.title.equals(title)) {
                        val barChart = this.barDrawer.drawBar(bar,this) // create the BarCHart
                        barChart.minimumHeight = 1000
                        llGraphics.addView(barChart) // add graphic
                    }
                }
                for(pie in pies) {
                    if(pie.isValidGraph(null) && pie.title.equals(title)) {
                        val pieChart = this.pieDrawer.drawPie(this,pie)
                        pieChart.minimumHeight = 1000
                        llGraphics.addView(pieChart)
                    }
                }
            } catch (ex: java.lang.Exception) {
                println("ERROR: cannot draw graphic $ex")
            }
        }
    }

    private fun returnTypingSection() {
        startActivity(Intent(this, TypingSection::class.java)) // holder for activity
    }

    private fun goReportSection(reportManager: ReportManager) {
        val intent = Intent(this, RepSection::class.java)
        val bundle = Bundle()
        bundle.putSerializable("reportManager",reportManager)
        intent.putExtras(bundle)
        startActivity(intent)
    }

}