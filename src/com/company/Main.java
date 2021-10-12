package com.company;

import java.util.Scanner;

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
