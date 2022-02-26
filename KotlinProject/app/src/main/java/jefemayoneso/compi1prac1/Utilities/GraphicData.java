package jefemayoneso.compi1prac1.Utilities;

import java.util.ArrayList;

/**
 *
 * @author jefemayoneso
 */
public class GraphicData {

    // for errors
    protected int titleDecl = 0; // Decl = declaration
    protected int mergeDecl = 0;
    protected int errorCounter = 0;

    // data
    protected String title;
    protected ArrayList<int[]> mergeItems;

    public GraphicData() {
    }

    /**
     * is the graphic valid for execute
     *
     * @return true if the graphic is valid
     */
    public boolean isValidGraph() {
        return errorCounter > 0;
    }

    public int getTitleDecl() {
        return titleDecl;
    }

    public void setTitleDecl(int titleDecl) {
        this.titleDecl = titleDecl;
    }

    public int getMergeDecl() {
        return mergeDecl;
    }

    public void setMergeDecl(int mergeDecl) {
        this.mergeDecl = mergeDecl;
    }

    public int getErrorCounter() {
        return errorCounter;
    }

    public void setErrorCounter(int errorCounter) {
        this.errorCounter = errorCounter;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ArrayList<int[]> getMergeItems() {
        return mergeItems;
    }

    public void setMergeItems(ArrayList<int[]> mergeItems) {
        this.mergeItems = mergeItems;
    }
    
}
