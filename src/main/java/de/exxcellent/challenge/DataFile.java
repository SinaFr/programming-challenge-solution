package de.exxcellent.challenge;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a data file with its path, a label column and columns
 * to compare.
 */
public class DataFile {

    private String path;
    private String labelCol;
    private List<String> colsToCompare;

    public DataFile(String path, String labelCol, List<String> colsToCompare) {
        this.path = path;
        this.labelCol = labelCol;
        this.colsToCompare = colsToCompare;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getLabelCol() {
        return labelCol;
    }

    public void setLabelCol(String labelCol) {
        this.labelCol = labelCol;
    }

    public List<String> getColsToCompare() {
        return colsToCompare;
    }

    public void setColsToCompare(ArrayList<String> colsToCompare) {
        this.colsToCompare = colsToCompare;
    }
}
