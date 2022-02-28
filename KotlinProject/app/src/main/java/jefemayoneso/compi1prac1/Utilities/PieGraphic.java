/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jefemayoneso.compi1prac1.Utilities;

import androidx.annotation.NonNull;

import jefemayoneso.compi1prac1.Backend.ParserActions.ReportManager;

/**
 *
 * @author jefemayoneso
 */
public class PieGraphic extends GraphicData {

    // for errors
    private int tagsDecl = 0;
    private int totalDecl = 0;
    private int valuesDecl = 0;
    private int pieTypeDecl = 0; // 0 -> Cantidad, 1 -> Porcentaje
    private int extraDecl = 0;

    //data
    private String pieGraphicType;
    private String[] tags;
    private Double[] values;
    private Double total;
    private String extra;

    public PieGraphic() {
    }

    public int getTagsDecl() {
        return tagsDecl;
    }

    public void setTagsDecl(int tagsDecl) {
        this.tagsDecl = tagsDecl;
    }

    public int getTotalDecl() {
        return totalDecl;
    }

    public void setTotalDecl(int totalDecl) {
        this.totalDecl = totalDecl;
    }

    public String getBarGraphicType() {
        return pieGraphicType;
    }

    public void setPieGraphicType(String pieGraphicType) {
        this.pieGraphicType = pieGraphicType;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    public Double[] getValues() {
        return values;
    }

    public void setValues(Double[] values) {
        this.values = values;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public int getValuesDecl() {
        return valuesDecl;
    }

    public void setValuesDecl(int valuesDecl) {
        this.valuesDecl = valuesDecl;
    }

    public int getPieTypeDecl() {
        return pieTypeDecl;
    }

    public void setPieTypeDecl(int pieTypeDecl) {
        this.pieTypeDecl = pieTypeDecl;
    }

    public int getExtraDecl() {
        return extraDecl;
    }

    public void setExtraDecl(int extraDecl) {
        this.extraDecl = extraDecl;
    }

    @Override
    public boolean isValidGraph(ReportManager reportManager) {
        boolean valid;
        int errCounter = 0;
        if (this.errorCounter > 0) { // error detected while declarations, this errors are validated externally
            return false;
        }
        try {
            if(!isTotalValuesValid()) {
                String message = this.pieGraphicType.equals("Porcentaje")? " deben sumar 100%": " no coinciden con el valor esperado";
                reportManager.addError(-1,-1,"Grafica Pie", "La suma de valores de la grafica "+ this.title + message,3);
                errCounter++;
            }else if (this.pieGraphicType.equals("Porcentaje") && this.totalDecl > 0) { // declared title when type = percentage
                reportManager.addError(-1,-1,"Grafica Pie","La grafica " + this.title + "Ha sido declarada como Porcentaje y tiene almenos 1 declaracion de total",3);
                errCounter++; // this has to be declared 0 times if type is Porcentaje
            } else if(this.pieGraphicType.equals("Cantidad") && this.pieTypeDecl != 1 && this.total != 1) {
                reportManager.addError(-1,-1,"Grafica Pie","La grafica " + this.title + " es de tipo Cantidad y debe tener solamente 1 declaracion de tipo y 1 de total",3);
                errCounter++; // need to be declared 1 time the title when type is Cantidad
            }
        } catch (Exception e) {
            errCounter++;
        }
        // validate more
        valid = this.titleDecl == 1 && pieTypeDecl == 1 && tagsDecl == 1 && valuesDecl == 1
                && this.extraDecl <= 1 && this.totalDecl <= 1 && this.mergeDecl == 1 && errCounter == 0;

        if(!valid) {
            reportManager.addError(-1,-1,"Grafica Pie","La grafica " + this.title + " Debe tener exactamente 1 declaracion de titulo, tipo, etiquetas, valores",3);
            return false;
        } else {
            return true;
        }
    }

    // method to print the info of the object
    @NonNull
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("\nPieGraphic valid: ");
        result.append("\nErrores: ").append(this.errorCounter);
        result.append("\nTitle: ").append(this.title);
        result.append("\nType: ").append(pieGraphicType);
        result.append("\nTags: ");
        for (String tag : tags) {
            result.append("\n\t").append(tag);
        }
        result.append("\nValues: ");
        for (Double value : values) {
            result.append("\n\t").append(value);
        }
        result.append("\nTotal: ").append(total);
        result.append("\nExtra: ").append(extra);
        result.append("\nUnir: ");
        for (int[] merge : mergeItems) {
            result.append("\n\t{").append(merge[0]).append(",").append(merge[1]).append("}");
        }
        result.append("\nDECLARATIONS:");
        result.append("\n\tTags: ").append(tagsDecl);
        result.append("\n\tTotal: ").append(totalDecl);
        result.append("\n\tValues: ").append(valuesDecl);
        result.append("\n\tPieType: ").append(pieTypeDecl);
        result.append("\n\tExtra: ").append(extraDecl);
        result.append("\n\tTitle: ").append(titleDecl);
        return result.toString();
    }

    private boolean isTotalValuesValid() {
        Double tmp = 0.0;
        for (Double tot: this.values) {
            tmp+=tot;
        }
        if(this.pieGraphicType.equals("Cantidad")) {
            return Math.round(tmp) == this.total; // total matches
        } else {
            return Math.round(tmp) == 100; // the sum of percentages is more than 100%
        }
    }
}
