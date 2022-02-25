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

    private short xAxisDecl = 0;
    private short yAxisDecl = 0;

    // data
    private String[] xAxisItems;
    private Double[] yAxisItems;

    public BarGraphic() {
    }

    public BarGraphic(String[] xAxisItems, Double[] yAxisItems) {
        this.xAxisItems = xAxisItems;
        this.yAxisItems = yAxisItems;
    }

    @Override
    public boolean isValidGraph() {
        if (this.errorCounter > 0) {
            return false;
        }
        return this.xAxisDecl == 1 && this.yAxisDecl == 1
                && this.titleDecl == 1 && this.mergeDecl <= 1 && this.title != null
                && !this.title.isEmpty();
    }

    public short getxAxisDecl() {
        return xAxisDecl;
    }

    public void setxAxisDecl(short xAxisDecl) {
        this.xAxisDecl = xAxisDecl;
    }

    public short getyAxisDecl() {
        return yAxisDecl;
    }

    public void setyAxisDecl(short yAxisDecl) {
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

}
