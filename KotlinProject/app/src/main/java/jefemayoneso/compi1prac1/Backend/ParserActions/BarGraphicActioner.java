/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  jefemayoneso.compi1prac1.Backend.ParserActions;

import jefemayoneso.compi1prac1.Utilities.BarGraphic;
import java.util.ArrayList;

/**
 *
 * @author jefemayoneso
 */
public class BarGraphicActioner {

    /**
     * Regist a title declaration and save the info of title in case never
     * declared before. This method receive info from token VALUE_ON-COMILLAS so
     * replace al "\"" for ""
     *
     * @param title the title of the declaration
     */
    public boolean titleDeclaration(String title, BarGraphic barGraph) {
        // add message to errors and set error to object
        barGraph.setTitleDecl((short) (barGraph.getTitleDecl() + 1)); // title decs ++
        if (barGraph.getTitleDecl() <= 1) {
            // save info for execution
            barGraph.setTitle(title.substring(1, title.length() - 1)); // save info
            return true;
        }
        return false;
    }

    /**
     * This method regist the values for x axis declarations in a pie graphic
     *
     * @param items the items to save
     */
    public boolean xAxisDeclaration(String items, BarGraphic barGraph) {
        // split item with \n for files
        barGraph.setxAxisDecl((short) (barGraph.getxAxisDecl() + 1)); // x axis dec ++
        String[] itemsSplit = items.split("\n");
        // validate lenght > 0
        if (barGraph.getxAxisDecl() <= 1 && itemsSplit.length > 0) {
            barGraph.setxAxisItems(itemsSplit); // save info
            return true;
        }
        return false;
    }

    /**
     * This method add the Doubles of the values for graphic, and validate if
     * there is more than 1 declaration, if yes, add an error
     *
     * @param items the items to save for y axis
     */
    public boolean yAxisDeclaration(String items, BarGraphic barGraph) {
        String[] itemsSplit = items.split("\n");
        Double[] numbers = new Double[itemsSplit.length];
        // cast to double
        barGraph.setyAxisDecl((short) (barGraph.getyAxisDecl() + 1));
        for (int i = 0; i <= itemsSplit.length; i++) {
            numbers[i] = Double.valueOf(itemsSplit[i]);
        }
        // validate lenght
        if (barGraph.getyAxisDecl() < 1 && items.length() > 0) {
            barGraph.setyAxisItems(numbers);
            return true;
        }
        return false;
    }

    /**
     * This method add a regist for unir[{a,b}...] delcarations, if there is an
     * existing declaration, add an error
     *
     * @param mergeCoordenates the values of x with y to merge
     */
    public boolean mergeDeclaration(ArrayList<short[]> mergeCoordenates, BarGraphic barGraph) {
        barGraph.setMergeDecl((short) (barGraph.getMergeDecl() + 1));
        if (barGraph.getMergeDecl() <= 1 && mergeCoordenates.size() > 0) {
            barGraph.setMergeItems(mergeCoordenates);
            return true;
        }
        return false;
    }
}
