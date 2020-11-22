package fusion_colors;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
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
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

import static javafx.scene.paint.Color.WHITE;

public class DatabasePage {

    private BorderPane border;
    private MainPage main;
    private String selectedImage;
    private int index;
    private String[] arrImages;
    private ArrayList<Car> carList;
    private String filePath;
    private LinkedList filePathList;

    public DatabasePage(BorderPane border, MainPage main){
        this.filePathList = new LinkedList();
        this.border = border;
        this.main = main;
        this.selectedImage = new String();
        this.arrImages = new String[0];
    }

    public void readDatabase() {

        try {
            this.carList = new ArrayList<Car>();
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8000/fusion_colors", "root", "123456");

            Statement stmt=con.createStatement();
            ResultSet rs =stmt.executeQuery("select * from car_parts;");
            while (rs.next()) {
                this.carList.add(new Car(rs.getInt(1),rs.getString(3),
                        rs.getString(4), rs.getInt(5),
                        rs.getString(2), rs.getString(6), rs.getString(7),
                        rs.getString(8), rs.getString(9),rs.getString(10)));
            }
            displayDatabase(this.carList);
            con.close();
        }catch(Exception e) {
            System.out.println(e);
        }

    }

    public void displayDatabase(ArrayList<Car> list) {
        this.border.setLeft(null);
        this.main.getStage().setTitle("Database");

        //create Title
        Text text = new Text();
        text.setX(10.0f);
        text.setY(10.0f);
        text.setCache(true);
        text.setText("    FUSION COLORS \n AUTO BODY REPAIR");
        text.setFill(Color.GREEN);
        text.setFont(Font.font(null, FontWeight.BOLD, 60));

        Reflection reflect = new Reflection();
        reflect.setFraction(0.7f);
        text.setEffect(reflect);
        text.setTranslateY(0);

        VBox title = new VBox(10);
        title.getChildren().add(text);
        title.setAlignment(Pos.CENTER);

        border.setTop(title);

        //display a new Background image
        ImageView view = new ImageView(new Image("file:resources/background_images/7.jpg"));
        view.fitWidthProperty().bind(this.main.getStage().widthProperty());
        view.fitHeightProperty().bind(this.main.getStage().heightProperty());
        this.main.getStackPane().getChildren().set(0, view);

        HBox hBox = new HBox(15);
        hBox.getStyleClass().addAll("searchBox","vBox");
        Button back = new Button("Main Page");
        back.getStyleClass().add("btn1");

        VBox searchBox = new VBox();     //add searchButton and search textfield together inside a vbox
        searchBox.getStyleClass().addAll("searchBox", "vBox");

        Button searchButton = new Button("Search");          //Button to search
        searchButton.getStyleClass().add("btn1");

        TextField search = new TextField();                               //textfield for search
        search.getStyleClass().add("textField2");
        search.setPrefWidth(200);
        search.setPrefHeight(60);
        hBox.getChildren().addAll(back,searchButton,search);

        GridPane grid = new GridPane();
        //Grid Pane to show database table
        ScrollPane scroll = new ScrollPane();                               //add grid to scroll pane

        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        scroll.getStyleClass().add("scroll-pane");

        //column names
        Text part_num = new Text("Part Number");
        part_num.setFill(WHITE);
        part_num.setFont(Font.font(30));
        part_num.setUnderline(true);
        grid.add(part_num, 0,0);
        Text make = new Text("Make");
        make.setFill(WHITE);
        make.setFont(Font.font(30));
        make.setUnderline(true);
        grid.add(make, 1,0);
        Text model = new Text("Model");
        model.setFill(WHITE);
        model.setFont(Font.font(30));
        model.setUnderline(true);
        grid.add(model, 2,0);
        Text year = new Text("Year");
        year.setFill(WHITE);
        year.setFont(Font.font(30));
        year.setUnderline(true);
        grid.add(year, 3,0);
        Text part_name = new Text("Part Name");
        part_name.setFill(WHITE);
        part_name.setFont(Font.font(30));
        part_name.setUnderline(true);
        grid.add(part_name, 4,0);
        Text used = new Text("Used");
        used.setFill(WHITE);
        used.setFont(Font.font(30));
        used.setUnderline(true);
        grid.add(used, 5,0);
        Text aNew = new Text("New");
        aNew.setFill(WHITE);
        aNew.setFont(Font.font(30));
        aNew.setUnderline(true);
        grid.add(aNew, 6,0);
        Text image = new Text("Image");
        image.setFill(WHITE);
        image.setFont(Font.font(30));
        image.setUnderline(true);
        grid.add(image, 7, 0);
        Text location = new Text("Location");
        location.setFill(WHITE);
        location.setFont(Font.font(30));
        location.setUnderline(true);
        grid.add(location, 8, 0);


        int index = 0;
        for (int i = 1; i <= list.size(); i++) {
            Hyperlink viewImage = new Hyperlink("View Image");
            viewImage.setFont(Font.font(28));
            viewImage.setTextFill(Color.YELLOW);
            viewImage.getStyleClass().add("hyperlink");
            final int id = list.get(index).getId();
            final String images = list.get(index).getImages();
            Text part_num2 = new Text(list.get(index).getPartNumber() + "");
            part_num2.setFill(WHITE);
            part_num2.setFont(Font.font(28));
            grid.add(part_num2, 0, i);
            Text make2 = new Text(list.get(index).getMake());
            make2.setFill(WHITE);
            make2.setFont(Font.font(28));
            grid.add(make2, 1, i);
            Text model2 = new Text(list.get(index).getModel());
            model2.setFill(WHITE);
            model2.setFont(Font.font(28));
            grid.add(model2, 2, i);
            Text year2 = new Text(list.get(index).getYear() + "");
            year2.setFill(WHITE);
            year2.setFont(Font.font(28));
            grid.add(year2, 3, i);
            Text part_name2 = new Text(list.get(index).getPartName());
            part_name2.setFill(WHITE);
            part_name2.setFont(Font.font(28));
            grid.add(part_name2, 4, i);
            Text aNew2 = new Text(list.get(index).getNewPart());
            aNew2.setFill(WHITE);
            aNew2.setFont(Font.font(28));
            grid.add(aNew2, 5, i);
            Text used2 = new Text(list.get(index).getUsedPart());
            used2.setFill(WHITE);
            used2.setFont(Font.font(28));
            grid.add(used2, 6, i);
            grid.add(viewImage, 7, i);
            Text locate = new Text(list.get(index).getLocation());
            locate.setFill(WHITE);
            locate.setFont(Font.font(28));
            grid.add(locate, 8, i);
            Button remove = new Button("Remove");
            remove.getStyleClass().add("remove");
            Button modify = new Button("Modify");
            modify.getStyleClass().add("modify");
            remove.setOnAction(e -> {
                removeRow(id);
            });
            modify.setOnAction(e -> {
                modifyRow(grid, id);
            });
            viewImage.setOnAction(e -> {
                showImages(images, id);
            });
            grid.add(remove, 9, i);
            grid.add(modify, 10, i);
            index++;
        }

        searchButton.setOnAction(e -> {
            String input = search.getText();
            ArrayList<Car> list2 = new ArrayList<>();
            if (input.equals("")) {
                displayDatabase(this.carList);
            }
            else if (input.charAt(0) >= 48 && input.charAt(0) <= 57) {
                int number = Integer.parseInt(input);
                for (int i = 0; i < this.carList.size(); i++) {
                    if (number == this.carList.get(i).getYear()) {
                        list2.add(this.carList.get(i));
                    }
                }
                displayDatabase(list2);
            }
            else {
                for (int i = 0; i < this.carList.size(); i++) {
                    if (input.equalsIgnoreCase(this.carList.get(i).getPartNumber()) || input.equalsIgnoreCase(this.carList.get(i).getMake()) || input.equalsIgnoreCase(this.carList.get(i).getModel())
                            || input.equalsIgnoreCase(this.carList.get(i).getPartName()) || input.equalsIgnoreCase(this.carList.get(i).getUsedPart())
                            || input.equalsIgnoreCase(this.carList.get(i).getNewPart())
                            || input.equalsIgnoreCase(this.carList.get(i).getLocation())) {
                        list2.add(this.carList.get(i));
                    }
                }
                displayDatabase(list2);
            }
        });

        this.border.setTop(title);  //Putting title at the top
        this.border.setAlignment(title, Pos.CENTER);

        this.border.setBottom(hBox); //Putting button at the bottom
        this.border.setAlignment(hBox, Pos.CENTER);
        scroll.setContent(grid);         //adding grid to scroll pane
        this.border.setCenter(scroll); //setting scrollPane at the center
        this.border.setAlignment(scroll, Pos.CENTER);
        grid.getStyleClass().add("grid");

        //take user back to main page
        back.setOnAction(e -> {
            this.main.getStackPane().getChildren().clear();
            this.main.displayPage(false);
        });
    }
    public int randomNumberForImage() {
        Random rand = new Random();
        return rand.nextInt(12);
    }
    public void removeRow(int id) {
//creating a new stage to verify user if they are sure they want to remove selected item
        Stage stage = new Stage();
        BorderPane border = new BorderPane();
        border.getStylesheets().add("file:resources/Styles.css");
        Button yes = new Button("Yes");
        yes.getStyleClass().add("btn1");
        Button no = new Button("No");
        no.getStyleClass().add("btn1");
        HBox hBox = new HBox();
        hBox.getStyleClass().add("hBox");
        hBox.getChildren().addAll(yes,no);
        hBox.setAlignment(Pos.CENTER);
        border.setCenter(hBox);


        //create Title
        Text text = new Text();
        text.setX(10.0f);
        text.setY(10.0f);
        text.setCache(true);
        text.setText("Are you sure you want to \n remove selected Car Part?");
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

        border.setBackground(new Background(new BackgroundFill(Color.BLACK,CornerRadii.EMPTY,Insets.EMPTY)));
        Scene scene = new Scene(border, 600, 400);
        scene.setFill(Color.BLACK);
        stage.setScene(scene);
        stage.show();
        yes.setOnAction(e -> {
            try {
                removeImageFromResource(id);
                stage.close();
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8000/fusion_colors", "root", "123456");
                String query = "DELETE FROM car_parts WHERE id = " + id + ";";
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

    public void modifyRow(GridPane grid, int id) {
        //obtaining all values for selected item
        try {
            //creating a Car instance
            ImageView view = new ImageView(new Image("file:resources/background_images/4.jpg"));
            view.fitWidthProperty().bind(this.main.getStage().widthProperty());
            view.fitHeightProperty().bind(this.main.getStage().heightProperty());
            this.main.getStackPane().getChildren().set(0, view);
            Car selected_car = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8000/fusion_colors", "root", "123456");
            Statement stmt=con.createStatement();
            ResultSet rs =stmt.executeQuery("select * from car_parts where id=" + id + ";");
            while(rs.next()) {
                selected_car = new Car(rs.getInt(1),rs.getString(3), rs.getString(4), rs.getInt(5), rs.getString(2),rs.getString(6),rs.getString(7), rs.getString(8), rs.getString(9),rs.getString(10));
            }
            if (selected_car.getImages() != null) {
                this.selectedImage += selected_car.getImages();
            }
            grid.getChildren().clear();

            ScrollPane scroll = new ScrollPane();
            scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            scroll.getStyleClass().add("scroll-pane");
            //create gui with text fields to edit selected row
            VBox imagehBox = new VBox(5);
            scroll.setContent(imagehBox);

            Button back = new Button("Back");
            back.getStyleClass().add("btn1");

            HBox hBox = new HBox();

            //create Title
            Text modify_text = new Text();
            modify_text.setX(10.0f);
            modify_text.setY(10.0f);
            modify_text.setCache(true);
            modify_text.setText("Modifying Part Number " + selected_car.getPartNumber());
            modify_text.setFill(WHITE);
            modify_text.setFont(Font.font(null, FontWeight.BOLD, 25));

            Reflection reflect = new Reflection();
            reflect.setFraction(0.7f);
            modify_text.setEffect(reflect);
            modify_text.setTranslateY(0);

            Button images = new Button("Upload Images");
            images.getStyleClass().add("btn1");
            imagehBox.getChildren().add(images);
            Button change = new Button("Apply Changes");
            change.getStyleClass().add("btn1");
            hBox.getChildren().addAll(back,change);
            Text t1 = new Text("Part Number");
            t1.setFill(WHITE);
            t1.setFont(Font.font(70));

            Text t2 = new Text("Make");
            t2.setFill(WHITE);
            t2.setFont(Font.font(70));

            Text t3 = new Text("Model");
            t3.setFill(WHITE);
            t3.setFont(Font.font(70));

            Text t4 = new Text("Year");
            t4.setFill(WHITE);
            t4.setFont(Font.font(70));

            Text t5 = new Text("Part Name");
            t5.setFill(WHITE);
            t5.setFont(Font.font(70));

            Text t6 = new Text("Used");
            t6.setFill(WHITE);
            t6.setFont(Font.font(70));

            Text t7 = new Text("New");
            t7.setFill(WHITE);
            t7.setFont(Font.font(70));

            Text t8 = new Text("Location");
            t8.setFill(WHITE);
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


            //creating text fields and combo boxes to let user enter input values
            TextField text1 = new TextField(selected_car.getPartNumber() + "");
            text1.setFont(Font.font(25));
            text1.getStyleClass().add("textField");
            TextField text2 = new TextField(selected_car.getMake());
            text2.setFont(Font.font(25));
            text2.getStyleClass().add("textField");
            TextField text3 = new TextField(selected_car.getModel());
            text3.setFont(Font.font(25));
            text3.getStyleClass().add("textField");
            TextField text4 = new TextField(selected_car.getYear() + "");
            text4.setFont(Font.font(25));
            text4.getStyleClass().add("textField");
            TextField text5 = new TextField(selected_car.getPartName());
            text5.setFont(Font.font(25));
            text5.getStyleClass().add("textField");
            TextField text6 = new TextField(selected_car.getLocation());
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
                            "None"
                    );
            final ComboBox comboBox = new ComboBox(usedOption);
            final ComboBox comboBox2 = new ComboBox(newOption);
            comboBox.setValue(selected_car.getUsedPart());
            comboBox2.setValue(selected_car.getNewPart());

            grid.add(text1, 1,3);
            grid.add(text2, 3,3);
            grid.add(text3, 1,4);
            grid.add(text4, 3,4);
            grid.add(text5, 1,5);
            grid.add(comboBox, 3,5);
            grid.add(comboBox2, 1,6);
            grid.add(text6, 3,6);
            grid.add(scroll, 0,12);

            back.setOnAction(e -> {
                removeImageFromResource();
                readDatabase();
            });
            images.setOnAction(e -> {
                String selected = uploadImages();
                if (selected != null && !this.selectedImage.contains(selected) && !selected.equals("") ) {
                    this.selectedImage += selected;

                    Text text = new Text(selected.substring(0, selected.length() - 1));
                    text.setFill(WHITE);
                    text.setFont(Font.font(30));
                    HBox imageHBox = new HBox();            //adding a label with hyperlink remove link
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
                else if (selected != null && this.selectedImage.contains(selected)) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Duplicate image! " +
                            " Image already exists", ButtonType.OK);
                    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                    alert.show();
                }
            });
            final int id2 = id;
            change.setOnAction(e -> {
                try {
                    int partNumber = Integer.parseInt(text1.getText());
                    int year = Integer.parseInt(text4.getText());
                    if (validatePartNumber(partNumber, id2)) {
                        System.out.println("Value: " + this.selectedImage);
                        String queryUpdate = "UPDATE car_parts SET part_number='" + partNumber + "', Make='" + text2.getText() + "'," +
                                " Model='" + text3.getText() + "', Year='" + Integer.parseInt(text4.getText()) + "', part_name='" + text5.getText() + "', " +
                                "used='" + comboBox.getSelectionModel().getSelectedItem().toString() + "', " +
                                "new='" + comboBox2.getSelectionModel().getSelectedItem().toString() + "', Images='" + this.selectedImage + "', location='" + text6.getText() + "' WHERE id=" + id;
                        this.selectedImage = new String();
                        System.out.println("Set string to empty: " + this.selectedImage);
                        try {
                            Statement update_statement = con.createStatement();
                            update_statement.executeUpdate(queryUpdate);
                            this.border.getChildren().remove(modify_text);
                            readDatabase();
                        } catch (Exception err) {
                            System.out.println(err);
                        }
                    } else {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION, "Part Number already Exists! ", ButtonType.OK);
                        alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                        alert.show();
                    }
                }catch (Exception er){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION, "Part Number or Year was entered wrong or not entered at all ", ButtonType.OK);
                    alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
                    alert.show();
                }
            });
            this.border.setRight(null);
            this.border.setCenter(grid);
            hBox.setAlignment(Pos.CENTER);
            border.setBottom(hBox);
            this.border.setLeft(modify_text);
            this.border.setAlignment(grid, Pos.CENTER);
        }catch(Exception e) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Either Part Number or Year was entered wrong " +
                    "Please insert a part number or year!", ButtonType.OK);
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.show();
        }
        this.main.getStage().setOnCloseRequest(e -> {
            //remove images from resource folder since user didn't click on add
            removeImageFromResource();
        });

    }
    public boolean validatePartNumber(int partNumber, int id) {
        boolean accepted = true;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8000/fusion_colors", "root", "123456");
            Statement stmt=con.createStatement();
            ResultSet rs =stmt.executeQuery("select id, part_number from car_parts;");
            while (rs.next()) {
                if (id != rs.getInt(1) && partNumber == rs.getInt(2)) {
                    accepted = false;
                }
            }
            con.close();
        }catch (Exception err) {
            System.out.println(err.getMessage());
        }
        return accepted;
    }
    public void showImages(String imageNames, int id) {
        // create a background fill
        Pane pane = new Pane();
        pane.setStyle("-fx-background-color:Black");
        // create Background
        this.main.getStackPane().getChildren().set(0, pane);
        this.main.getStage().setTitle("Images");
        //clear the border pane
        this.border.getChildren().clear();
        this.index = 0;
        this.arrImages = imageNames.split(",");
        Image img = null;
        ImageView view = null;
        Button exit = new Button("Exit");
        exit.getStyleClass().add("btn1");
        Button next = new Button("Next");
        next.getStyleClass().add("btn1");
        Button prev = new Button("Back");
        prev.getStyleClass().add("btn1");
        HBox h = new HBox();

        exit.setOnAction(e -> {
            readDatabase();
        });

        next.setOnAction(e -> {
            String s = this.filePathList.getFirst();
            this.filePathList.removeFirst();
            this.filePathList.addLast(s);
            if (this.index + 1 < this.arrImages.length) {
                this.index++;
                System.out.println("Index: " + this.index);
                Image nextImage = new Image("file:resources/car_part_images/" + this.arrImages[this.index]);
                ImageView nextView = new ImageView(nextImage);
                nextView.setFitWidth(500);
                nextView.setFitHeight(500);
                Text currentImage = new Text("Image " + (index + 1) + " of " + this.arrImages.length);
                currentImage.setFill(Color.GREEN);
                currentImage.setFont(Font.font(25));
                this.border.setTop(currentImage);
                this.border.setCenter(nextView);
            }
            else {
                if (this.arrImages.length != 0 && !this.arrImages[this.index].equals("")) {
                    Image nextImage = new Image("file:resources/car_part_images/" + this.arrImages[this.index]);
                    ImageView nextView = new ImageView(nextImage);
                    nextView.setFitWidth(500);
                    nextView.setFitHeight(500);
                    Text currentImage = new Text("Image " + (this.index + 1) + " of " + this.arrImages.length);
                    currentImage.setFill(Color.GREEN);
                    currentImage.setFont(Font.font(25));
                    this.border.setTop(currentImage);
                    this.border.setCenter(nextView);
                }
            }
        });
        prev.setOnAction(e -> {
            String s = this.filePathList.getLast();
            this.filePathList.removeLast();
            this.filePathList.addFirst(s);
            if (this.index - 1 >= 0) {
                this.index--;
                System.out.println("index: " + this.index);
                Image nextImage = new Image("file:resources/car_part_images/" + this.arrImages[this.index]);
                ImageView nextView = new ImageView(nextImage);
                nextView.setFitWidth(500);
                nextView.setFitHeight(500);
                Text currentImage = new Text("Image " + (index + 1) + " of " + this.arrImages.length);
                currentImage.setFill(Color.GREEN);
                currentImage.setFont(Font.font(25));
                this.border.setTop(currentImage);
                this.border.setCenter(nextView);
            }
            else {
                if (this.arrImages.length != 0 && !this.arrImages[this.index].equals("")) {
                    Image nextImage = new Image("file:resources/car_part_images/" + this.arrImages[index]);
                    ImageView nextView = new ImageView(nextImage);
                    nextView.setFitWidth(500);
                    nextView.setFitHeight(500);
                    Text currentImage = new Text("Image " + (index + 1) + " of " + this.arrImages.length);
                    currentImage.setFill(Color.GREEN);
                    currentImage.setFont(Font.font(25));
                    this.border.setTop(currentImage);
                    this.border.setCenter(nextView);
                }
            }
        });


        Button removeImage = new Button("Remove");
        removeImage.getStyleClass().add("btn1");
        removeImage.setOnAction(e -> {
            String[] newImgValues = removeImage(id, this.index);
            this.arrImages = new String[newImgValues.length];
            //deep copy
            for (int i = 0; i < newImgValues.length; i++) {
                this.arrImages[i] = newImgValues[i];
            }
            if (this.index > 0) {
                this.index--;
            }

            if (this.arrImages.length == 0) {
                //nothing to display
                this.border.setTop(null);
                Text text = new Text("No Images to Display");
                text.setFont(Font.font(25));
                text.setFill(Color.WHITE);
                this.border.setCenter(text);
            }
            else {
                Image nextImage = new Image("file:resources/car_part_images/" + this.arrImages[this.index]);
                ImageView nextView = new ImageView(nextImage);
                nextView.setFitWidth(500);
                nextView.setFitHeight(500);
                Text currentImage = new Text("Image " + (index + 1) + " of " + this.arrImages.length);
                currentImage.setFill(Color.GREEN);
                currentImage.setFont(Font.font(25));
                this.border.setTop(currentImage);
                this.border.setCenter(nextView);
            }
        });
        if(this.arrImages[0].equals("")) {
            Text text = new Text("No Images to Display");
            text.setFont(Font.font(25));
            text.setFill(Color.WHITE);
            this.border.setCenter(text);
        }
        else {
            System.out.println(this.index);
            img = new Image("file:resources/car_part_images/" + this.arrImages[index]);
            view = new ImageView(img);
            view.setFitWidth(500);
            view.setFitHeight(500);
            Text currentImage = new Text("Image " + (this.index + 1) + " of " + this.arrImages.length);
            currentImage.setFill(Color.GREEN);
            currentImage.setFont(Font.font(25));
            this.border.setTop(currentImage);
            this.border.setCenter(view);
            this.border.setRight(removeImage);
        }

        h.getChildren().addAll(exit, next, prev, removeImage);
        this.border.setBottom(h);
        h.setAlignment(Pos.CENTER);
    }

    /**
     *This method allows user to upload images for all parts in database
     */
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

    public String[] removeImage(int id, int index) {
        String[] newArr = null;
        String imgToRemove = "";
        String imgNotToRemove = "";

        try {

            newArr = new String[this.arrImages.length - 1];
            int count = 0;
            for (int i = 0; i < this.arrImages.length; i++) {
                if (i != index) {
                    imgNotToRemove += this.arrImages[i] + ",";
                    newArr[count] = this.arrImages[i];
                    count++;
                }
                else if(i == index){
                    imgToRemove += this.arrImages[i];
                }
                removeImageFromResource(id,imgToRemove, imgNotToRemove);
            }

        }
        catch(Exception err) {
            System.out.println(err);
        }
        return newArr;
    }

    public void removeImageFromResource() {
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

    public void removeImageFromResource(int id) {
        String[] arr = null;
        //store all images in a arrayList
        LinkedList list = new LinkedList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8000/fusion_colors", "root", "123456");
            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery("select id, Images from car_parts;");
            while (rs.next()) {
                if (rs.getInt(1) == id) {
                    this.selectedImage = rs.getString(2);
                }
                else {
                    list.addFirst(rs.getString(2));
                }
            }
            arr = this.selectedImage.split(",");
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
                }
                catch(Exception err) {
                    System.out.println(err.getMessage());
                }
            }
        }
    }

    public void removeImageFromResource(int id, String imgToRemove, String imgNotToRemove) {
        //store all images in a arrayList
        LinkedList list = new LinkedList();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:8000/fusion_colors", "root", "123456");
            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery("select id, Images from car_parts;");
            while (rs.next()) {
                if (rs.getInt(1) != id) {
                    list.addFirst(rs.getString(2));
                }
            }
            if (!list.exist(imgToRemove)) {
                Path xPath = Paths.get("resources/car_part_images/" + imgToRemove);
                Files.delete(xPath);
            }
            String query = "UPDATE car_parts SET Images='" + imgNotToRemove + "' WHERE id=" + id;
            stmt.executeUpdate(query);
            con.close();
        }catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }
}
