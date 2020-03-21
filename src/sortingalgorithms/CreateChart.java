package sortingalgorithms;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;


public class CreateChart {
        private int size;
        private long[] arr;
        public CreateChart(int size, long[] arr) {
            this.size = size;
            this.arr = arr;
        }


        public void createGui() {
            final NumberAxis xAxis = new NumberAxis("ArrayList Size", 0, 300000, 50000);
            final NumberAxis yAxis = new NumberAxis("Time(NanoSeconds)", 0, 1000, 50);

            final LineChart<Number, Number> lineChart = new LineChart<Number, Number>(xAxis, yAxis);

            lineChart.setTitle("Sorting Algorithms");
            XYChart.Series<Number, Number> series1 = new XYChart.Series<Number, Number>();
            series1.setName("Bubble Sort");
            // populating the series with data
            series1.getData().add(new XYChart.Data<Number, Number>(0, 0));
            series1.getData().add(new XYChart.Data<Number, Number>(this.size, this.arr[0]));
            series1.getData().add(new XYChart.Data<Number, Number>(this.size/2, this.arr[0]/2));

            XYChart.Series<Number, Number> series2 = new XYChart.Series<Number, Number>();
            series2.setName("Insertion Sort");
            // populating the series with data
            series2.getData().add(new XYChart.Data<Number, Number>(0, 0));
            series2.getData().add(new XYChart.Data<Number, Number>(this.size, this.arr[1]));
            series2.getData().add(new XYChart.Data<Number, Number>(this.size/2, this.arr[1]/2));

            XYChart.Series<Number, Number> series3 = new XYChart.Series<Number, Number>();
            series3.setName("Selection Sort");
            // populating the series with data
            series3.getData().add(new XYChart.Data<Number, Number>(0, 0));
            series3.getData().add(new XYChart.Data<Number, Number>(this.size, this.arr[2]));
            series3.getData().add(new XYChart.Data<Number, Number>(this.size/2, this.arr[2]/ 2));

            XYChart.Series<Number, Number> series4 = new XYChart.Series<Number, Number>();
            series4.setName("Quick Sort");
            // populating the series with data
            series4.getData().add(new XYChart.Data<Number, Number>(0, 0));
            series4.getData().add(new XYChart.Data<Number, Number>(this.size, this.arr[3]));
            series4.getData().add(new XYChart.Data<Number, Number>(this.size/2, this.arr[3]/2));


            XYChart.Series<Number, Number> series5 = new XYChart.Series<Number, Number>();
            series5.setName("Merge Sort");
            // populating the series with data
            series5.getData().add(new XYChart.Data<Number, Number>(0, 0));
            series5.getData().add(new XYChart.Data<Number, Number>(this.size, this.arr[4]));
            series5.getData().add(new XYChart.Data<Number, Number>(this.size/2, this.arr[4]/2));

            XYChart.Series<Number, Number> series6 = new XYChart.Series<Number, Number>();
            series6.setName("Counting Sort");
            // populating the series with data
            series6.getData().add(new XYChart.Data<Number, Number>(0, 0));
            series6.getData().add(new XYChart.Data<Number, Number>(this.size, this.arr[5]));
            series6.getData().add(new XYChart.Data<Number, Number>(this.size/2, this.arr[5]/2));

            XYChart.Series<Number, Number> series7 = new XYChart.Series<Number, Number>();
            series7.setName("Radix Sort");
            // populating the series with data
            series7.getData().add(new XYChart.Data<Number, Number>(0, 0));
            series7.getData().add(new XYChart.Data<Number, Number>(this.size, this.arr[6]));
            series7.getData().add(new XYChart.Data<Number, Number>(this.size/2, this.arr[6]/2));


            Scene scene = new Scene(lineChart, 800, 600);
            lineChart.getData().addAll(series1, series2, series3, series4, series5, series6, series7);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }


}
