package jefemayoneso.compi1prac1.Utilities;

import java.util.ArrayList;

/**
 *
 * @author jefemayoneso
 */
public class GraphicData {

    // for errors
    protected short titleDecl = 0; // Decl = declaration
    protected short mergeDecl = 0;
    protected short errorCounter = 0;

    // data
    protected String title;
    protected ArrayList<short[]> mergeItems;

    public GraphicData() {
    }

    /**
     * is the graphic valid for execute
     *
     * @return
     */
    public boolean isValidGraph() {
        return errorCounter > 0;
    }

    public short getTitleDecl() {
        return titleDecl;
    }

    public void setTitleDecl(short titleDecl) {
        this.titleDecl = titleDecl;
    }

    public short getMergeDecl() {
        return mergeDecl;
    }

    public void setMergeDecl(short mergeDecl) {
        this.mergeDecl = mergeDecl;
    }

    public short getErrorCounter() {
        return errorCounter;
    }

    public void setErrorCounter(short errorCounter) {
        this.errorCounter = errorCounter;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<short[]> getMergeItems() {
        return mergeItems;
    }

    public void setMergeItems(ArrayList<short[]> mergeItems) {
        this.mergeItems = mergeItems;
    }
    
}
