package com.example.javacalculator;

import java.util.Stack;

public class Evaluator {
    public static String EvuluateExpressionToRPN(String expression) {
        String CurrentLine = "";
        Stack<Character> stack = new Stack<>();

        int p;
        for (int i = 0; i < expression.length(); i++) {
            p = getPriority(expression.charAt(i));
            switch (p) {
                case 0 -> CurrentLine += expression.charAt(i);
                case 1 -> stack.push(expression.charAt(i));
                case -1 -> {
                    while (getPriority(stack.peek()) != 1) {
                        CurrentLine += stack.pop();
                    }
                    stack.pop();
                }
                default -> {
                    CurrentLine += " ";
                    while (!stack.empty()) {
                        if (getPriority(stack.peek()) >= p) {
                            CurrentLine += stack.pop();
                        } else break;
                    }
                    stack.push(expression.charAt(i));
                }
            }
        }
        while (!stack.empty()) {
            CurrentLine += stack.pop();
        }
        return CurrentLine;
    }

    private static int getPriority(char p) {
        switch (p) {
            case '*', '/' -> {
                return 3;
            }
            case '+', '-' -> {
                return 2;
            }
            case '(' -> {
                return 1;
            }
            case ')' -> {
                return -1;
            }
            default -> {
                return 0;
            }
        }
    }
}