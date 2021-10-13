package com.example.javacalculator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
//import java.util.Scanner;
import java.io.IOException;

class Calculator {
    public static double Add(double a, double b) {
        return a + b;
    }

    public static double Subtract(double a, double b) {
        return a - b;
    }

    public static double Multiply(double a, double b) {
        return a * b;
    }

    public static double Divide(double a, double b) {
        return a / b;
    }
}

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        // Max-min height and width
        stage.setMinHeight(725.0);
        stage.setMinWidth(430.0);
        stage.setMaxHeight(725.0);
        stage.setMaxWidth(430.0);
        //
        stage.setOpacity(0.985);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}





/*
public class Main {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        double a, b;
        char operator;
        System.out.print("Enter first number: ");
        a = s.nextDouble();
        System.out.println("Choose an operator : +, -, * or / :");
        operator = s.next().charAt(0);
        System.out.print("Enter second number: ");
        b = s.nextDouble();
        switch (operator) {
            case '+' -> System.out.println(Calculator.Add(a, b));
            case '-' -> System.out.println(Calculator.Subtract(a, b));
            case '*' -> System.out.println(Calculator.Multiply(a, b));
            case '/' -> System.out.println(Calculator.Divide(a, b));
            default -> System.out.println("Wrong operator!");
        }
    }
}
*/