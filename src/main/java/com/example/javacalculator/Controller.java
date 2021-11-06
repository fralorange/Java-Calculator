package com.example.javacalculator;

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
import java.util.Stack;

import ch.obermuhlner.math.big.BigDecimalMath;

public class Controller {
    boolean addBool = false;
    boolean subBool = false;
    boolean divBool = false;
    boolean mulBool = false;
    boolean powerBool = false;
    boolean baserootBool = false;
    boolean operand2 = false;
    boolean modBool = false;
    boolean FloatingPoint = false;
    boolean SecondFunc = false;
    boolean newLine = true;
    double x, y;
    BigDecimal operand;
    BigDecimal result = BigDecimal.ZERO;

    public static double EvaluatedResult(String MathExpression) {
        String[] strings = MathExpression.split(" ");
        Stack<Double> stack = new Stack<>();

        for (String string : strings) {
            if (Evaluator.isNumber(string)) {
                stack.push(Double.parseDouble(string));
            } else if(!string.isBlank()) {
                double tmp1 = stack.pop();
                double tmp2 = stack.pop();
                switch (string) {
                    case "+" -> stack.push(BigDecimal.valueOf(tmp1).add(BigDecimal.valueOf(tmp2)).doubleValue());
                    case "-" -> stack.push(BigDecimal.valueOf(tmp2).subtract(BigDecimal.valueOf(tmp1)).doubleValue());
                    case "×" -> stack.push(BigDecimal.valueOf(tmp1).multiply(BigDecimal.valueOf(tmp2)).doubleValue());
                    case "÷" -> stack.push(BigDecimal.valueOf(tmp2).divide(BigDecimal.valueOf(tmp1), 31, RoundingMode.HALF_UP).doubleValue());
                }
            }
        }
        if (!stack.empty()) {
            return stack.pop();
        } else throw new ArithmeticException("Stack error...");
    }


    @FXML
    private Button ate;

    @FXML
    private Label digitValueString;
    String buttonDigitValue = "0";

    @FXML
    private Label prevDigitValueString;
    String prevDigitValue = "";

    @FXML
    private Button dot;

    @FXML
    private Button five;

    @FXML
    private Button four;

    @FXML
    private Button nine;

    @FXML
    private Button one;

    @FXML
    private Button plusminus;

    @FXML
    private Button seven;

    @FXML
    private Button six;

    @FXML
    private Button three;

    @FXML
    private Button two;

    @FXML
    private Button zero;

    @FXML
    private Button CE;

    @FXML
    private Button C;

    @FXML
    private Button operator_divide;

    @FXML
    private Button operator_minus;

    @FXML
    private Button operator_multiply;

    @FXML
    private Button operator_plus;

    @FXML
    private Button equals;

    @FXML
    private AnchorPane mainSec;

    @FXML
    private Button SecF;

    @FXML
    private Button SecFend;

    @FXML
    private Button equals2;

    @FXML
    private Button ybaseroot;

    @FXML
    private Button squareroot;

    @FXML
    private Button square;

    @FXML
    private Button power;

    @FXML
    private Button mod;

    @FXML
    private Button pi;

    @FXML
    private Button exponent;

    @FXML
    private Button fact;

    @FXML
    private Button leftbr;

    @FXML
    private Button rightbr;


    private void onButtonClick(ActionEvent event) {
        if (Character.toString(digitValueString.getText().charAt(0)).equals("0") && (((Button) event.getSource()).getText().equals("0")) && !FloatingPoint)
            return;
        if (buttonDigitValue.equals("0")) {
            buttonDigitValue = "";
        }
        if (newLine) {
            prevDigitValueString.setText("");
            buttonDigitValue = "";
            result = BigDecimal.ZERO;
            operand = BigDecimal.ZERO;
            newLine = false;
        }
        if (operand2) {
            buttonDigitValue = "";
            FloatingPoint = false;
            operand2 = false;
        }
        buttonDigitValue += ((Button) event.getSource()).getText();
        digitValueString.setText(buttonDigitValue);
    }

    private void onCEClick(ActionEvent event) {
        FloatingPoint = false;
        buttonDigitValue = "0";
        result = BigDecimal.ZERO;
        digitValueString.setText(buttonDigitValue);
        if (prevDigitValueString.getText().contains("=")) {
            prevDigitValueString.setText("");
        }
    }

    private void onCClick(ActionEvent event) {
        buttonDigitValue = "0";
        addBool = false;
        subBool = false;
        divBool = false;
        mulBool = false;
        powerBool = false;
        baserootBool = false;
        modBool = false;
        operand2 = false;
        FloatingPoint = false;
        newLine = false;
        prevDigitValueString.setText("");
        operand = BigDecimal.ZERO;
        result = BigDecimal.ZERO;
        digitValueString.setText(buttonDigitValue);
    }


    private void onEqualsClick(ActionEvent event) {
        if (addBool) {
            result = operand.add(BigDecimal.valueOf(Double.parseDouble(buttonDigitValue)));
        } else if (subBool) {
            result = operand.subtract(BigDecimal.valueOf(Double.parseDouble(buttonDigitValue)));
        } else if (mulBool) {
            result = operand.multiply(BigDecimal.valueOf(Double.parseDouble(buttonDigitValue)));
        } else if (divBool) {
            result = operand.divide(BigDecimal.valueOf(Double.parseDouble(buttonDigitValue)), 31, RoundingMode.HALF_UP);
        } else if (powerBool) {
            result = BigDecimalMath.pow(operand, BigDecimal.valueOf(Double.parseDouble(buttonDigitValue)), new MathContext(31));
        } else if (baserootBool) {
            result = BigDecimalMath.pow(operand, BigDecimal.valueOf(1).divide(BigDecimal.valueOf(Double.parseDouble(buttonDigitValue))), new MathContext(31));
        } else if (modBool) {
            result = operand.remainder(BigDecimal.valueOf(Double.parseDouble(buttonDigitValue)));
        }
        prevDigitValue += buttonDigitValue + "=";
        prevDigitValueString.setText(prevDigitValue);
        BigDecimal resStripped = result.stripTrailingZeros();
        if (resStripped.toPlainString().length() > 31) {
            digitValueString.setText(resStripped.toEngineeringString());
        } else {
            digitValueString.setText(resStripped.toPlainString());
        }
        buttonDigitValue = "0";
        newLine = true;
        FloatingPoint = false;
    }

    private void negate(ActionEvent event) {
        buttonDigitValue = ((!digitValueString.getText().equals("")) && (!digitValueString.getText().equals("0"))) ? BigDecimal.valueOf(Double.parseDouble(digitValueString.getText())).negate().stripTrailingZeros().toPlainString() : "0";
        digitValueString.setText(buttonDigitValue);
        result = BigDecimal.ZERO;
    }

    private void OnDotClick(ActionEvent event) {
        if (!FloatingPoint) {
            buttonDigitValue += (!buttonDigitValue.equals("")) ? "." : "0.";
            digitValueString.setText(buttonDigitValue);
            FloatingPoint = true;
        }
    }

    private void OnSecondFunctionClick(ActionEvent event) {
        Duration dur = Duration.seconds(0.35);
        TranslateTransition trans = new TranslateTransition();
        FadeTransition fade = new FadeTransition();
        trans.setDuration(dur);
        fade.setDuration(dur);
        trans.setNode(mainSec);
        fade.setNode(mainSec);
        if (SecondFunc) {
            trans.setToY(0);
            fade.setToValue(0);
        } else {
            trans.setToY(-185);
            fade.setToValue(1.0);
        }
        trans.play();
        fade.play();
        SecondFunc = !SecondFunc;
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

    private void OnOperator(ActionEvent event) {
        addBool = false;
        subBool = false;
        divBool = false;
        mulBool = false;
        powerBool = false;
        baserootBool = false;
        modBool = false;
        //
        newLine = false;
        //
        String s = ((Button) event.getSource()).getText();
        operand = (result.doubleValue() != 0) ? result : BigDecimal.valueOf(Double.parseDouble(buttonDigitValue));
        switch (s) {
            case "+" -> {
                addBool = true;
                prevDigitValue = operand.stripTrailingZeros().toPlainString() + "+";
            }
            case "-" -> {
                subBool = true;
                prevDigitValue = operand.stripTrailingZeros().toPlainString() + "-";
            }
            case "×" -> {
                mulBool = true;
                prevDigitValue = operand.stripTrailingZeros().toPlainString() + "×";
            }
            case "÷" -> {
                divBool = true;
                prevDigitValue = operand.stripTrailingZeros().toPlainString() + "÷";
            }
            case "𝑥ʸ" -> {
                powerBool = true;
                prevDigitValue = operand.stripTrailingZeros().toPlainString() + "^";
            }
            case "√x" -> {
                baserootBool = true;
                prevDigitValue = operand.stripTrailingZeros().toPlainString() + "ybaseroot";
            }
            case "mod" -> {
                modBool = true;
                prevDigitValue = operand.stripTrailingZeros().toPlainString() + "mod";
            }
        }
        prevDigitValueString.setText(prevDigitValue);
        operand2 = true;
    }


    @FXML
    void initialize() {
        System.out.println("~5+10×4÷~2");
        System.out.println(Evaluator.EvaluateExpressionToRPN("(22+8)×5"));
        System.out.println(EvaluatedResult(Evaluator.EvaluateExpressionToRPN("(22+8)×5")));
        one.setOnAction(this::onButtonClick);
        two.setOnAction(this::onButtonClick);
        three.setOnAction(this::onButtonClick);
        four.setOnAction(this::onButtonClick);
        five.setOnAction(this::onButtonClick);
        six.setOnAction(this::onButtonClick);
        seven.setOnAction(this::onButtonClick);
        ate.setOnAction(this::onButtonClick);
        nine.setOnAction(this::onButtonClick);
        zero.setOnAction(this::onButtonClick);
        CE.setOnAction(this::onCEClick);
        C.setOnAction(this::onCClick);
        operator_plus.setOnAction(this::OnOperator);
        operator_minus.setOnAction(this::OnOperator);
        operator_multiply.setOnAction(this::OnOperator);
        operator_divide.setOnAction(this::OnOperator);
        power.setOnAction(this::OnOperator);
        mod.setOnAction(this::OnOperator);
        equals.setOnAction(this::onEqualsClick);
        equals2.setOnAction(this::onEqualsClick);
        plusminus.setOnAction(this::negate);
        dot.setOnAction(this::OnDotClick);
        SecF.setOnAction(this::OnSecondFunctionClick);
        SecFend.setOnAction(this::OnSecondFunctionClick);
        fact.setOnAction(event -> {
            if (digitValueString.getText().equals("0")) {
                buttonDigitValue = "1";
            } else {
                buttonDigitValue = digitValueString.getText();
                BigDecimal FactRes = BigDecimal.ONE;
                for (int factor = 2; factor <= Integer.parseInt(buttonDigitValue); factor++) {
                    FactRes = FactRes.multiply(BigDecimal.valueOf(factor));
                }
                buttonDigitValue = FactRes.stripTrailingZeros().toPlainString();
            }
            digitValueString.setText(buttonDigitValue);
            prevDigitValue = String.format("fact(%s)", digitValueString.getText());
            prevDigitValueString.setText(prevDigitValue);
            result = BigDecimal.ZERO;
        });
        square.setOnAction(event -> {
            buttonDigitValue = BigDecimal.valueOf(Double.parseDouble(digitValueString.getText())).pow(2).stripTrailingZeros().toPlainString();
            digitValueString.setText(buttonDigitValue);
            prevDigitValue = String.format("sqr(%s)", digitValueString.getText());
            prevDigitValueString.setText(prevDigitValue);
            result = BigDecimal.ZERO;
        });
        ybaseroot.setOnAction(this::OnOperator);
        squareroot.setOnAction(event -> {
            buttonDigitValue = BigDecimal.valueOf(Double.parseDouble(digitValueString.getText())).sqrt(new MathContext(31)).stripTrailingZeros().toPlainString();
            prevDigitValue = String.format("sqrt(%s)", digitValueString.getText());
            digitValueString.setText(buttonDigitValue);
            prevDigitValueString.setText(prevDigitValue);
            result = BigDecimal.valueOf(0);
        });
        pi.setOnAction(event -> {
            buttonDigitValue = String.valueOf(Math.PI);
            digitValueString.setText(buttonDigitValue);
            newLine = true;
            result = BigDecimal.ZERO;
        });
        exponent.setOnAction(event -> {
            buttonDigitValue = String.valueOf(Math.E);
            digitValueString.setText(buttonDigitValue);
            newLine = true;
            result = BigDecimal.ZERO;
        });
    }

}
