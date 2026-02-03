package com.leo;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class BMIApp extends Application {

    @Override
    public void start(Stage stage) {

        // Root background
        BorderPane root = new BorderPane();
        root.setStyle("-fx-background-color: #121212;");

        // ===== LEFT PANEL (Inputs) =====
        VBox left = new VBox(15);
        left.setPrefWidth(260);
        left.setAlignment(Pos.CENTER);
        left.setStyle("-fx-background-color:#1e1e1e; -fx-padding:20;");

        Label title = new Label("BMI Calculator");
        title.setTextFill(Color.web("#00E5FF"));
        title.setFont(Font.font(20));

        TextField weight = createField("Weight (kg)");
        TextField height = createField("Height (feet)");

        Button btn = new Button("Calculate");
        btn.setStyle("-fx-background-color:#00c6ff; -fx-text-fill:black; -fx-font-size:14px;");

        left.getChildren().addAll(title, weight, height, btn);

        // ===== CENTER GAUGE =====
        Pane gaugePane = new Pane();
        gaugePane.setPrefSize(500, 400);

        Arc under = makeArc(150, Color.web("#d32f2f"), 180, 45);
        Arc normal = makeArc(150, Color.web("#2e7d32"), 135, 60);
        Arc over = makeArc(150, Color.web("#fbc02d"), 75, 45);
        Arc obese = makeArc(150, Color.web("#c62828"), 30, 30);

        Label bmiText = new Label("BMI: --");
        bmiText.setTextFill(Color.WHITE);
        bmiText.setFont(Font.font(24));
        bmiText.setLayoutX(220);
        bmiText.setLayoutY(220);

        Label categoryText = new Label("");
        categoryText.setTextFill(Color.web("#00E5FF"));
        categoryText.setFont(Font.font(16));

        // Arrow
        Line arrow = new Line(250, 250, 250, 130);
        arrow.setStroke(Color.WHITE);
        arrow.setStrokeWidth(4);

        Circle center = new Circle(250, 250, 6, Color.WHITE);

        btn.setOnAction(e -> {
            try {
                double w = Double.parseDouble(weight.getText());
                double hFeet = Double.parseDouble(height.getText());
                double h = hFeet * 0.3048;

                double bmi = w / (h * h);
                bmiText.setText(String.format("BMI = %.1f", bmi));

                String category;
                Color c;

                if (bmi < 18.5) {
                    category = "Underweight";
                    c = Color.web("#ff5252");
                }
                else if (bmi < 25) {
                    category = "Normal";
                    c = Color.web("#66bb6a");
                }
                else if (bmi < 30) {
                    category = "Overweight";
                    c = Color.web("#ffeb3b");
                }
                else {
                    category = "Obese";
                    c = Color.web("#d32f2f");
                }

                categoryText.setText(category);
                categoryText.setTextFill(c);

                double angle;
                if (bmi < 18.5)
                    angle = 180 - (bmi / 18.5) * 45;
                else if (bmi < 25)
                    angle = 135 - ((bmi - 18.5) / 6.5) * 60;
                else if (bmi < 30)
                    angle = 75 - ((bmi - 25) / 5) * 45;
                else
                    angle = 30;

                arrow.setRotate(angle);

            } catch (Exception ex) {
                bmiText.setText("Invalid input");
                categoryText.setText("");
            }
        });

        gaugePane.getChildren().addAll(under, normal, over, obese, arrow, center, bmiText, categoryText);
        categoryText.setLayoutX(220);
        categoryText.setLayoutY(260);

        root.setLeft(left);
        root.setCenter(gaugePane);

        stage.setScene(new Scene(root, 800, 450));
        stage.setTitle("BMI Calculator");
        stage.show();
    }

    private TextField createField(String text) {
        TextField tf = new TextField();
        tf.setPromptText(text);
        tf.setStyle("-fx-background-color:#263238; -fx-text-fill:white;");
        return tf;
    }

    private Arc makeArc(double radius, Color color, double start, double length) {
        Arc arc = new Arc(250, 250, radius, radius, start, length);
        arc.setType(ArcType.OPEN);
        arc.setStroke(color);
        arc.setStrokeWidth(22);
        arc.setFill(Color.TRANSPARENT);
        return arc;
    }

    public static void main(String[] args) {
        launch();
    }
}