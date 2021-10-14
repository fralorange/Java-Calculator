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
    double FinalValue = 0.0;


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
        buttonDigitValue += ((Button) event.getSource()).getText();
        digitValueString.setText(buttonDigitValue);
    }

    private void onCEClick(ActionEvent event) {
        buttonDigitValue = "";
        digitValueString.setText("0");
    }

    private void onCClick(ActionEvent event) {
        buttonDigitValue = "";
        digitValueString.setText("0");
    }

    private void onEqualsClick(ActionEvent event) {
        if (addBool) {
            digitValueString.setText(String.valueOf(FinalValue+Double.parseDouble(buttonDigitValue)));
        } else if (subBool) {
            digitValueString.setText(String.valueOf(FinalValue-Double.parseDouble(buttonDigitValue)));
        } else if (mulBool) {
            digitValueString.setText(String.valueOf(FinalValue*Double.parseDouble(buttonDigitValue)));
        } else if (divBool) {
            digitValueString.setText(String.valueOf(FinalValue/Double.parseDouble(buttonDigitValue)));
        }
        //buttonDigitValue = "";
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
        // TODO сделать чтобы можно было менять оператор без ошибки
        // TODO сделать чтобы можно было к результату обращаться с помощью операторов
        operator_plus.setOnAction(event -> {
            addBool = true;
            subBool = false;
            mulBool = false;
            divBool = false;
            FinalValue = Double.parseDouble(buttonDigitValue);
            prevDigitValue = buttonDigitValue;
            buttonDigitValue = "";
        });
        operator_minus.setOnAction(event -> {
            addBool = false;
            subBool = true;
            mulBool = false;
            divBool = false;
            FinalValue = Double.parseDouble(buttonDigitValue);
            prevDigitValue = buttonDigitValue;
            buttonDigitValue = "";
        });
        operator_multiply.setOnAction(event -> {
            addBool = false;
            subBool = false;
            mulBool = true;
            divBool = false;
            FinalValue = Double.parseDouble(buttonDigitValue);
            prevDigitValue = buttonDigitValue;
            buttonDigitValue = "";
        });
        operator_divide.setOnAction(event -> {
            addBool = false;
            subBool = false;
            mulBool = false;
            divBool = true;
            FinalValue = Double.parseDouble(buttonDigitValue);
            prevDigitValue = buttonDigitValue;
            buttonDigitValue = "";
        });
        equals.setOnAction(this::onEqualsClick);

    }

}
