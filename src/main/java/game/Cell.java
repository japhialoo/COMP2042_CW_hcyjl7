package game;


import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Cell class for all methods associated with altering and creating a cell.
 */
public class Cell {
    private final Rectangle rectangle;
    private final Group root;
    private Text textClass;
    private boolean modify = false;

    /**
     *
     * @param modify Boolean value for if the cell was modified during an action.
     */
    void setModify(boolean modify) {
        this.modify = modify;
    }

    /**
     *
     * @return Boolean value to determine if the cell has been modified.
     */
    boolean getModify() {
        return modify;
    }

    /**
     *
     * @param x sets the scale for the x-axis of the cell
     * @param y sets the scale for the y-axis of the cell
     * @param scale scales the x-axis and y-axis accordingly
     * @param root Unsure what is the root for
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
     * Sets textClass to input text.
     * @param textClass text to be displayed in this cell.
     */
    void setTextClass(Text textClass) {
        this.textClass = textClass;
    }

    /**
     * Swaps contents of two cells.
     * @param cell Destination of current cell
     */
    void changeCell(Cell cell) {
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
     * Adds the value of two cells and merges them together.
     * @param cell cell to be merged with current cell.
     */
    void adder(Cell cell) {
        cell.getTextClass().setText((cell.getNumber() + this.getNumber()) + "");
        textClass.setText("0");
        root.getChildren().remove(textClass);
        cell.setColorByNumber(cell.getNumber());
        setColorByNumber(getNumber());
    }

    /**
     * Sets color of the cell according to the number of the cell.
     * @param number Number in the cell.
     */
    void setColorByNumber(int number) {
        switch (number) {
            case 0:
                rectangle.setFill(Color.rgb(224, 226, 226, 0.5));
                break;
            case 2:
                rectangle.setFill(Color.rgb(232, 255, 100, 0.5));
                break;
            case 4:
                rectangle.setFill(Color.rgb(232, 220, 50, 0.5));
                break;
            case 8:
                rectangle.setFill(Color.rgb(232, 200, 44, 0.8));
                break;
            case 16:
                rectangle.setFill(Color.rgb(232, 170, 44, 0.8));
                break;
            case 32:
                rectangle.setFill(Color.rgb(180, 120, 44, 0.7));
                break;
            case 64:
                rectangle.setFill(Color.rgb(180, 100, 44, 0.7));
                break;
            case 128:
                rectangle.setFill(Color.rgb(180, 80, 44, 0.7));
                break;
            case 256:
                rectangle.setFill(Color.rgb(180, 60, 44, 0.8));
                break;
            case 512:
                rectangle.setFill(Color.rgb(180, 30, 44, 0.8));
                break;
            case 1024:
                rectangle.setFill(Color.rgb(250, 0, 44, 0.8));
                break;
            case 2048:
                rectangle.setFill(Color.rgb(250,0,0,1));
                break;
            default:
                rectangle.setFill(Color.rgb(0,0,0,1));

        }

    }

    /**
     *
     * @return X-axis size of the cell
     */
    double getX() {
        return rectangle.getX();
    }

    /**
     *
     * @return Y-axis size of the cell
     */
    double getY() {
        return rectangle.getY();
    }

    /**
     *
     * @return Int value of the number in the cell.
     */
    int getNumber() {
        return Integer.parseInt(textClass.getText());
    }

    /**
     *
     * @return Text value of current cell.
     */
    private Text getTextClass() {
        return textClass;
    }

}
