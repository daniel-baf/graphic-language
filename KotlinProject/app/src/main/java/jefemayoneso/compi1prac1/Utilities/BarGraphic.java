/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jefemayoneso.compi1prac1.Utilities;

import androidx.annotation.NonNull;

import java.util.Objects;

import jefemayoneso.compi1prac1.Backend.ParserActions.ReportManager;

/**
 *
 * @author jefemayoneso
 */
public class BarGraphic extends GraphicData {

    private int xAxisDecl = 0;
    private int yAxisDecl = 0;

    // data
    private String[] xAxisItems;
    private Double[] yAxisItems;

    public BarGraphic() {
    }

    @Override
    public boolean isValidGraph(ReportManager reportManager) {
        if (this.errorCounter > 0) {
            return false;
        }
        try {
            if(this.title.isEmpty()) {
                reportManager.addError(-1,-1,"Grafica barra","El titulo de la grafica no puede estar vacio",3);
                return false;
            } else if(this.xAxisDecl != 1) {
                reportManager.addError(-1,-1,"Grafica barra","Se debe definir solamente 1 vez los ejes x",3);
                return false;
            } else if(this.yAxisDecl != 1) {
                reportManager.addError(-1,-1,"Grafica barra","Se debe definir solamente 1 vez los ejes y",3);
                return false;
            } else if(this.titleDecl != 1) {
                reportManager.addError(-1,-1,"Grafica barra","Se debe definir solamente 1 vez el titulo",3);
                return false;
            } else if(this.mergeDecl != 1) {
                reportManager.addError(-1,-1,"Grafica barra","Se debe definir solamente 1 vez el atributo unir",3);
                return false;
            }
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    public int getxAxisDecl() {
        return xAxisDecl;
    }

    public void setxAxisDecl(int xAxisDecl) {
        this.xAxisDecl = xAxisDecl;
    }

    public int getyAxisDecl() {
        return yAxisDecl;
    }

    public void setyAxisDecl(int yAxisDecl) {
        this.yAxisDecl = yAxisDecl;
    }

    public String[] getxAxisItems() {
        return xAxisItems;
    }

    public void setxAxisItems(String[] xAxisItems) {
        this.xAxisItems = xAxisItems;
    }

    public Double[] getyAxisItems() {
        return yAxisItems;
    }

    public void setyAxisItems(Double[] yAxisItems) {
        this.yAxisItems = yAxisItems;
    }

    @NonNull
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("\nBarGraphic");
        result.append("\nErrores: ").append(this.errorCounter);
        result.append("\nTitle: ").append(this.title);
        result.append("\nxAxis: ");
        for (String tag : xAxisItems) {
            result.append("\n\t").append(tag);
        }
        result.append("\nyAxis: ");
        for (Double value : yAxisItems) {
            result.append("\n\t").append(value);
        }
        result.append("\nUnir: ");
        for (int[] merge : mergeItems) {
            result.append("\n\t{").append(merge[0]).append(",").append(merge[1]).append("}");
        }
        result.append("\nDECLARATIONS:");
        result.append("\n\tyAxis: ").append(yAxisDecl);
        result.append("\n\txAxis: ").append(yAxisDecl);
        result.append("\n\tmerge: ").append(mergeDecl);
        result.append("\n\tTitle: ").append(titleDecl);
        return result.toString();
    }
}
