package com.eden.test;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.BubbleChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class SampleTest1 extends Application{


    private void init(Stage primaryStage) {

        Group root = new Group();

        primaryStage.setScene(new Scene(root));

        root.getChildren().add(createChart()); 

    }

 

    protected BubbleChart<Number, Number> createChart() {

        final NumberAxis xAxis = new NumberAxis();

        final NumberAxis yAxis = new NumberAxis();

        final BubbleChart<Number,Number> bc = new BubbleChart<Number,Number>(xAxis,yAxis);

        // setup chart

        bc.setTitle("Advanced BubbleChart");

        xAxis.setLabel("X Axis");

        yAxis.setLabel("Y Axis");

        // add starting data

        XYChart.Series<Number,Number> series1 = new XYChart.Series<Number,Number>();

        series1.setName("Data Series 1");

        for (int i=0; i<20; i++) series1.getData().add(

                new XYChart.Data<Number,Number>(Math.random()*100, Math.random()*100, (Math.random()*10)));

        XYChart.Series<Number,Number> series2 = new XYChart.Series<Number,Number>();

        series2.setName("Data Series 2");

        for (int i=0; i<20; i++) series2.getData().add(

                new XYChart.Data<Number,Number>(Math.random()*100, Math.random()*100, (Math.random()*10)));

        bc.getData().addAll(series1, series2);

        return bc;

    }

 

    @Override public void start(Stage primaryStage) throws Exception {

        init(primaryStage);

        primaryStage.show();

    }

    public static void main(String[] args) { launch(args); }
}
