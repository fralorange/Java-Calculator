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

public class Controller {
    boolean addBool = false;
    boolean subBool = false;
    boolean divBool = false;
    boolean mulBool = false;
    boolean operand2 = false;
    boolean FloatingPoint = false;
    boolean SecondFunc = false;
    BigDecimal operand = new BigDecimal(0.0);
    BigDecimal result = new BigDecimal(0.0);

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
        if (buttonDigitValue.equals("0")) buttonDigitValue = "";
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
        buttonDigitValue = "";
        result = BigDecimal.valueOf(0.0);
        digitValueString.setText("0");
    }

    private void onCClick(ActionEvent event) {
        buttonDigitValue = "";
        addBool = false;
        subBool = false;
        divBool = false;
        mulBool = false;
        operand2 = false;
        FloatingPoint = false;
        operand = BigDecimal.valueOf(0.0);
        result = BigDecimal.valueOf(0.0);
        digitValueString.setText("0");


    }


    private void onEqualsClick(ActionEvent event) {
        if (addBool) {
            result = operand.add(BigDecimal.valueOf(Double.parseDouble(buttonDigitValue)));
        } else if (subBool) {
            result = operand.subtract(BigDecimal.valueOf(Double.parseDouble(buttonDigitValue)));
        } else if (mulBool) {
            result = operand.multiply(BigDecimal.valueOf(Double.parseDouble(buttonDigitValue)));
        } else if (divBool) {
            result = operand.divide(BigDecimal.valueOf(Double.parseDouble(buttonDigitValue)));
        }
        digitValueString.setText((result.stripTrailingZeros()).toPlainString());
        buttonDigitValue = "";
        FloatingPoint = false;
    }

    private void negate(ActionEvent event) {
        buttonDigitValue = ((!digitValueString.getText().equals("")) && (!digitValueString.getText().equals("0")) && (!digitValueString.getText().equals("0.0"))) ? String.valueOf(Double.parseDouble(digitValueString.getText()) * -1) : "0";
        digitValueString.setText(buttonDigitValue);
        result = BigDecimal.valueOf(0.0);
    }

    private void OnDotClick(ActionEvent event) {
        if (!FloatingPoint) {
            buttonDigitValue += (!buttonDigitValue.equals("")) ? "." : "0.";
            digitValueString.setText(buttonDigitValue);
            FloatingPoint = true;
        }
    }

    private void OnSecondFunctionClick(ActionEvent event) {
        Duration dur = Duration.seconds(0.4);
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

    double x,y;

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
            operand = (result.doubleValue() != 0) ? result : BigDecimal.valueOf(Double.parseDouble(buttonDigitValue));
            operand2 = true;
        });
        operator_minus.setOnAction(event -> {
            addBool = false;
            subBool = true;
            mulBool = false;
            divBool = false;
            operand = (result.doubleValue() != 0) ? result : BigDecimal.valueOf(Double.parseDouble(buttonDigitValue));
            operand2 = true;
        });
        operator_multiply.setOnAction(event -> {
            addBool = false;
            subBool = false;
            mulBool = true;
            divBool = false;
            operand = (result.doubleValue() != 0) ? result : BigDecimal.valueOf(Double.parseDouble(buttonDigitValue));
            operand2 = true;
        });
        operator_divide.setOnAction(event -> {
            addBool = false;
            subBool = false;
            mulBool = false;
            divBool = true;
            operand = (result.doubleValue() != 0) ? result : BigDecimal.valueOf(Double.parseDouble(buttonDigitValue));
            operand2 = true;
        });
        equals.setOnAction(this::onEqualsClick);
        plusminus.setOnAction(this::negate);
        dot.setOnAction(this::OnDotClick);
        SecF.setOnAction(this::OnSecondFunctionClick);
        SecFend.setOnAction(this::OnSecondFunctionClick);
        equals2.setOnAction(this::onEqualsClick);
        fact.setOnAction(event -> {
            if (digitValueString.getText().equals("0")) {
                buttonDigitValue = "1";
            } else {
                buttonDigitValue = digitValueString.getText();
                result = BigDecimal.valueOf(1);
                for (int factor = 2; factor <= Integer.parseInt(buttonDigitValue); factor++) {
                    result = result.multiply(BigDecimal.valueOf(factor));
                }
                buttonDigitValue = result.stripTrailingZeros().toPlainString();
            }
            digitValueString.setText(buttonDigitValue);
            buttonDigitValue = "";
        });
    }

}
