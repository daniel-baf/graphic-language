package jefemayoneso.compi1prac1

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

// graphics

class TypingSection : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // methods
        findViewById<Button>(R.id.btnStartAnalysis).setOnClickListener { sendData() }
        findViewById<Button>(R.id.btnTestText).setOnClickListener { setTestText() }
    }

    private fun sendData() {
        val intent = Intent(this, GraphSection::class.java) // holder for activity
        intent.putExtra("data", findViewById<EditText>(R.id.edTxtTextToAnalyze).text.toString())
        startActivity(intent)
    }

    private fun setTestText() {
        val test: String =
            "#Aqui estoy definiendo varios gráficos de Pie\n" +
                "Def Pie{\n" +
                "titulo: \"Grafica 2\";\n" +
                "tipo: Cantidad;\n" +
                "etiquetas: [\"Compi1\", \"Compi2\"];\n" +
                "valores:[5+123-(12+1)*1/23,10+23/3];\n" +
                "total: 145;\n" +
                "unir:[{0,1}, {1,0}];\n" +
                "extra: \"Resto\";\n" +
                "}\n" +
                "#Aqui estoy definiendo un gráfico de barras\n" +
                "def Barras{\n" +
                "\n\ttitulo: \"Grafica 1\";\n" +
                "\n\tejex:[\"item1\", \"item2\"];\n" +
                "\n\tejey:[5, 9];\n" +
                "\n\tunir:[{0,1}, {1,0}];\n" +
                "#Aqui termine\n" +
                "\n}\n" +
                "Def Pie{\n" +
                "titulo: \"Grafica 3\";\n" +
                "tipo: Porcentaje;\n" +
                "etiquetas: [\"Compi1\", \"Compi2\"];\n" +
                "valores:[70, 30];\n" +
                "unir:[{0,1}, {1,0}];\n" +
                "extra: \"Resto\";\n" +
                "}" +
                "Ejecutar(\"Grafica 1\");" +
                "Ejecutar(\"Grafica 2\");" +
                "Ejecutar(\"Grafica 3\");"
        findViewById<EditText>(R.id.edTxtTextToAnalyze).setText(test)
    }


}