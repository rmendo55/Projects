package sortingalgorithms;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

public class Main extends Application {

    public void start (Stage stage) {

        Pane pane = new Pane();
        HBox h = new HBox();
        h.setSpacing(20);
        Button b1 = new Button("50000");
        Button b2 = new Button("100000");
        Button b3 = new Button("150000");
        Button b4 = new Button("200000");
        Button b5 = new Button("250000");
        Button b6 = new Button("300000");
        h.getChildren().addAll(b1, b2, b3,b4, b5,b6);
        pane.getChildren().addAll(h);
        pane.setStyle("-fx-background-color: BLACK");

        Scene paneScene = new Scene(pane);
        Stage paneStage = new Stage();
        paneStage.setScene(paneScene);
        paneStage.setTitle("Choose ArrayList Size You Want to test");
        paneStage.show();

        b1.setOnAction(e -> {
            paneStage.close();
            int size = Integer.parseInt(b1.getText());
            long[] arr = testAlgorithms(size);
            CreateChart chart = new CreateChart(size, arr);
            chart.createGui();
        });

        b2.setOnAction(e -> {
            paneStage.close();
            int size = Integer.parseInt(b2.getText());
            long[] arr = testAlgorithms(size);
            CreateChart chart = new CreateChart(size, arr);
            chart.createGui();
        });

        b3.setOnAction(e -> {
            paneStage.close();
            int size = Integer.parseInt(b3.getText());
            long[] arr = testAlgorithms(size);
            CreateChart chart = new CreateChart(size, arr);
            chart.createGui();
        });

        b4.setOnAction(e -> {
            paneStage.close();
            int size = Integer.parseInt(b4.getText());
            long[] arr = testAlgorithms(size);
            CreateChart chart = new CreateChart(size, arr);
            chart.createGui();
        });

        b5.setOnAction(e -> {
            paneStage.close();
            int size = Integer.parseInt(b5.getText());
            long[] arr = testAlgorithms(size);
            CreateChart chart = new CreateChart(size, arr);
            chart.createGui();
        });

        b6.setOnAction(e -> {
            paneStage.close();
            int size = Integer.parseInt(b6.getText());
            long[] arr = testAlgorithms(size);
            CreateChart chart = new CreateChart(size, arr);
            chart.createGui();
        });
    }
    public static void main(String[] args) {
       launch(args);

    }

    public static<E> void printList(ArrayList<E> list) {
        for (int i = 0; i < list.size(); i++) {
            if (i % 10 == 0) {
                System.out.println();
                System.out.print(list.get(i) + " ");
            }
            System.out.print(list.get(i) + " ");

        }
    }

    public static long[] testAlgorithms(int size) {
        long[] arr = new long[7];


        ArrayList<Integer> list = new ArrayList<>();
        int random =  0;
        for (int i = 0; i < size; i++) {
            random = (int)(Math.random() * 999 + 1);
            list.add(random);
        }

        ArrayList<Integer> list2 = new ArrayList<>();
        ArrayList<Integer> list3 = new ArrayList<>();
        ArrayList<Integer> list4 = new ArrayList<>();
        ArrayList<Integer> list5 = new ArrayList<>();
        ArrayList<Integer> list6 = new ArrayList<>();
        ArrayList<Integer> list7 = new ArrayList<>();


        for (int i = 0; i < list.size(); i++) {
            list2.add(list.get(i));
            list3.add(list.get(i));
            list4.add(list.get(i));
            list5.add(list.get(i));
            list6.add(list.get(i));
            list7.add(list.get(i));
        }

        //bubble sort
        Instant start = Instant.now();
        Sorting.bubbleSort(list);
        Instant end = Instant.now();
        long elapsed1 = Duration.between(start, end).toMillis();
        arr[0] = elapsed1;


        //Insertion Sort
        Instant start2 = Instant.now();
        Sorting.insertionSort(list2);
        Instant end2 = Instant.now();
        long elapsed2 = Duration.between(start2, end2).toMillis();
        arr[1] = elapsed2;


        //Selection Sort
        Instant start3 = Instant.now();
        Sorting.selectionSort(list3);
        Instant end3 = Instant.now();
        long elapsed3 = Duration.between(start3, end3).toMillis();
        arr[2] = elapsed3;

        //Quick Sort
        Instant start4 = Instant.now();
        Sorting.quickSort(list4);
        Instant end4 = Instant.now();
        long elapsed4 = Duration.between(start4, end4).toMillis();
        arr[3] = elapsed4;


        //Merge Sort
        Instant start5 = Instant.now();
        Sorting.mergeSort(list5);
        Instant end5 = Instant.now();
        long elapsed5 = Duration.between(start5, end5).toMillis();
        arr[4] = elapsed5;

        //Counting Sort
        Instant start6 = Instant.now();
        Sorting.countingSort(list6, 1000);
        Instant end6 = Instant.now();
        long elapsed6 = Duration.between(start6, end6).toMillis();
        arr[5] = elapsed6;


        //radix sort
        Instant start7 = Instant.now();
        Sorting.radixSort(list7);
        Instant end7 = Instant.now();
        long elapsed7 = Duration.between(start7, end7).toMillis();
        arr[6] = elapsed7;
        System.out.println(elapsed7);

        return arr;
    }
}
