/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jefemayoneso.compi1prac1.Utilities;

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
    public boolean isValidGraph() {
        System.out.println("ERRORES: " + errorCounter);
        if (this.errorCounter > 0) {
            return false;
        }
        return this.xAxisDecl == 1 && this.yAxisDecl == 1
                && this.titleDecl == 1 && this.mergeDecl <= 1 && this.title != null
                && !this.title.isEmpty();
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

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("\nBarGraphic valid: ").append(isValidGraph());
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
