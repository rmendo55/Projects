package CarParts;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.beans.EventHandler;
import java.io.File;
import java.sql.*;
import java.util.ArrayList;

public class DatabasePage {    private BorderPane border;
    private MainPage main;
    private String selectedImage;
    private int index;
    private String[] arrImages;

    public DatabasePage(BorderPane border, MainPage main){
        this.border = border;
        this.main = main;
        this.selectedImage = new String();
        this.arrImages = new String[0];
    }

    public void readDatabase() {
        try {
            ArrayList<Car> carPartList = new ArrayList<Car>();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8000/fusion_colors", "root", "123456");

            Statement stmt=con.createStatement();
            ResultSet rs =stmt.executeQuery("select * from car_parts;");
            while (rs.next()) {
                carPartList.add(new Car(rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(2),                        rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9)));
            }
            displayDatabase(carPartList);
            con.close();
        }catch(Exception e) {
            System.out.println(e);
        }
    }

    public void displayDatabase(ArrayList<Car> list) {
        Label title = new Label("Fusion Colors Auto Body Repair & Painting");
        this.border.setTop(title);
        Button back = new Button("Main Page");
        this.border.setBottom(back);
        back.setOnAction(e -> {
            this.border = main.setElements();
            this.main.redisplayMainPage(this.border);
        });
        GridPane grid = new GridPane();
        ScrollPane scroll = new ScrollPane();
        grid.add(new Text("Part Number"), 0,0);
        grid.add(new Text("Make"), 1,0);
        grid.add(new Text("Model"), 2,0);
        grid.add(new Text("Year"), 3,0);
        grid.add(new Text("Part Name"), 4,0);
        grid.add(new Text("Used"), 5,0);
        grid.add(new Text("New"), 6,0);
        grid.add(new Text("Image"), 7, 0);
        int index = 0;
        for (int i = 1; i <= list.size(); i++) {
                Hyperlink viewImage = new Hyperlink("View Image");
                final int partNumber = list.get(index).getPartNumber();
                final String images = list.get(index).getImages();
                System.out.println("Images: " + images);
                grid.add(new Text(list.get(index).getPartNumber() + ""), 0, i);
                grid.add(new Text(list.get(index).getMake()), 1, i);
                grid.add(new Text(list.get(index).getModel()), 2, i);
                grid.add(new Text(list.get(index).getYear() + ""), 3, i);
                grid.add(new Text(list.get(index).getPartName()), 4, i);
                grid.add(new Text(list.get(index).isNewPart()), 6, i);
                grid.add(new Text(list.get(index).isUsedPart()), 5, i);
                grid.add(viewImage, 7, i);
                Button remove = new Button("Remove");
                Button modify = new Button("Modify");
                remove.setOnAction(e -> {
                    removeRow(partNumber);
                });
                modify.setOnAction(e -> {
                    modifyRow(grid, partNumber);
                });
                viewImage.setOnAction(e -> {
                    showImages(images, partNumber);
                });
            grid.add(remove, 8, i);
            grid.add(modify, 9, i);
            index++;
        }
        scroll.setContent(grid);
        scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        this.border.setCenter(scroll);
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
            border.setTop(new Label("Are you sure you want to remove item?"));
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
                selected_car = new Car(rs.getString(3), rs.getString(4), rs.getInt(5), rs.getInt(2),rs.getString(6),rs.getString(7), rs.getString(8), rs.getString(9));
            }
            this.selectedImage += selected_car.getImages();
            grid.getChildren().clear();
            //create qui with text fields to edit selected row
            Button back = new Button("Back");
            this.border.setBottom(back);
            back.setOnAction(e -> {
                readDatabase();
            });
            Text modify_text = new Text("Modifying Part Number " + selected_car.getPartNumber());
            this.border.setLeft(modify_text);
            Button images = new Button("Upload Images");
            images.setOnAction(e -> {
                String selected = uploadImages();
                if (!this.selectedImage.contains(selected)) {
                    this.selectedImage += selected;
                }
                else {
                    Pane pane = new Pane();
                    Text text = new Text("Unable to add image. Image already exists");
                    Button ok = new Button("ok");
                    Stage stage = new Stage();
                    pane.getChildren().addAll(text, ok);
                    Scene scene = new Scene(pane, 300,300);
                    stage.setScene(scene);
                    stage.show();
                    ok.setOnAction(e2 -> {
                       stage.close();
                    });
                }
            });
            grid.add(new Text("Part Number"), 0,0);
            grid.add(new Text("Make"), 1,0);
            grid.add(new Text("Model"), 2,0);
            grid.add(new Text("Year"), 3,0);
            grid.add(new Text("Part Name"), 4,0);
            grid.add(new Text("Upload Images"), 5,0);
            grid.add(new Text("Used"), 6,0);
            grid.add(new Text("New"), 7,0);

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
            grid.add(images, 5,1);
            grid.add(comboBox, 6,1);
            grid.add(comboBox2, 7,1);
            Button button = new Button("Apply Changes");
            grid.add(button, 0, 2);
            this.border.setCenter(grid);
            this.border.setAlignment(grid, Pos.CENTER);
            button.setOnAction(e -> {
               String queryUpdate = "UPDATE car_parts SET part_number='" + text1.getText() + "', Make='" + text2.getText() + "', Model='" + text3.getText() + "', Year='" + text4.getText() + "', part_name='" + text5.getText() + "', used='" + comboBox.getSelectionModel().getSelectedItem().toString() + "', new='" + comboBox2.getSelectionModel().getSelectedItem().toString() + "', Images='" + this.selectedImage + "' WHERE part_number=" + partNumber;
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
    public void showImages(String imageNames, int partNumber) {
        this.index = 0;
        this.arrImages = imageNames.split(",");
        Image img = null;
        ImageView view = null;
        this.border.getChildren().clear();
        HBox h = new HBox();
        Button exit = new Button("Exit");
        exit.setOnAction(e -> {
           readDatabase();
        });
        Button next = new Button("Next");
        next.setOnAction(e -> {
            if (this.index + 1 < this.arrImages.length) {
                this.index++;
                Image nextImage = new Image("file:resources/car_part_images/" + this.arrImages[index]);
                ImageView nextView = new ImageView(nextImage);
                Text currentImage = new Text("Image " + (index + 1) + " of " + this.arrImages.length);
                this.border.setTop(currentImage);
                this.border.setCenter(nextView);
            }
            else {
                if (this.arrImages.length != 0) {
                    Image nextImage = new Image("file:resources/car_part_images/" + this.arrImages[index]);
                    ImageView nextView = new ImageView(nextImage);
                    Text currentImage = new Text("Image " + (index + 1) + " of " + this.arrImages.length);
                    this.border.setTop(currentImage);
                    this.border.setCenter(nextView);
                }
            }
        });
        Button prev = new Button("Back");
        prev.setOnAction(e -> {
            if (this.index - 1 >= 0) {
                this.index--;
                Image nextImage = new Image("file:resources/car_part_images/" + this.arrImages[index]);
                ImageView nextView = new ImageView(nextImage);
                Text currentImage = new Text("Image " + (index + 1) + " of " + this.arrImages.length);
                this.border.setTop(currentImage);
                this.border.setCenter(nextView);
            }
            else {
                if (this.arrImages.length != 0) {
                    Image nextImage = new Image("file:resources/car_part_images/" + this.arrImages[index]);
                    ImageView nextView = new ImageView(nextImage);
                    Text currentImage = new Text("Image " + (index + 1) + " of " + this.arrImages.length);
                    this.border.setTop(currentImage);
                    this.border.setCenter(nextView);
                }
            }
        });
        Button removeImage = new Button("Remove");
        removeImage.setOnAction(e -> {
            String[] newImgValues = removeImage(partNumber, this.index);
            this.arrImages = new String[newImgValues.length];
            //deep copy
            for (int i = 0; i < newImgValues.length; i++) {
                this.arrImages[i] = newImgValues[i];
            }
            if (this.arrImages.length == 0) {
                //nothing to display
                this.border.setRight(null);
                this.border.setTop(null);
                this.border.setCenter(new Text("No Images to Display"));
            }
            else {
                Image nextImage = new Image("file:resources/car_part_images/" + this.arrImages[index]);
                ImageView nextView = new ImageView(nextImage);
                Text currentImage = new Text("Image " + (index + 1) + " of " + this.arrImages.length);
                this.border.setTop(currentImage);
                this.border.setCenter(nextView);
            }
        });

        if(this.arrImages[0].equals("")) {
                this.border.setCenter(new Text("No Images to Display"));
            }
            else {
                img = new Image("file:resources/car_part_images/" + this.arrImages[0]);
                view = new ImageView(img);
                Text currentImage = new Text("Image " + (index + 1) + " of " + this.arrImages.length);
                this.border.setTop(currentImage);
                this.border.setCenter(view);
                this.border.setRight(removeImage);
            }
            h.getChildren().addAll(exit, next, prev);
            this.border.setBottom(h);
    }

    /**
     *This method allows user to upload images for all parts in database
     */
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

    public String[] removeImage(int partNumber, int index) {
        String[] newArr = null;
        try {
            String img = "";
            newArr = new String[this.arrImages.length - 1];
            int count = 0;
            for (int i = 0; i < this.arrImages.length; i++) {
                if (i != index) {
                    newArr[count] = this.arrImages[i];
                    img += this.arrImages[i] + ",";
                    count++;
                }
            }
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8000/fusion_colors", "root", "123456");
            String query = "UPDATE car_parts SET Images='" + img + "' WHERE part_number=" + partNumber;
            Statement stmt = con.createStatement();
            stmt.executeUpdate(query);
        }
        catch(Exception err) {
            System.out.println(err);
        }
        return newArr;
    }

}