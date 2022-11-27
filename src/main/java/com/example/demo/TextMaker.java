package com.example.demo;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * I'm not too sure what's the purpose of this class other than to get text and set font sizes
 */
class TextMaker {
    private static TextMaker singleInstance = null;
    private double length = GameScene.getLENGTH();
    private double fontSize = (3 * length) / 7.0;

    private TextMaker() {

    }

    static TextMaker getSingleInstance() {
        if (singleInstance == null)
            singleInstance = new TextMaker();
        return singleInstance;
    }

    /**
     *
     * @param input Value to be displayed on the cell.
     * @param xCell x-axis length of the cell.
     * @param yCell y-axis length of the cell.
     * @param root unsure what root is yet.
     * @return Displays the input value on the cell.
     */
    Text madeText(String input, double xCell, double yCell, Group root) {
        Text text = new Text(input);
        text.setFont(Font.font(fontSize));
        text.relocate((xCell + (1.2)* length / 7.0), (yCell + 2 * length / 7.0));
        text.setFill(Color.WHITE);

        return text;
    }

    /**
     * swapping the text of the first cell with the text of the second cell
     * @param first Text of first cell to be swapped
     * @param second Text of second cell to be swapped
     */
    static void changeTwoText(Text first, Text second) {
        String temp;
        temp = first.getText();
        first.setText(second.getText());
        second.setText(temp);

        double tempNumber;
        tempNumber = first.getX();
        first.setX(second.getX());
        second.setX(tempNumber);

        tempNumber = first.getY();
        first.setY(second.getY());
        second.setY(tempNumber);

    }

}
