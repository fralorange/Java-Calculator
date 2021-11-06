package com.example.javacalculator;

import java.util.Stack;

public class Evaluator {
    public static String EvaluateExpressionToRPN(String expression) {
        StringBuilder CurrentLine = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        int p;
        for (int i = 0; i < expression.length(); i++) {
            p = getPriority(expression.charAt(i));
            switch (p) {
                case 0 -> CurrentLine.append(expression.charAt(i));
                case 1 -> stack.push(expression.charAt(i));
                case -1 -> {
                    CurrentLine.append(' ');
                    while (getPriority(stack.peek()) != 1) {
                        CurrentLine.append(stack.pop());
                    }
                    stack.pop();
                }
                case 4 -> {
                    stack.push(expression.charAt(i));
                }
                default -> {
                    CurrentLine.append(' ');
                    while (!stack.empty()) {
                        if (getPriority(stack.peek()) >= p) {
                            CurrentLine.append(stack.pop());
                        } else break;
                    }
                    stack.push(expression.charAt(i));
                }
            }
        }
        while (!stack.empty()) {
            CurrentLine.append(' ').append(stack.pop());
        }
        return CurrentLine.toString();
    }

    public static boolean isNumber(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isBinaryOperator(String s) {
        return (getPriority(s.charAt(0)) >= 1 && getPriority(s.charAt(0)) < 4);
    }

    private static int getPriority(char p) {
        switch (p) {
            case '~' -> {
                return 4;
            }
            case '×', '÷' -> {
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