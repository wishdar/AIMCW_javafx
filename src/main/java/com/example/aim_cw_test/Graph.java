package com.example.aim_cw_test;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;

public class Graph extends Application {
    @Override
    public void start(Stage stage) {

        Double[] solutions = {0.0,1.0,2.0,3.0,4.0}; //SA.getSolutions();
        Double[] temps = {0.0,1.0,2.0,3.0,4.0}; //SA.getTemps();


        //Defining the x-axis
        NumberAxis xAxis = new NumberAxis();
        xAxis.setLabel("Temperature");

        //Defining the y-axis
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Solution Total Distance");

        //Creating the line chart
        LineChart linechart = new LineChart(xAxis, yAxis);

        //Prepare XYChart.Series objects by setting data
        XYChart.Series series = new XYChart.Series();
        series.setName("fuck you dickhead");

        for(int i = 0; i<temps.length; i++) {
            series.getData().add(new XYChart.Data(temps[i], solutions[i]));
        }

        //Setting the data to Line chart
        linechart.getData().add(series);

        //Creating a Group object
        Group root = new Group(linechart);

        //Creating a scene object
        Scene scene = new Scene(root, 600, 400);

        //Setting title to the Stage
        stage.setTitle("Line Chart");

        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
    }
}

