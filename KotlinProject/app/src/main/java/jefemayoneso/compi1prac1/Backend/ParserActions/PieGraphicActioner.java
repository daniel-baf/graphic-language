package jefemayoneso.compi1prac1.Backend.ParserActions;

import jefemayoneso.compi1prac1.Utilities.PieGraphic;
import java.util.ArrayList;

/**
 *
 * @author jefemayoneso
 */
public class PieGraphicActioner {

    public boolean titleDeclaration(String title, PieGraphic pieGraph) {
        // add message to errors and set error to object
        pieGraph.setTitleDecl((short) (pieGraph.getTitleDecl() + 1)); // title decs ++
        if (pieGraph.getTitleDecl() <= 1) {
            // save info for execution
            pieGraph.setTitle(title.substring(1, title.length() - 1)); // save info
            return true;
        }
        return false;
    }

    public boolean typeDeclaration(int type, PieGraphic pieGraphic) {
        // save data
        pieGraphic.setPieTypeDecl((short) (pieGraphic.getPieTypeDecl() + 1));
        if (pieGraphic.getPieTypeDecl() <= 1) {
            // save info for execution
            String typeS = type == 0 ? "Cantidad" : "Porcentaje";
            pieGraphic.setPieGraphicType(typeS);
            return true;
        }
        return false;
    }

    boolean tagsDeclaration(String items, PieGraphic pieGraphic) {
        // split item with \n for files
        String[] itemsSplit = items.split("\n");
        pieGraphic.setTagsDecl((short) (pieGraphic.getTagsDecl() + 1));
        // validate lenght > 0
        if (pieGraphic.getTagsDecl() <= 1 && itemsSplit.length > 0) {
            pieGraphic.setTags(itemsSplit);
            return true;
        }
        return false;
    }

    boolean valuesDeclaration(String items, PieGraphic pieGraphic) {
        String[] itemsSplit = items.split("\n");
        Double[] numbers = new Double[itemsSplit.length];
        // cast to double
        for (int i = 0; i < itemsSplit.length; i++) {
            numbers[i] = Double.valueOf(itemsSplit[i]);
        }
        pieGraphic.setValuesDecl((short) (pieGraphic.getValuesDecl() + 1));
        if (pieGraphic.getValuesDecl() <= 1 && numbers.length > 0) {
            pieGraphic.setValues(numbers);
            return true;
        }
        return false;
    }

    boolean totalDeclaration(Double number, PieGraphic pieGraphic) {
        pieGraphic.setTotalDecl((short) (pieGraphic.getTotalDecl() + 1));
        if (pieGraphic.getTotalDecl() <= 1) {
            pieGraphic.setTotal(number);
            return true;
        }
        return false;
    }

    boolean mergeDeclaration(ArrayList<short[]> mergeCoordenates, PieGraphic pieGraphic) {
        pieGraphic.setMergeDecl((short) (pieGraphic.getMergeDecl() + 1));
        if (pieGraphic.getMergeDecl() <= 1 && mergeCoordenates.size() > 0) {
            pieGraphic.setMergeItems(mergeCoordenates);
            return true;
        }
        return false;
    }

    boolean extraDeclaration(String extra, PieGraphic pieGraphic) {
        pieGraphic.setExtraDecl((short) (pieGraphic.getExtraDecl() + 1));
        if (pieGraphic.getExtraDecl() <= 1) {
            pieGraphic.setExtra(extra);
            return true;
        }
        return false;
    }
}
