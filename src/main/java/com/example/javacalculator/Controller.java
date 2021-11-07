package com.example.javacalculator;

import ch.obermuhlner.math.big.BigDecimalMath;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class Controller {
    private String resultedValueString = "";
    private String digitResultedValueString = "";
    private boolean SecondFuncMenu = false;
    private boolean FloatingPoint = false;
    private boolean NewLine = false;
    private double x, y;
    private int n = 0;


    private static double EvaluatedResult(String MathExpression) {
        String[] strings = MathExpression.split(" ");
        Stack<Double> stack = new Stack<>();
        MathContext precision = new MathContext(31);
        for (String string : strings) {
            if (Evaluator.isNumber(string)) {
                stack.push(Double.parseDouble(string));
            } else if (!string.isBlank()) {
                double tmp1 = stack.pop();
                if (Evaluator.isBinaryOperator(string)) {
                    double tmp2 = stack.pop();
                    switch (string) {
                        case "+" -> stack.push(BigDecimal.valueOf(tmp1).add(BigDecimal.valueOf(tmp2)).doubleValue());
                        case "-" -> stack.push(BigDecimal.valueOf(tmp2).subtract(BigDecimal.valueOf(tmp1)).doubleValue());
                        case "×" -> stack.push(BigDecimal.valueOf(tmp1).multiply(BigDecimal.valueOf(tmp2)).doubleValue());
                        case "÷" -> stack.push(BigDecimal.valueOf(tmp2).divide(BigDecimal.valueOf(tmp1), 31, RoundingMode.HALF_UP).doubleValue());
                        case "^" -> stack.push(BigDecimalMath.pow(BigDecimal.valueOf(tmp2), BigDecimal.valueOf(tmp1), precision).doubleValue());
                        case "y" -> stack.push(BigDecimalMath.pow(BigDecimal.valueOf(tmp2), BigDecimal.ONE.divide(BigDecimal.valueOf(tmp1), 31, RoundingMode.HALF_UP), precision).doubleValue());
                        case "m" -> stack.push(BigDecimal.valueOf(tmp2).remainder(BigDecimal.valueOf(tmp1)).doubleValue());
                    }
                } else {
                    switch (string) {
                        case "~" -> stack.push(BigDecimal.valueOf(-tmp1).doubleValue());
                        case "²" -> stack.push(BigDecimalMath.pow(BigDecimal.valueOf(tmp1), 2, precision).doubleValue());
                        case "√" -> stack.push(BigDecimalMath.sqrt(BigDecimal.valueOf(tmp1), precision).doubleValue());
                        case "!" -> stack.push(BigDecimalMath.factorial(BigDecimal.valueOf(tmp1), precision).doubleValue());
                    }
                }
            }
        }
        if (!stack.empty()) {
            return stack.pop();
        } else throw new ArithmeticException("Stack error...");
    }

    //FXML Variables

    @FXML
    private Label digitValueLabel,prevDigitValueLabel;

    private String digitValueString = "";
    private String prevDigitValueString = "";

    // Buttons

    @FXML
    private Button one, two, three, four, five, six, seven, ate, nine, zero;

    @FXML
    private Button operator_plus, operator_minus, operator_multiply, operator_divide, negate, power, mod;

    @FXML
    private Button ybaseroot, squareroot, square;

    @FXML
    private Button pi, exponent;

    @FXML
    private Button fact;

    @FXML
    private Button leftbr, rightbr;

    @FXML
    private Button dot;

    //UI BUTTONS

    @FXML
    private Button CE, C;

    @FXML
    private Button equals, equals2;

    @FXML
    private Button SecF, SecFend;

    //

    @FXML
    private AnchorPane mainSec;

    //

    private void ClearLine(ActionEvent event) {
        FloatingPoint = false;
        digitValueString = "";
        digitResultedValueString = "";
        digitValueLabel.setText("0");
    }

    private void ClearExpression(ActionEvent event) {
        resultedValueString = "";
        prevDigitValueString = "";
        prevDigitValueLabel.setText(prevDigitValueString);
        ClearLine(event);
    }

    private void onNumberClick(ActionEvent event) {
        if ((digitValueString.equals("0")) && (((Button) event.getSource()).getText().equals("0")) && !FloatingPoint)
            return;
        if (digitValueString.equals("0")) {
            digitValueString = "";
        }
        if (NewLine) {
            ClearExpression(event);
            NewLine = false;
        }
        digitResultedValueString += ((Button) event.getSource()).getText();
        digitValueString += ((Button) event.getSource()).getText();
        digitValueLabel.setText(digitValueString);
    }

    private void OnDotClick(ActionEvent event) {
        if (!FloatingPoint) {
            if (NewLine) {
                ClearExpression(event);
                NewLine = false;
            }
            digitResultedValueString += (!digitValueString.equals("")) ? "." : "0.";
            digitValueString += (!digitValueString.equals("")) ? "." : "0.";
            digitValueLabel.setText(digitValueString);
            FloatingPoint = true;
        }
    }

    private void GetMathPi(ActionEvent event) {
        NewLine = true;
        digitValueLabel.setText(String.valueOf(Math.PI));
    }

    private void GetMathE(ActionEvent event) {
        NewLine = true;
        digitValueLabel.setText(String.valueOf(Math.E));
    }

    private void Negate(ActionEvent event) {
        digitValueString = BigDecimal.valueOf(-Double.parseDouble(digitValueString)).stripTrailingZeros().toPlainString();
        digitResultedValueString = (digitValueString.charAt(0) == '-') ? "~" + digitValueString.replace("-", "") : digitValueString;
        digitValueLabel.setText(digitValueString);
    }

    private void OnOperatorClick(ActionEvent event) {
        String lastSymbol = (prevDigitValueString.length() > 1) ? prevDigitValueString.substring(prevDigitValueString.length() - 1) : "";
        String operator = ((Button) event.getSource()).getText();
        FloatingPoint = false;
        if (!digitValueString.isEmpty()) {
            ;
        } else if (Arrays.asList(new String[]{"+", "-", "×", "÷"}).contains(lastSymbol)) {
            prevDigitValueString = prevDigitValueString.substring(0, prevDigitValueString.length() - 1);
            resultedValueString = resultedValueString.substring(0, resultedValueString.length() - 1);
        }
        if (NewLine) {
            digitValueString = "";
            digitResultedValueString = "";
            if (digitValueLabel.getText().contains("E+")) {
                resultedValueString = BigDecimal.valueOf(Double.parseDouble(digitValueLabel.getText())).toPlainString();
                prevDigitValueString = String.format("(%s)", digitValueLabel.getText());
            } else {
                resultedValueString = digitValueLabel.getText();
                prevDigitValueString = digitValueLabel.getText();
            }
            FloatingPoint = false;
            NewLine = false;
        }
        prevDigitValueString += digitValueString;
        resultedValueString += digitResultedValueString;
        digitValueString = "";
        digitResultedValueString = "";
        // +,-,×,÷ stream to short the code
        List<String> BinaryOperators = Arrays.asList("+", "-", "×", "÷");
        BinaryOperators.stream().filter(x -> x.equals(operator)).forEach(o -> {
            prevDigitValueString += o;
            resultedValueString += o;
        });
        switch (operator) {
            case "𝑥ʸ" -> {
                prevDigitValueString += "^";
                resultedValueString += "^";
            }
            case "𝑥²" -> {
                if (lastSymbol.equals("²")) {
                    prevDigitValueString = String.format("(%s)", prevDigitValueString);
                }
                prevDigitValueString += "²";
                resultedValueString += "²";
            }
            case "√x" -> {  /// square root
                if (prevDigitValueString.charAt(0) == '√') {
                    prevDigitValueString = String.format("(%s)", prevDigitValueString);
                }
                prevDigitValueString = "√" + prevDigitValueString;
                resultedValueString = resultedValueString + "√";
            }
            case "√𝑥" -> {  /// y base root
                if (prevDigitValueString.charAt(0) == '√') {
                    prevDigitValueString = String.format("(%s)", prevDigitValueString);
                }
                prevDigitValueString = prevDigitValueString + "yroot";
                resultedValueString = resultedValueString + "y";
            }
            case "mod" -> {
                prevDigitValueString += "mod";
                resultedValueString += "m";
            }
            case "𝑛!" -> {
                prevDigitValueString += "!";
                resultedValueString += "!";
            }
        }
        ;
        prevDigitValueLabel.setText(prevDigitValueString);
    }

    private void onBracketsClick(ActionEvent event) {
        String bracket = ((Button) event.getSource()).getText();
        if (bracket.equals("(")) {
            if (NewLine) {
                ClearExpression(event);
                NewLine = false;
            }
            prevDigitValueString += "(";
            resultedValueString += "(";
            n += 1;
        } else if ((bracket.equals(")")) && (n > 0)) {
            prevDigitValueString += digitValueString + ")";
            resultedValueString += digitResultedValueString + ")";
            digitValueString = "";
            digitResultedValueString = "";
            n -= 1;
        }
        prevDigitValueLabel.setText(prevDigitValueString);
    }


    private void onEqualsClick(ActionEvent event) {
        resultedValueString += digitResultedValueString;
        String placeholder = BigDecimal.valueOf(EvaluatedResult(Evaluator.EvaluateExpressionToRPN(resultedValueString))).stripTrailingZeros().toPlainString();
        if (placeholder.length() > 21) {
            digitValueLabel.setText(BigDecimal.valueOf(Double.parseDouble(placeholder)).toEngineeringString());
        } else digitValueLabel.setText(placeholder);
        prevDigitValueString += digitValueString + "=";
        prevDigitValueLabel.setText(prevDigitValueString);
        NewLine = true;
    }


    // UI //

    private void OnSecondFunctionClick(ActionEvent event) {
        Duration dur = Duration.seconds(0.35);
        TranslateTransition trans = new TranslateTransition();
        FadeTransition fade = new FadeTransition();
        trans.setDuration(dur);
        fade.setDuration(dur);
        trans.setNode(mainSec);
        fade.setNode(mainSec);
        if (SecondFuncMenu) {
            trans.setToY(0);
            fade.setToValue(0);
        } else {
            trans.setToY(-185);
            fade.setToValue(1.0);
        }
        trans.play();
        fade.play();
        SecondFuncMenu = !SecondFuncMenu;
        mainSec.setDisable(!mainSec.isDisable());
    }

    @FXML
    private void Dragged(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX() - x);
        stage.setY(event.getScreenY() - y);
    }

    @FXML
    private void Pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    @FXML
    private void Close(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    private void Min(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }


    @FXML
    void initialize() {
        List<Button> numbers = Arrays.asList(one, two, three, four, five, six, seven, ate, nine, zero);
        List<Button> operators = Arrays.asList(operator_plus, operator_minus, operator_multiply, operator_divide, power, mod, square, squareroot, ybaseroot, fact);
        //
        numbers.forEach(number -> number.setOnAction(this::onNumberClick));
        operators.forEach(operator -> operator.setOnAction(this::OnOperatorClick));
        rightbr.setOnAction(this::onBracketsClick);
        leftbr.setOnAction(this::onBracketsClick);
        negate.setOnAction(this::Negate);
        dot.setOnAction(this::OnDotClick);
        pi.setOnAction(this::GetMathPi);
        exponent.setOnAction(this::GetMathE);
        // UI Buttons
        CE.setOnAction(this::ClearLine); // clear line
        C.setOnAction(this::ClearExpression); // clear whole string
        equals.setOnAction(this::onEqualsClick); // two identical buttons 1 // throws final string to RPM Evaluator
        equals2.setOnAction(this::onEqualsClick); // two identical buttons 2
        SecF.setOnAction(this::OnSecondFunctionClick);
        SecFend.setOnAction(this::OnSecondFunctionClick);
    }


}
