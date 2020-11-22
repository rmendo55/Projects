package fusion_colors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class AddNewCarPart {
    private BorderPane border;
    private MainPage main;
    private String filePath;
    private String selectedImage;
    private LinkedList filePathList;

    public AddNewCarPart(BorderPane border, MainPage main) {
        this.filePathList = new LinkedList();
        this.border = border;
        this.main = main;
        this.selectedImage = new String();
    }

    public String uploadImages() {
        String image = "";
        FileChooser jFile = new FileChooser();
        jFile.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png"),
                new FileChooser.ExtensionFilter("GIF", "*.gif"));
        File file = jFile.showOpenDialog(this.main.getStage());
        BufferedImage bImage = null;
        try {
            File initialImage = new File(file.getAbsolutePath());
            this.filePath = "resources/car_part_images/" + file.getName();
            this.filePathList.addLast("resources/car_part_images/" + file.getName());
            bImage = ImageIO.read(initialImage);
            String name = file.getName();
            String[] arr = name.split("\\.");
            ImageIO.write(bImage, arr[1], new File("resources/car_part_images/" + file.getName()));
            image = file.getName() + ",";
        }
        catch(Exception err) {
            image = null;
        }
        return image;
    }

    public void createUserInput() {
        ImageView view = new ImageView(new Image("file:resources/background_images/12.jpg"));
        view.fitWidthProperty().bind(this.main.getStage().widthProperty());
        view.fitHeightProperty().bind(this.main.getStage().heightProperty());
        this.main.getStackPane().getChildren().set(0, view);

        this.main.getStage().setTitle("Add New Car Part");
        GridPane grid = new GridPane();


        ScrollPane scroll = new ScrollPane();
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scroll.getStyleClass().add("scroll-pane");

        VBox imagehBox = new VBox(5);
        scroll.setContent(imagehBox);

        Text t1 = new Text("Enter Part Number");
        t1.setFill(Color.WHITE);
        t1.setFont(Font.font(60));

        Text t2 = new Text("Enter Make");
        t2.setFill(Color.WHITE);
        t2.setFont(Font.font(60));

        Text t3 = new Text("Enter Model");
        t3.setFill(Color.WHITE);
        t3.setFont(Font.font(60));

        Text t4 = new Text("Year");
        t4.setFill(Color.WHITE);
        t4.setFont(Font.font(60));

        Text t5 = new Text("Part Name");
        t5.setFill(Color.WHITE);
        t5.setFont(Font.font(60));

        Text t6 = new Text("Choose Used Type");
        t6.setFill(Color.WHITE);
        t6.setFont(Font.font(60));


        Text t7 = new Text("Choose New Type");
        t7.setFill(Color.WHITE);
        t7.setFont(Font.font(60));

        Text t8 = new Text("Location");
        t8.setFill(Color.WHITE);
        t8.setFont(Font.font(70));

        //setting column values
        grid.add(t1, 0,3);
        grid.add(t2, 2,3);
        grid.add(t3, 0,4);
        grid.add(t4, 2,4);
        grid.add(t5, 0,5);
        grid.add(t6, 2,5);
        grid.add(t7, 0,6);
        grid.add(t8, 2,6);

        TextField text1 = new TextField();
        text1.setFont(Font.font(20));
        text1.getStyleClass().add("textField");
        TextField text2 = new TextField();
        text2.setFont(Font.font(20));
        text2.getStyleClass().add("textField");
        TextField text3 = new TextField();
        text3.setFont(Font.font(20));
        text3.getStyleClass().add("textField");
        TextField text4 = new TextField();
        text4.setFont(Font.font(20));
        text4.getStyleClass().add("textField");
        TextField text5 = new TextField();
        text5.setFont(Font.font(20));
        text5.getStyleClass().add("textField");
        TextField text6 = new TextField();
        text6.setFont(Font.font(25));
        text6.getStyleClass().add("textField");
        ObservableList<String> usedOption =
                FXCollections.observableArrayList(
                        "After Market",
                        "Junk Yard",
                        "None"
                );
        ObservableList<String> newOption =
                FXCollections.observableArrayList(
                        "Dealer",
                        "After Market",
                        "None"
                );
        final ComboBox comboBox = new ComboBox(usedOption);
        comboBox.getStyleClass().add("combo-box");
        final ComboBox comboBox2 = new ComboBox(newOption);
        comboBox2.getStyleClass().add("combo-box");
        comboBox.setValue("None");
        comboBox2.setValue("None");
        Button images = new Button("Upload Images");
        images.getStyleClass().add("btn1");
        imagehBox.getChildren().add(images);

        grid.add(text1, 1,3);
        grid.add(text2, 3,3);
        grid.add(text3, 1,4);
        grid.add(text4, 3,4);
        grid.add(text5, 1,5);
        grid.add(comboBox, 3,5);
        grid.add(comboBox2, 1,6);
        grid.add(text6, 3,6);
        grid.add(scroll, 1,12);


        images.setOnAction(e -> {
            this.selectedImage += uploadImages();
        });

        this.border.setCenter(grid);
        this.border.setAlignment(grid, Pos.CENTER);

        HBox hBox = new HBox();
        Button add = new Button("Add");
        add.getStyleClass().add("btn1");
        Button back = new Button("go back");
        back.getStyleClass().add("btn1");
        hBox.getChildren().addAll(add,back);
        hBox.setAlignment(Pos.CENTER);
        this.border.setBottom(hBox);
        images.setOnAction(e -> {
            String selected = uploadImages();
            if (selected != null && !this.selectedImage.contains(selected) && !selected.equals("") ) {
                this.selectedImage += selected;
                HBox imageHBox = new HBox();            //adding a label with hyperlink remove link
                Text text = new Text(selected.substring(0, selected.length() - 1));
                text.setFill(Color.WHITE);
                text.setFont(Font.font(30));
                Hyperlink removeImage = new Hyperlink("X");
                removeImage.setFont(Font.font(30));
                removeImage.setTextFill(Color.RED);
                imageHBox.getChildren().addAll(text, removeImage);
                imagehBox.getChildren().add(imageHBox);
                removeImage.setOnAction(e2 -> {
                    String current = "";
                    String[] arr = this.selectedImage.split(",");
                    this.selectedImage = new String();
                    for (int i = 0; i < arr.length; i++) {
                        current = arr[i] += ",";
                        if (!arr[i].equals(selected)){
                            this.selectedImage += arr[i];
                        }
                    }
                    imageHBox.getChildren().clear();

                    try {
                        String value = this.filePathList.remove(selected);
                        Path xPath = Paths.get(value);
                        Files.delete(xPath);
                    }
                    catch(Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                });
            }
            else if (selected != null && this.selectedImage.contains(selected) && !this.selectedImage.isEmpty()){
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Unable to add image. Image already exists", ButtonType.OK);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.show();
            }
        });

        add.setOnAction(e -> {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8000/fusion_colors", "root", "123456");
                String partNumber = text1.getText();
                int year = Integer.parseInt(text4.getText());
                if (validatePartNumber(partNumber)) {
                    String query = "INSERT INTO car_parts (part_number, Make, Model, Year, part_name, used, new, images,location) " +
                            "VALUES (" + "'" + partNumber + "'" + "," + "'" + text2.getText() + "'" + "," + "'" + text3.getText()
                            + "'" + "," + year + "," + "'" + text5.getText() + "'" + "," + "'" +
                            comboBox.getSelectionModel().getSelectedItem().toString() + "'" + "," + "'" + comboBox2.getSelectionModel().getSelectedItem().toString()
                            + "'" + "," + "'" + this.selectedImage + "'" + "," + "'" + text6.getText() + "'" + ");";
                    Statement stmt = con.createStatement();
                    stmt.executeUpdate(query);
                    verifyAddFunction(partNumber);
                    this.selectedImage = new String();
                }
                else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION,"Part Number already exists!", ButtonType.OK);
                    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                    alert.show();
                }
            } catch (Exception err) {
                System.out.println("error");
                System.out.println(err.getMessage());
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Either part Number or year was entered wrong or not entered at all" , ButtonType.OK);
                alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                alert.show();
            }
        });
        back.setOnAction(e -> {
            removeImages();
            this.main.getStackPane().getChildren().clear();
            this.main.displayPage(false);
        });

        this.border.setBottom(hBox);

        this.main.getStage().setOnCloseRequest(e -> {
            removeImages();
        });
    }

    public void verifyAddFunction(String part_number) {
        Stage stage = new Stage();
        BorderPane border = new BorderPane();
        border.getStylesheets().add("file:resources/Styles.css");

        //create Title
        Text text = new Text();
        text.setX(10.0f);
        text.setY(10.0f);
        text.setCache(true);
        text.setText("Part Number: " + part_number + " was added");
        text.setFill(Color.GREEN);
        text.setFont(Font.font(null, FontWeight.BOLD, 40));

        Reflection reflect = new Reflection();
        reflect.setFraction(0.7f);
        text.setEffect(reflect);
        text.setTranslateY(0);

        VBox title = new VBox(10);
        title.getChildren().add(text);
        title.setAlignment(Pos.CENTER);

        border.setTop(title);

        Button ok = new Button("Ok");
        ok.getStyleClass().add("btn1");

        border.setCenter(ok);
        border.setBackground(new Background(new BackgroundFill(Color.BLACK,CornerRadii.EMPTY, Insets.EMPTY)));
        Scene scene = new Scene(border, 600, 400);
        stage.setScene(scene);
        stage.show();
        ok.setOnAction(e -> {
            stage.close();
            createUserInput();
        });
    }

    public boolean validatePartNumber(String partNumber) {
        System.out.println("Validate Part Number");
        boolean accepted = false;
        try {
            ArrayList<String> list = new ArrayList<String>();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8000/fusion_colors", "root", "123456");
            Statement stmt=con.createStatement();
            ResultSet rs =stmt.executeQuery("select part_number from car_parts;");
            while (rs.next()) {
                list.add(rs.getString(1));
            }
            con.close();
            if (!list.contains(partNumber)) {
                accepted = true;
            }

        }catch (Exception err) {
            System.out.println(err.getMessage());
        }
        return accepted;
    }

    public void removeImages() {
        String[] arr = this.selectedImage.split(",");
        //store all images in a arrayList
        LinkedList list = new LinkedList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8000/fusion_colors", "root", "123456");
            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery("select Images from car_parts;");
            while (rs.next()) {
                list.addFirst(rs.getString(1));
            }
            con.close();
        }catch (Exception err) {
            System.out.println(err.getMessage());
        }
        for (int i = 0; i < arr.length; i++) {
            //only remove image if image doesn't exist in database
            if (!list.exist(arr[i])) {
                try {
                    Path xPath = Paths.get("resources/car_part_images/" + arr[i]);
                    Files.delete(xPath);
                } catch(Exception err) {
                    System.out.println(err.getMessage());
                }
            }
        }
    }
}