/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jefemayoneso.compi1prac1.Backend.ParserActions;

import jefemayoneso.compi1prac1.Utilities.*;
import java.util.ArrayList;

/**
 *
 * @author jefemayoneso
 */
public class ParserActioner {

    private final ArrayList<BarGraphic> barGraphics;
    private final ArrayList<PieGraphic> pieGraphics;
    private final ArrayList<String> graphsToExec;
    private final BarGraphicActioner bga;
    private final PieGraphicActioner pga;
    private BarGraphic barGraph;
    private PieGraphic pieGraphic;
    private final ReportManager reportManager;

    public ParserActioner() {
        this.barGraphics = new ArrayList<>();
        this.pieGraphics = new ArrayList<>();
        this.barGraph = new BarGraphic();
        this.pieGraphic = new PieGraphic();
        this.bga = new BarGraphicActioner();
        this.pga = new PieGraphicActioner();
        this.reportManager = new ReportManager();
        this.graphsToExec = new ArrayList<>();
    }

    public void saveBarGraphData() {
        this.reportManager.increaseBarGraphicsCounter(); // increase counter
        if(this.barGraph.isValidGraph(this.reportManager)) {// save graphic data if it is valid
            this.barGraphics.add(barGraph);
            System.out.println("Bar graph saved");
        }
        this.barGraph = new BarGraphic();// reset object
    }

    public void savePieGraphData() {
        this.reportManager.increasePieGraphicsCounter(); // increase counter for report
        if(this.pieGraphic.isValidGraph(this.reportManager)) {
            this.pieGraphics.add(pieGraphic); // save graphic data when valid and have merge declaration
            System.out.println("Pie graph saved " + this.pieGraphic.getBarGraphicType());
        }
        this.pieGraphic = new PieGraphic(); // reset object
    }

    /**
     * Regist a title declaration and save the info of title in case never
     * declared before. This method receive info from token VALUE_ON-COMILLAS so
     * replace al "\"" for ""
     *
     * @param line the line of token
     * @param col the col of token
     * @param title the title of token
     */
    public void registTitleDeclaration(int line, int col, String title, int graphType) {
        // add message to errors and set error to object
        boolean done = graphType == 0 ? bga.titleDeclaration(title, this.barGraph) : this.pga.titleDeclaration(title, this.pieGraphic);
        // show message
        if (!done) {
            this.reportManager.addError(line, col, "Declaracion de titulo", "Hay más de 1 declaracion de titulo", 1);
            regsitErrorOnGraph(graphType);
        }
    }

    /**
     * This method regist the values for x axis declarations in a pie graphic
     *
     * @param line the line of token
     * @param col the col of token
     * @param items the items to save
     */
    public void registXAxisDeclaration(int line, int col, String items) {
        // split item with \n for files
        if (!this.bga.xAxisDeclaration(items, this.barGraph)) {
            this.reportManager.addError(line, col, "Declaracion de eje x", "Hay más de 1 declaracion de ejes x", 1);
            regsitErrorOnGraph(0);
        }
    }

    /**
     * This method add the Doubles of the values for graphic, and validate if
     * there is more than 1 declaration, if yes, add an error
     *
     * @param line the line of token
     * @param col the col of token
     * @param items the items to save
     */
    public void registYAxisDeclaration(int line, int col, String items) {
        if (!this.bga.yAxisDeclaration(items, this.barGraph)) {
            this.reportManager.addError(line, col, "Declaraciones de eje y", "Hay mas de 1 declaracion de ejes y", 1);
            regsitErrorOnGraph(0);
        }
    }

    /**
     * This method add a regist for unir[{a,b}...] delcarations, if there is an
     * existing declaration, add an error
     *
     * @param line the line of token
     * @param col the col of token
     * @param mergeCoordenates the coordinates to save
     */
    public void registMergeDeclaration(int line, int col, ArrayList<int[]> mergeCoordenates, int graphType) {
        boolean done = graphType == 0 ? this.bga.mergeDeclaration(mergeCoordenates, this.barGraph) : this.pga.mergeDeclaration(mergeCoordenates, this.pieGraphic);
        if (!done) {
            this.reportManager.addError(line, col, "Declaraciones de union de items", "Hay mas de 1 declaraciones unir", 1);
            regsitErrorOnGraph(graphType);
        }
    }

    /**
     * This method ad a regist for instrucctions like Tipo: Cantidad
     *
     * @param line the line of token
     * @param col the col of token
     * @param type the type of the graphic
     */
    public void registTypeDeclaration(int line, int col, int type) {
        if (!this.pga.typeDeclaration(type, this.pieGraphic)) {
            this.reportManager.addError(line, col, "Declaracion de tipo de grafica de barra", "Hay mas de 1 declaraciones unir", 1);
            regsitErrorOnGraph(1);
        }
    }

    /**
     * This method adds a regist for instructions like Valores: [number, number]
     *
     * @param line the line of token
     * @param col the col of token
     * @param items the items to save
     */
    public void registTagsDeclaration(int line, int col, String items) {
        if (!this.pga.tagsDeclaration(items, this.pieGraphic)) {
            this.reportManager.addError(line, col, "Declaracion de etiquetas", "Hay mas de 1 declaracion para tags", 1);
            regsitErrorOnGraph(1);
        }
    }

    public void regisExtraDeclaration(int line, int col, String extra) {
        if (!this.pga.extraDeclaration(extra, this.pieGraphic)) {
            this.reportManager.addError(line, col, "Declaracion de extras", "Hay mas de 1 declaracion de extras", 1);
            regsitErrorOnGraph(1);
        }
    }

    /**
     * This method add regists for instructions like valores; [number,
     * number...]
     *
     * @param line the line of token
     * @param col the col of token
     * @param numbers the numbers to save
     */
    public void registValuesDeclaration(int line, int col, String numbers) {
        if (!this.pga.valuesDeclaration(numbers, this.pieGraphic)) {
            this.reportManager.addError(line, col, "Declaracion de valores", "Hay mas de 1 declaracion para valores", 1);
            regsitErrorOnGraph(1);
        }
    }

    /**
     * Regist a total declaration on the graphic
     * @param line the line of the token
     * @param col the col of the toekn
     * @param number the number for the total declaration
     */
    public void registTotalDeclaration(int line, int col, Double number) {
        if (!this.pga.totalDeclaration(number, this.pieGraphic)) {
            this.reportManager.addError(line, col, "Declaracion de total", "Hay mas de 1 declaracion para totales", 1);
            regsitErrorOnGraph(1);
        }
    }

    /**
     * Set a new error on the object counter
     *
     * @param type 0 = bar graphic, else = pie grahpic
     */
    public void regsitErrorOnGraph(int type) {
        if (type == 0) {
            this.barGraph.setErrorCounter(this.barGraph.getErrorCounter() + 1);
        } else {
            this.pieGraphic.setErrorCounter(this.pieGraphic.getErrorCounter() + 1);
        }
    }

    // getters and setters
    public ArrayList<PieGraphic> getPieGraphics() {
        return pieGraphics;
    }
    public ArrayList<BarGraphic> getBarGraphics() {return this.barGraphics; }

    public ReportManager getReportManager() {
        return this.reportManager;
    }

    public void addGraphToExec(String graphTitle) {
        this.graphsToExec.add(graphTitle.substring(1,graphTitle.length()-1));
    }

    public ArrayList<String> getGraphsToExec() {
        return this.graphsToExec;
    }

}
