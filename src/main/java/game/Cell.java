package game;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Cell class for all methods associated with altering and creating a cell
 *
 * @author japhialoo-modified
 */
public class Cell {
    /**
     * Shape of the cell
     */
    private final Rectangle rectangle;
    /**
     * Root of the cell
     */
    private final Group root;
    /**
     * Text to be displayed in the cell
     */
    private Text textClass;
    /**
     * Checks if a cell has been modified
     */
    private boolean modify = false;

    /**
     * Takes in x and Y values to set the position of the cell
     * Scale determines the size of the cell
     * @param x sets the scale for the x-axis of the cell
     * @param y sets the scale for the y-axis of the cell
     * @param scale scales the x-axis and y-axis accordingly
     * @param root The root node that will inherit the elements to be rendered
     */
    Cell(double x, double y, double scale, Group root) {
        rectangle = new Rectangle();
        rectangle.setX(x);
        rectangle.setY(y);
        rectangle.setHeight(scale);
        rectangle.setWidth(scale);
        this.root = root;
        rectangle.setFill(Color.rgb(224, 226, 226, 0.5));
        this.textClass = TextMaker.getSingleInstance().madeText("0", x, y);
        root.getChildren().add(rectangle);
    }

    /**
     * Takes in a boolean value and assigns it to this cell's modify value
     * to determine if this cell has merged with another cell
     * @param modify Boolean value for if the cell was modified during an action
     */
    public void setModify(boolean modify) {
        this.modify = modify;
    }

    /**
     * Gets the boolean value to determine if the current cell was previously merged during a single move.
     * @return Boolean value to determine if the cell has been modified
     */
    public boolean getModify() {
        return !modify;
    }

    /**
     * Takes the text value to be displayed on the cell and assigns it to the current cell.
     * @param textClass text to be displayed in this cell
     */
    public void setTextClass(Text textClass) {
        this.textClass = textClass;
    }

    /**
     * Takes in the destination cell of the current cell and swaps their content to simulate cells moving on the board.
     * 0 values will not be shown on the board.
     * @param cell Destination of current cell
     */
    public void changeCell(Cell cell) {
        TextMaker.changeTwoText(textClass, cell.getTextClass());
        root.getChildren().remove(cell.getTextClass());
        root.getChildren().remove(textClass);

        if (!cell.getTextClass().getText().equals("0")) {
            root.getChildren().add(cell.getTextClass());
        }
        if (!textClass.getText().equals("0")) {
            root.getChildren().add(textClass);
        }
        setColorByNumber(getNumber());
        cell.setColorByNumber(cell.getNumber());
    }

    /**
     * Takes in the cell that is supposed to merge with the current cell and adds their values together and displays them on the cell
     * then setting the value of the current cell to 0 and removing 0 from the view.
     * @param cell cell to be merged with current cell
     */
    public void adder(Cell cell) {
        cell.getTextClass().setText((cell.getNumber() + this.getNumber()) + "");
        textClass.setText("0");
        root.getChildren().remove(textClass);
        cell.setColorByNumber(cell.getNumber());
        setColorByNumber(getNumber());
    }

    /**
     * Takes in the value that is displayed in the current cell and changed the color of the cell accordingly.
     * @param number Number in the cell
     *               if number exceeds 2048, color of the cell will turn black
     */
    public void setColorByNumber(int number) {
        switch (number) {
            case 0 -> rectangle.setFill(Color.rgb(224, 226, 226, 0.5));
            case 2 -> rectangle.setFill(Color.rgb(232, 255, 100, 0.5));
            case 4 -> rectangle.setFill(Color.rgb(232, 220, 50, 0.5));
            case 8 -> rectangle.setFill(Color.rgb(232, 200, 44, 0.8));
            case 16 -> rectangle.setFill(Color.rgb(232, 170, 44, 0.8));
            case 32 -> rectangle.setFill(Color.rgb(180, 120, 44, 0.7));
            case 64 -> rectangle.setFill(Color.rgb(180, 100, 44, 0.7));
            case 128 -> rectangle.setFill(Color.rgb(180, 80, 44, 0.7));
            case 256 -> rectangle.setFill(Color.rgb(180, 60, 44, 0.8));
            case 512 -> rectangle.setFill(Color.rgb(180, 30, 44, 0.8));
            case 1024 -> rectangle.setFill(Color.rgb(250, 0, 44, 0.8));
            case 2048 -> rectangle.setFill(Color.rgb(250, 0, 0, 1));
            default -> rectangle.setFill(Color.rgb(0, 0, 0, 1));
        }

    }

    /**
     * Gets width of rectangle
     * @return X-axis size of the cell
     */
    public double getX() {
        return rectangle.getX();
    }

    /**
     * Gets height of rectangle
     * @return Y-axis size of the cell
     */
    public double getY() {
        return rectangle.getY();
    }

    /**
     * Gets number in cell
     * @return Int value of the number in the cell
     */
    public int getNumber() {
        return Integer.parseInt(textClass.getText());
    }

    /**
     * Gets Text in cell
     * @return Text value of current cell
     */
    private Text getTextClass() {
        return textClass;
    }

}
