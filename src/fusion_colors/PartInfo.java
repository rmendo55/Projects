package fusion_colors;

public class PartInfo {
    private String partNumber;
    private String partName;
    private String usedPart;
    private String newPart;
    private String images;
    private String location;
    public PartInfo(){}

    public PartInfo(String partNumber, String partName, String usedPart, String newPart, String images, String location) {
        this.partNumber = partNumber;
        this.partName = partName;
        this.usedPart = usedPart;
        this.newPart = newPart;
        this.images = images;
        this.location = location;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public String getPartName() {
        return partName;
    }

    public String getUsedPart() {
        return usedPart;
    }

    public String getNewPart() {
        return newPart;
    }

    public String getImages() {
        return this.images;
    }

    public String getLocation() {return this.location;}

    public String toString() {
        String outPut = "";
        outPut += "Part Number: " + this.partNumber + " Part Name: " + this.partName + " Used: " + this.usedPart + " New: " + this.newPart;
        return outPut;
    }
}
