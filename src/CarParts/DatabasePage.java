package CarParts;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.sql.*;
import java.util.ArrayList;

public class DatabasePage {
    private BorderPane border;
    public DatabasePage(BorderPane border){
        this.border = border;
    }

    public void readDatabase() {
        try {
            ArrayList<Car> carPartList = new ArrayList<Car>();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8000/fusion_colors", "root", "123456");

            Statement stmt=con.createStatement();
            ResultSet rs =stmt.executeQuery("select * from car_parts;");
            while (rs.next()) {
                carPartList.add(new Car(rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(2),                        rs.getString(6), rs.getString(7), rs.getString(8), null));
            }
            displayDatabase(carPartList);
            con.close();
        }catch(Exception e) {
            System.out.println(e);
        }
    }

    public void displayDatabase(ArrayList<Car> list) {
        GridPane grid = new GridPane();
        Text text1 = new Text("Part Number");
        Text text2 = new Text("Make");
        Text text3 = new Text("Model");
        Text text4 = new Text("Year");
        Text text5 = new Text("Part Name");
        Text text6 = new Text("Used");
        Text text7 = new Text("New");
        grid.add(text1, 0,0);
        grid.add(text2, 1,0);
        grid.add(text3, 2,0);
        grid.add(text4, 3,0);
        grid.add(text5, 4,0);
        grid.add(text6, 5,0);
        grid.add(text7, 6,0);
        int index = 0;
        for (int i = 1; i <= list.size(); i++) {
                final int partNumber = list.get(index).getPartNumber();
                grid.add(new Text(list.get(index).getPartNumber() + ""), 0, i);
                grid.add(new Text(list.get(index).getMake()), 1, i);
                grid.add(new Text(list.get(index).getModel()), 2, i);
                grid.add(new Text(list.get(index).getYear() + ""), 3, i);
                grid.add(new Text(list.get(index).getPartName()), 4, i);
                if (list.get(index).isUsedPart() == null) {
                    grid.add(new Text(list.get(index).isNewPart()), 6, i);
                    grid.add(new Text("None"), 5, i);
                }
                else {
                    grid.add(new Text(list.get(index).isUsedPart()), 5, i);
                    grid.add(new Text("None"), 6, i);
                }
                Button remove = new Button("Remove");
                Button modify = new Button("Modify");
                remove.setOnAction(e -> {
                    removeRow(partNumber);
                });
                modify.setOnAction(e -> {
                    modifyRow(grid, partNumber);
                });
            grid.add(remove, 7, i);
            grid.add(modify, 8, i);
            index++;
        }
        this.border.setCenter(grid);
        this.border.setAlignment(grid, Pos.CENTER);
        grid.getStyleClass().add("grid");
        this.border.setCenter(grid);
    }

    public void removeRow(int partNumber) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8000/fusion_colors", "root", "123456");
            String query = "DELETE FROM car_parts WHERE part_number = " + partNumber + ";";
            Statement stmt=con.createStatement();
            stmt.executeUpdate(query);
            readDatabase();
        }catch(Exception e) {
            System.out.println(e);
        }
    }
    public void modifyRow(GridPane grid, int partNumber) {
        try {
            Car selected_car = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8000/fusion_colors", "root", "123456");
            String query = "DELETE FROM car_parts WHERE part_number = " + partNumber + ";";
            Statement stmt=con.createStatement();
            ResultSet rs =stmt.executeQuery("select * from car_parts where part_number=" + partNumber + ";");
            while(rs.next()) {
                selected_car = new Car(rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(2),rs.getString(6),rs.getString(7), rs.getString(8), null);
            }
            grid.getChildren().clear();
            //create qui with text fields to edit selected row
            
        }catch(Exception e) {
            System.out.println(e);
        }
    }
}