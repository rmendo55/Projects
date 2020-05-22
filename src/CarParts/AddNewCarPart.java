package CarParts;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class AddNewCarPart {
    private BorderPane border;
    private MainPage main;
    private String selectedImage;

    public AddNewCarPart(BorderPane border, MainPage main) {
        this.border = border;
        this.main = main;
        this.selectedImage = new String();
    }

    public String uploadImages() {
        JFileChooser jFile = new JFileChooser();
        String image = "";
        if (jFile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            BufferedImage bImage = null;
            try {
                File initialImage = new File(jFile.getSelectedFile().getAbsolutePath());
                bImage = ImageIO.read(initialImage);
                String name = jFile.getSelectedFile().getName();
                String[] arr = name.split("\\.");
                ImageIO.write(bImage, arr[1], new File("resources/car_part_images/" + jFile.getSelectedFile().getName()));
                image = jFile.getSelectedFile().getName() +",";
            } catch (Exception err) {
                System.out.println(err.getMessage());
            }
        }
        return image;
    }
//    create input boxes to insert value
    public void createUserInput() {
        //create text field boxes
        HBox h = new HBox();
        HBox h2 = new HBox();
        VBox v1 = new VBox();
        VBox v2 = new VBox();
        Text t1 = new Text("Enter Part Number");
        TextField text1 = new TextField();
        Text t2 = new Text("Enter Make");
        TextField text2 = new TextField();
        Text t3 = new Text("Enter Model");
        TextField text3 = new TextField();
        Text t4 = new Text("Year");
        TextField text4 = new TextField();
        Text t5 = new Text("Part Name");
        TextField text5 = new TextField();
        Text t6 = new Text("Choose Used Type");
        Text t7 = new Text("Choose New Type");
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
        Button images = new Button("Upload Images");
        images.setOnAction(e -> {
            this.selectedImage += uploadImages();
        });
        v1.getChildren().addAll(t1,t2,t3,t4,t5,t6,t7);
        v2.getChildren().addAll(text1,text2,text3,text4,text5,comboBox,comboBox2, images);
        h.getChildren().addAll(v1,v2);
        Button add = new Button("Add");
        Button back = new Button("go back");
        h2.getChildren().addAll(add, back);
        add.setOnAction(e -> {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8000/fusion_colors", "root", "123456");
                String query = "INSERT INTO car_parts (part_number, Make, Model, Year, part_name, used, new, Images) " +
                        "VALUES (" + Integer.parseInt(text1.getText().toString()) + "," + "'" + text2.getText().toString() + "'" + "," + "'" + text3.getText().toString()
                        + "'" + "," + Integer.parseInt(text4.getText().toString()) + "," + "'" + text5.getText().toString() + "'" + "," + "'" + comboBox.getSelectionModel().getSelectedItem().toString() + "'" + "," + "'" + comboBox2.getSelectionModel().getSelectedItem().toString() + "'" + "," + "'" + this.selectedImage + "'" + ");";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(query);
                verifyAddFunction(Integer.parseInt(text1.getText().toString()));
            }catch(Exception err) {
                System.out.println(err);
            }
        });
        back.setOnAction(e -> {
            this.selectedImage = new String();
            this.border = main.setElements();
            this.main.redisplayMainPage(this.border);
        });
        this.border.setBottom(h2);
        this.border.setCenter(h);
        this.border.setAlignment(h, Pos.CENTER);
    }
    public void verifyAddFunction(int part_number) {
        Stage stage = new Stage();
        BorderPane border = new BorderPane();
        Button ok = new Button("Ok");
        border.setCenter(ok);
        border.setTop(new Label("Part Number: " + part_number + " was added"));
        Scene scene = new Scene(border, 200, 200);
        stage.setScene(scene);
        stage.show();
        ok.setOnAction(e -> {
            stage.close();
            createUserInput();
        });
    }
}
