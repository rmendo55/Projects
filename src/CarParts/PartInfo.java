package CarParts;

import java.util.ArrayList;

public class PartInfo {
    private int partNumber;
    private String partName;
    private String usedPart;
    private String newPart;
    private ArrayList<String> images;
    public PartInfo(){}

    public PartInfo(int partNumber, String partName, String usedPart, String newPart, ArrayList<String> images) {
        this.partNumber = partNumber;
        this.partName = partName;
        this.usedPart = usedPart;
        this.newPart = newPart;
    }

    public int getPartNumber() {
        return partNumber;
    }

    public String getPartName() {
        return partName;
    }

    public String isUsedPart() {
        return usedPart;
    }

    public String isNewPart() {
        return newPart;
    }

    public void setPartNumber(int partNumber) {
        this.partNumber = partNumber;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public void setUsedPart(String usedPart) {
        this.usedPart = usedPart;
    }

    public void setNewPart(String newPart) {
        this.newPart = newPart;
    }

    public String toString() {
        String outPut = "";
        outPut += "Part Number: " + this.partNumber + " Part Name: " + this.partName + " Used: " + this.usedPart + " New: " + this.newPart;
        return outPut;
    }
}
