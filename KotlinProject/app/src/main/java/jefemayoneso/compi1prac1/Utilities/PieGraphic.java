/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jefemayoneso.compi1prac1.Utilities;

import androidx.annotation.NonNull;

/**
 *
 * @author jefemayoneso
 */
public class PieGraphic extends GraphicData {

    // for errors
    private short tagsDecl = 0;
    private short totalDecl = 0;
    private short valuesDecl = 0;
    private short pieTypeDecl = 0; // 0 -> Cantidad, 1 -> Porcentaje
    private short extraDecl = 0;

    //data
    private String pieGraphicType;
    private String[] tags;
    private Double[] values;
    private Double total;
    private String extra;

    public PieGraphic() {
    }

    public PieGraphic(String pieGraphicType, String[] tags, Double[] values, Double total, String extra) {
        this.pieGraphicType = pieGraphicType;
        this.tags = tags;
        this.values = values;
        this.total = total;
        this.extra = extra;
    }

    public short getTagsDecl() {
        return tagsDecl;
    }

    public void setTagsDecl(short tagsDecl) {
        this.tagsDecl = tagsDecl;
    }

    public short getTotalDecl() {
        return totalDecl;
    }

    public void setTotalDecl(short totalDecl) {
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

    public short getValuesDecl() {
        return valuesDecl;
    }

    public void setValuesDecl(short valuesDecl) {
        this.valuesDecl = valuesDecl;
    }

    public short getPieTypeDecl() {
        return pieTypeDecl;
    }

    public void setPieTypeDecl(short pieTypeDecl) {
        this.pieTypeDecl = pieTypeDecl;
    }

    public short getExtraDecl() {
        return extraDecl;
    }

    public void setExtraDecl(short extraDecl) {
        this.extraDecl = extraDecl;
    }

    @Override
    public boolean isValidGraph() {
        if (this.errorCounter > 0) {
            return false;
        } if(this.pieGraphicType.equals("Porcentaje") && this.pieTypeDecl > 0) {
            return false;
        }
        return (this.titleDecl == 1 && pieTypeDecl == 1 && tagsDecl == 1 && valuesDecl == 1
                && this.totalDecl <= 1 && this.mergeDecl == 1 && this.extraDecl <= 1);
    }

    // method to print the info of the object
    @NonNull
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("\nPieGraphic valid: ").append(isValidGraph());
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
        for (short[] merge : mergeItems) {
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
}
