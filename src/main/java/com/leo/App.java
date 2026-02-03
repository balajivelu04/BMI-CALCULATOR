package com.leo;

import javax.swing.JOptionPane;

public class App {
    public static void main(String[] args) {
        try {
            String weightStr = JOptionPane.showInputDialog(null, "Enter weight in kilograms:", "BMI Calculator", JOptionPane.QUESTION_MESSAGE);
            if (weightStr == null) return; // Cancelled
            double weight = Double.parseDouble(weightStr);

            String heightStr = JOptionPane.showInputDialog(null, "Enter height in meters:", "BMI Calculator", JOptionPane.QUESTION_MESSAGE);
            if (heightStr == null) return; // Cancelled
            double height = Double.parseDouble(heightStr);

            double bmi = weight / (height * height);

            String status;
            if (bmi < 18.5) {
                status = "Underweight";
            } else if (bmi < 25) {
                status = "Normal";
            } else if (bmi < 30) {
                status = "Overweight";
            } else {
                status = "Obese";
            }

            JOptionPane.showMessageDialog(null, "BMI: " + String.format("%.2f", bmi) + "\nHealth status: " + status, "BMI Result", JOptionPane.INFORMATION_MESSAGE);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Please enter valid numbers.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}