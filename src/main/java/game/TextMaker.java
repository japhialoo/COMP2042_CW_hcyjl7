package game;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Class that manages text attribute of values in a cell.
 */
class TextMaker {
    /**
     * Single Instance of TextMaker class.
     */
    private static TextMaker singleInstance = null;


    /**
     * Method to get a single instance of the TextMaker class
     * @return Instance of TextMaker class
     */
    static TextMaker getSingleInstance() {
        if (singleInstance == null)
            singleInstance = new TextMaker();
        return singleInstance;
    }

    /**
     * Method to make and set style of the text in the game.
     * @param input Value to be displayed on the cell.
     * @param xCell x-axis length of the cell.
     * @param yCell y-axis length of the cell.
     * @return Displays the input value on the cell.
     */
    Text madeText(String input, double xCell, double yCell) {
        double fontSize = (3 * GameScene.LENGTH) / 8.0;
        Text text = new Text(input);
        text.setFont(Font.font(fontSize));
        text.relocate((xCell + (0.3)* GameScene.LENGTH / 7.0), (yCell + 2 * GameScene.LENGTH / 7.0));
        text.setFill(Color.WHITE);

        return text;
    }


    /**
     * Swapping values of the first and second cell.
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
