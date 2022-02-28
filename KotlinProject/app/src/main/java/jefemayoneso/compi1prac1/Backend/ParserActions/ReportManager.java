package jefemayoneso.compi1prac1.Backend.ParserActions;

import java.io.Serializable;
import java.util.ArrayList;

import jefemayoneso.compi1prac1.Utilities.CommonError;

public class ReportManager implements Serializable {

    private final ArrayList<CommonError> errors;
    private ArrayList<char[]> mathOperators;
    private int barGraphicsCounter;
    private int pieGraphicsCounter;


    public ReportManager() {
        this.errors = new ArrayList<>();
        this.mathOperators = new ArrayList<>();
        this.barGraphicsCounter = 0;
        this.pieGraphicsCounter = 0;
    }

    public ReportManager(ArrayList<CommonError> errors, ArrayList<char[]> mathOperators, int barGraphicsCounter, int pieGraphicsCounter) {
        this.errors = errors;
        this.mathOperators = mathOperators;
        this.barGraphicsCounter = barGraphicsCounter;
        this.pieGraphicsCounter = pieGraphicsCounter;
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
        String errType;
        switch (errorType) {
            case 1:
                errType = "Lexico";
                break;
            case 2:
                errType = "Sintactico";
                break;
            case 3:
                errType = "Semantico";
                break;
            default:
                errType = "unexpected";
        }
        this.errors.add(new CommonError(line, col, errType, message, lexeme));
    }

    public ArrayList<CommonError> getErrors() {
        return errors;
    }

    public ArrayList<char[]> getMathOperators() {
        return mathOperators;
    }

    public int getBarGraphicsCounter() {
        return barGraphicsCounter;
    }

    public void setBarGraphicsCounter(int barGraphicsCounter) {
        this.barGraphicsCounter = barGraphicsCounter;
    }

    public int getPieGraphicsCounter() {
        return pieGraphicsCounter;
    }

    public void setPieGraphicsCounter(int pieGraphicsCounter) {
        this.pieGraphicsCounter = pieGraphicsCounter;
    }

    public void setMathOperators(ArrayList<char[]> mathOperators) {
        this.mathOperators = mathOperators;
    }

    public void addError(ArrayList<CommonError> errors) {
        this.errors.addAll(errors);
    }
}
