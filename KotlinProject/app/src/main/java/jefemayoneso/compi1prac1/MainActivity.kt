package jefemayoneso.compi1prac1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import java.io.StringReader
import jefemayoneso.compi1prac1.Lexer.GraphLangLexer
import jefemayoneso.compi1prac1.Parser.GraphLangParser
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // methods
        findViewById<Button>(R.id.btnStartAnalysis).setOnClickListener{startAnalysis()}
        findViewById<Button>(R.id.btnTestText).setOnClickListener { setTestText() }
    }

    // event listeners
    private fun startAnalysis() {
        // variables for analysis
        val reader = StringReader(findViewById<EditText>(R.id.edTxtTextToAnalyze).text.toString())
        val lexer = GraphLangLexer(reader)
        val parser = GraphLangParser(lexer)
        // execute
        try {
            parser.parse()
            // show reports
            // math symbols
            for (item in parser.actioner.reportManager.mathOperators) {
                Log.i("Item: ", item[0] + "," + item[1] + "," + item[2])
            }
            // numn of declarations
            Log.i("Declaraciones de pie: ", parser.actioner.reportManager.pieGraphicsCounter.toString())
            Log.i("Declaraciones de barra: ", parser.actioner.reportManager.barGraphicsCounter.toString())
            // errors
            Log.i("ERRORES","")
            for(item in parser.actioner.reportManager.errors) {
                Log.i("ERROR: ", "\n\tL:" + item.line + "\n\tC:" + item.col + "\n\tTipo:" + item.errorType + "\n\tmessage:" + item.message)
            }
        } catch (ex: Exception) {
            println(ex)
        }
    }
    private fun setTestText() {
        val test: String =
            "#Aqui estoy definiendo varios gráficos de Pie\n" +
            "Def Pie{\n" +
            "titulo: \"Grafica 2\";\n" +
            "tipo: Cantidad;\n" +
            "etiquetas: [\"Compi1\", \"Compi2\"];\n" +
            "valores:[5+123-(12+1)*1/23, 10-23];\n" +
            "total: 25;\n" +
            "unir:[{0,1}, {1,0}];\n" +
            "extra: \"Resto\";\n" +
            "}"

        findViewById<EditText>(R.id.edTxtTextToAnalyze).setText(test)
    }
}