package CarParts;

import java.util.ArrayList;

public class Car extends PartInfo{
    //data fields
    private String make;
    private String model;
    private int year;
    public Car() {}

    public Car(String make, String model, int year) {
        super();
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public Car(String make, String model, int year, int partNumber, String partName, String usedPart, String newPart, ArrayList<String > images) {
        super(partNumber, partName, usedPart, newPart, images);
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

    public void setMake(String make) {
        this.make = make;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String toString() {
        String outPut = "";
        outPut += "Make: " + this.make + " Model: " + this.model + " Year: " + this.year;
        outPut += " " + super.toString();
        return outPut;
    }
}