package com.leo;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BMIApp extends Application {

    @Override
    public void start(Stage stage) {

        TextField weightField = new TextField();
        weightField.setPromptText("Enter weight (kg)");

        TextField heightField = new TextField();
        heightField.setPromptText("Enter height (meters)");

        Button btn = new Button("Calculate BMI");

        Label result = new Label();

        btn.setOnAction(e -> {
            double weight = Double.parseDouble(weightField.getText());
            double height = Double.parseDouble(heightField.getText());
            double bmi = weight / (height * height);

            String status;
            if (bmi < 18.5) status = "Underweight";
            else if (bmi < 25) status = "Normal";
            else if (bmi < 30) status = "Overweight";
            else status = "Obese";

            result.setText("BMI: " + String.format("%.2f", bmi) + "\nStatus: " + status);
        });

        VBox layout = new VBox(10, weightField, heightField, btn, result);
        Scene scene = new Scene(layout, 300, 250);

        stage.setTitle("BMI Calculator");
        stage.setScene(scene);
        stage.show();
    }
}
