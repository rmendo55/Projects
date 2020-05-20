package CarParts;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;

public class DatabasePage {

    private BorderPane border;
    private MainPage main;
    public DatabasePage(BorderPane border, MainPage main){
        this.border = border;
        this.main = main;
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
        Button back = new Button("Main Page");
        this.border.setBottom(back);
        back.setOnAction(e -> {
            this.border = main.setElements();
            this.main.redisplayMainPage(this.border);
        });
        GridPane grid = new GridPane();
        grid.add(new Text("Part Number"), 0,0);
        grid.add(new Text("Make"), 1,0);
        grid.add(new Text("Model"), 2,0);
        grid.add(new Text("Year"), 3,0);
        grid.add(new Text("Part Name"), 4,0);
        grid.add(new Text("Used"), 5,0);
        grid.add(new Text("New"), 6,0);
        int index = 0;
        for (int i = 1; i <= list.size(); i++) {
                final int partNumber = list.get(index).getPartNumber();
                grid.add(new Text(list.get(index).getPartNumber() + ""), 0, i);
                grid.add(new Text(list.get(index).getMake()), 1, i);
                grid.add(new Text(list.get(index).getModel()), 2, i);
                grid.add(new Text(list.get(index).getYear() + ""), 3, i);
                grid.add(new Text(list.get(index).getPartName()), 4, i);
                grid.add(new Text(list.get(index).isNewPart()), 6, i);
                grid.add(new Text(list.get(index).isUsedPart()), 5, i);

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
            Stage stage = new Stage();
            BorderPane border = new BorderPane();
            Button yes = new Button("Yes");
            Button no = new Button("No");
            HBox hBox = new HBox();
            hBox.getChildren().addAll(yes,no);
            border.setCenter(hBox);
            border.setTop(new Label("Are you seure you want to remove item?"));
            Scene scene = new Scene(border, 200, 200);
            stage.setScene(scene);
            stage.show();
            yes.setOnAction(e -> {
                try {
                    stage.close();
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8000/fusion_colors", "root", "123456");
                    String query = "DELETE FROM car_parts WHERE part_number = " + partNumber + ";";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(query);
                    readDatabase();
                }
                catch(Exception err) {
                    System.out.println(err);
                }
            });
            no.setOnAction(e -> {
               stage.close();
            });
    }

    public void modifyRow(GridPane grid, int partNumber) {
        try {
            Car selected_car = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8000/fusion_colors", "root", "123456");
            Statement stmt=con.createStatement();
            ResultSet rs =stmt.executeQuery("select * from car_parts where part_number=" + partNumber + ";");
            while(rs.next()) {
                selected_car = new Car(rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(2),rs.getString(6),rs.getString(7), rs.getString(8), null);
            }
            grid.getChildren().clear();
            //create qui with text fields to edit selected row
            Button back = new Button("Back");
            this.border.setBottom(back);
            back.setOnAction(e -> {
                readDatabase();
            });
            Text modify_text = new Text("Modifying Part Number " + selected_car.getPartNumber());
            this.border.setLeft(modify_text);
            grid.add(new Text("Part Number"), 0,0);
            grid.add(new Text("Make"), 1,0);
            grid.add(new Text("Model"), 2,0);
            grid.add(new Text("Year"), 3,0);
            grid.add(new Text("Part Name"), 4,0);
            grid.add(new Text("Used"), 5,0);
            grid.add(new Text("New"), 6,0);

            TextField text1 = new TextField(selected_car.getPartNumber() + "");
            TextField text2 = new TextField(selected_car.getMake());
            TextField text3 = new TextField(selected_car.getModel());
            TextField text4 = new TextField(selected_car.getYear() + "");
            TextField text5 = new TextField(selected_car.getPartName());
            ObservableList<String> usedOption =
                    FXCollections.observableArrayList(
                            "After Market",
                            "Junk Yard",
                            "No"
                    );
            ObservableList<String> newOption =
                    FXCollections.observableArrayList(
                            "Dealer",
                            "No"
                    );
            final ComboBox comboBox = new ComboBox(usedOption);
            final ComboBox comboBox2 = new ComboBox(newOption);
            comboBox.setValue(selected_car.isUsedPart());
            comboBox2.setValue(selected_car.isNewPart());

            grid.add(text1, 0,1);
            grid.add(text2, 1,1);
            grid.add(text3, 2,1);
            grid.add(text4, 3,1);
            grid.add(text5, 4,1);
            grid.add(comboBox, 5,1);
            grid.add(comboBox2, 6,1);
            Button button = new Button("Apply Changes");
            grid.add(button, 0, 2);
            this.border.setCenter(grid);
            this.border.setAlignment(grid, Pos.CENTER);
            button.setOnAction(e -> {
               String queryUpdate = "UPDATE car_parts SET part_number='" + text1.getText() + "', Make='" + text2.getText() + "', Model='" + text3.getText() + "', Year='" + text4.getText() + "', part_name='" + text5.getText() + "', used='" + comboBox.getSelectionModel().getSelectedItem().toString() + "', new='" + comboBox2.getSelectionModel().getSelectedItem().toString() + "' WHERE part_number=" + partNumber;
                System.out.println(queryUpdate);
                try {
                    Statement update_statement = con.createStatement();
                    update_statement.executeUpdate(queryUpdate);
                    this.border.getChildren().remove(modify_text);
                    readDatabase();
                }
                catch(Exception err) {
                    System.out.println(err);
                }
            });
        }catch(Exception e) {
            System.out.println(e);
        }
    }
}