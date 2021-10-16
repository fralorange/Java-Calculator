package com.example.javacalculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class Controller {
    boolean addBool = false;
    boolean subBool = false;
    boolean divBool = false;
    boolean mulBool = false;
    boolean operand2 = false;
    double operand = 0.0;
    double result = 0.0;
    boolean FloatingPoint = false;

    @FXML
    private Button ate;

    @FXML
    private Label digitValueString;
    String buttonDigitValue = "";

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

    private void onButtonClick(ActionEvent event) {
        if (Character.toString(digitValueString.getText().charAt(0)).equals("0") && (((Button) event.getSource()).getText().equals("0")) && !FloatingPoint)
            return;
        if (buttonDigitValue == "0") buttonDigitValue = "";
        if (operand2) {
            buttonDigitValue = "";
            operand2 = false;
        }
        buttonDigitValue += ((Button) event.getSource()).getText();
        digitValueString.setText(buttonDigitValue);
    }

    private void onCEClick(ActionEvent event) {
        buttonDigitValue = "";
        result = 0.0;
        digitValueString.setText("0");
    }

    private void onCClick(ActionEvent event) {
        buttonDigitValue = "";
        addBool = false;
        subBool = false;
        divBool = false;
        mulBool = false;
        operand2 = false;
        operand = 0.0;
        result = 0.0;
        digitValueString.setText("0");
    }

    private void onEqualsClick(ActionEvent event) {
        if (addBool) {
            result = operand + Double.parseDouble(buttonDigitValue);
        } else if (subBool) {
            result = operand - Double.parseDouble(buttonDigitValue);
        } else if (mulBool) {
            result = operand * Double.parseDouble(buttonDigitValue);
        } else if (divBool) {
            result = operand / Double.parseDouble(buttonDigitValue);
        }
        digitValueString.setText(String.valueOf(result));
        buttonDigitValue = "";
    }

    private void negate(ActionEvent event) {
        buttonDigitValue = ((!digitValueString.getText().equals("")) && (!digitValueString.getText().equals("0"))) ? String.valueOf(Double.parseDouble(digitValueString.getText()) * -1) : "0";
        digitValueString.setText(buttonDigitValue);
        result = 0;
    }

    @FXML
    void initialize() {
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
        operator_plus.setOnAction(event -> {
            addBool = true;
            subBool = false;
            mulBool = false;
            divBool = false;
            operand = (result != 0) ? result : Double.parseDouble(buttonDigitValue);
            operand2 = true;
        });
        operator_minus.setOnAction(event -> {
            addBool = false;
            subBool = true;
            mulBool = false;
            divBool = false;
            operand = (result != 0) ? result : Double.parseDouble(buttonDigitValue);
            operand2 = true;
        });
        operator_multiply.setOnAction(event -> {
            addBool = false;
            subBool = false;
            mulBool = true;
            divBool = false;
            operand = (result != 0) ? result : Double.parseDouble(buttonDigitValue);
            operand2 = true;
        });
        operator_divide.setOnAction(event -> {
            addBool = false;
            subBool = false;
            mulBool = false;
            divBool = true;
            operand = (result != 0) ? result : Double.parseDouble(buttonDigitValue);
            operand2 = true;
        });
        equals.setOnAction(this::onEqualsClick);
        plusminus.setOnAction(this::negate);
    }

}
