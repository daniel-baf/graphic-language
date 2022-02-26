package jefemayoneso.compi1prac1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.github.mikephil.charting.charts.BarChart
import jefemayoneso.compi1prac1.Lexer.GraphLangLexer
import jefemayoneso.compi1prac1.Parser.GraphLangParser
import jefemayoneso.compi1prac1.UI.BarGraphDrawer
import jefemayoneso.compi1prac1.Utilities.BarGraphic
import java.io.StringReader

class GraphSection : AppCompatActivity() {

    private lateinit var barDrawer: BarGraphDrawer;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph_section)
        addListeners()
        val data =  intent.getStringExtra("data")
        barDrawer = BarGraphDrawer()
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
            drawGraphics(parser.actioner.barGraphics,parser.actioner.graphsToExec) // exec graphic
        } catch (ex: Exception) {
            // TODO SHOW ERROR MESSAGE
            println("ERROR ON CODE PRINT: $ex")
            ex.printStackTrace()
        }
    }

    private fun drawGraphics(bars: ArrayList<BarGraphic>, toDraw: ArrayList<String>) {
        // get liens
        val llGraphics = findViewById<LinearLayout>(R.id.infoLayout)
        val llTitle = TextView(this) // the title for container
        // create multiple divs for pie graphic
        val iterator = 0;
        for(title in toDraw) {// look into all graphics values
            for (bar in bars) {// look for bar instance
                // look if the actual graphic is valid
                if(bar.isValidGraph && bar.mergeDecl.equals(1) && bar.title.equals(title)) {
                    val barChart = this.barDrawer.drawBar(bar,this) // create the BarCHart
                    barChart.minimumHeight = 1000
                    llGraphics.addView(barChart) // add graphic
                } else {
                    Log.i("drawing","Graph no valid")
                }
            }
        }
        // give height
    }

    private fun returnTypingSection() {
        startActivity(Intent(this, TypingSection::class.java)) // holder for activity
    }

}