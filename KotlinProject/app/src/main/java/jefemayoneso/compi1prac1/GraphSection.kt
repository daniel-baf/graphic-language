package jefemayoneso.compi1prac1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.LinearLayout
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
        addListeners()
        val data =  intent.getStringExtra("data")
        barDrawer = BarGraphDrawer()
        pieDrawer = PieGraphDrawer()
        // analyze
        startAnalysis(data.toString())
        // validate graphics
    }

    private fun addListeners() {
        findViewById<Button>(R.id.btnReturnToCode).setOnClickListener { returnTypingSection() }
    }

    // analysis
    // event listeners
    private fun startAnalysis(data: String) {
        // variables for analysis
        val lexer = GraphLangLexer(StringReader(data))
        val parser = GraphLangParser(lexer)
        // execute
        try {
            parser.parse() // start syntax analysis
            drawGraphics(parser.actioner.barGraphics,parser.actioner.graphsToExec, parser.actioner.pieGraphics) // exec graphic
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
        }
    }

    private fun returnTypingSection() {
        startActivity(Intent(this, TypingSection::class.java)) // holder for activity
    }

}