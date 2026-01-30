package com.leo;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter weight in kilograms: ");
        double weight = sc.nextDouble();

        System.out.print("Enter height in meters: ");
        double height = sc.nextDouble();

        double bmi = weight / (height * height);

        System.out.println("BMI: " + bmi);

        if (bmi < 18.5) {
            System.out.println("Health status: Underweight");
        } else if (bmi < 25) {
            System.out.println("Health status: Normal");
        } else if (bmi < 30) {
            System.out.println("Health status: Overweight");
        } else {
            System.out.println("Health status: Obese");
        }

        sc.close();
    }
}