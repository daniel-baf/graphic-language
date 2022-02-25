package jefemayoneso.compi1prac1.Backend.ParserActions;

import java.util.ArrayList;

import jefemayoneso.compi1prac1.Utilities.SyntaxError;

public class ReportManager {

    private ArrayList<SyntaxError> errors;
    private ArrayList<String[]> mathOperators;
    private int barGraphicsCounter;
    private int pieGraphicsCounter;

    public ReportManager() {
        this.errors = new ArrayList<>();
        this.mathOperators = new ArrayList<>();
        this.barGraphicsCounter = 0;
        this.pieGraphicsCounter = 0;
    }

    public ArrayList<SyntaxError> getErrors() {
        return errors;
    }

    public ArrayList<String[]> getMathOperators() {
        return mathOperators;
    }

    public int getBarGraphicsCounter() {
        return barGraphicsCounter;
    }

    public int getPieGraphicsCounter() {
        return pieGraphicsCounter;
    }

    public void increasePieGraphicsCounter() {
        this.pieGraphicsCounter++;
    }

    public void increaseBarGraphicsCounter() {
        this.barGraphicsCounter++;
    }

    /**
     * This message add errors to a list of error
     *
     * @param line the line where error is produced
     * @param col the column where error is produced
     * @param lexeme the lexeme read when got error
     * @param message the message to display
     * @param errorType 1 = lexical error, 2 = syntax error
     */
    public void addError(int line, int col, String lexeme, String message, int errorType) {
        String errType = errorType == 0 ? "lexico" : "sintactico";
        this.errors.add(new SyntaxError(line, col, errType, message, lexeme));
    }

    public void addMathSymbolsReport(String previousTkn, String actualTkn, String nextTokn) {
        this.mathOperators.add(new String[]{previousTkn, actualTkn, nextTokn});
    }
}
