package fusion_colors;

public class Car extends PartInfo{
    //data fields
    private int id;
    private String make;
    private String model;
    private int year;
    public Car() {}

    public Car(int id, String make, String model, int year) {
        super();
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public Car(int id, String make, String model, int year, String partNumber, String partName, String usedPart, String newPart, String images, String location) {
        super(partNumber, partName, usedPart, newPart, images, location);
        this.id = id;
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public int getYear() {
        return year;
    }

    public int getId() {
        return this.id;
    }

    public String toString() {
        String outPut = "";
        outPut += "Make: " + this.make + " Model: " + this.model + " Year: " + this.year;
        outPut += " " + super.toString();
        return outPut;
    }
}