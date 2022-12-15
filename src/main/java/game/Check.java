package game;

import javafx.scene.paint.Color;

/**
 * Check class for all methods in charge of validating moves and checking conditions.
 *
 * @author japhialoo
 */
public class Check{
    /**
     * Boolean value for if cells have moved or not
     */
    public static boolean moved = false;

    /**
     * Checks if any numbers on the grid have moved.
     * @return Boolean value for if any cell has moved in the grid
     */
    public boolean moved() {
        return moved;
    }

    /**
     * Takes in Cells from the grid displayed in the game and checks for neighbouring right and bottom cells values.
     * @param i Row index of the cell
     * @param j Column index of the cell
     * @param cells 2d array of cells displayed on the grid
     * @return Boolean value to determine if neighbouring cells have the same number
     */
    public boolean haveSameNumberNearly(int i, int j, Cell[][] cells) {
        if (i < GameScene.n - 1 && j < GameScene.n - 1) {
            if (cells[i + 1][j].getNumber() == cells[i][j].getNumber())
                return true;
            return cells[i][j + 1].getNumber() == cells[i][j].getNumber();
        }
        return false;
    }

    /**
     * Takes in the 2D array of cells on the grid and determines if any cells can be merged creating a move for the user.
     * @param cells 2d array of cells displayed on the grid
     * @return Boolean value to determine if any cells can move
     */
    public boolean canNotMove(Cell[][] cells) {
        for (int i = 0; i < GameScene.n; i++) {
            for (int j = 0; j < GameScene.n; j++) {
                if (haveSameNumberNearly(i, j, cells)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Checks if the grid shown to the user in game has any empty cells.
     * @param cells 2D array of cells in the game
     * @return Boolean value to determine if there are any empty cells in the grid
     */
    public Boolean  haveEmptyCell(Cell[][] cells) {
        for (int i = 0; i < GameScene.n; i++) {
            for (int j = 0; j < GameScene.n; j++) {
                if (cells[i][j].getNumber() == 0)
                    return true;
            }
        }
        return false;
    }

    /**
     * Checks if cell with value "2048" has appeared in the game. This is the win condition for the game
     * @param cells 2D array of cells shown in game
     * @return True if 2048 is in the grid
     */
    public boolean  have2048(Cell[][] cells) {
        for (int i = 0; i < GameScene.n; i++) {
            for (int j = 0; j < GameScene.n; j++) {
                if(cells[i][j].getNumber() == 2048)
                    return true;
            }
        }
        return false;
    }



    /**
     * Takes in the index values of a cell and its destination and checks if it can be
     * merged with the number it is approaching horizontally
     * @param i Row index of the cell
     * @param j Column index of the cell
     * @param des Destination of the cell
     * @param sign Determines direction. 1 and -1
     * @param cells Individual cells in the grid
     * @return Boolean value of whether the Horizontal destination is valid
     */
    public boolean isValidDesH(int i, int j, int des, int sign, Cell[][] cells) {
        if (des + sign < GameScene.n && des + sign >= 0) {
            return cells[i][des + sign].getNumber() == cells[i][j].getNumber() && cells[i][des + sign].getModify()
                    && cells[i][des + sign].getNumber() != 0 && cells[i][j].getModify();
        }
        return false;
    }

    /**
     * Takes in the index values of a cell and its destination and checks if it can be
     * merged with the number it is approaching vertically
     * @param i Row index of the cell
     * @param j Column index of the cell
     * @param des Destination of the cell
     * @param sign Determines direction. 1 and -1
     * @param cells Individual cells in the grid
     * @return Boolean value of whether the Vertical destination is valid
     */
    public boolean isValidDesV(int i, int j, int des, int sign, Cell[][] cells) {
        if (des + sign < GameScene.n && des + sign >= 0)
            return cells[des + sign][j].getNumber() == cells[i][j].getNumber() && cells[des + sign][j].getModify()
                    && cells[des + sign][j].getNumber() != 0 && cells[i][j].getModify();
        return false;
    }

    /**
     * Takes in a cell from the grid and checks for the farthest empty cell it can reach according to its direction.
     * @param i Row index for cells in the grid
     * @param j Column index for cells in the grid
     * @param direct Direction of the cell in the grid
     * @param cells Individual cells in the grid
     * @return Coordinate value of the cells destination in the grid
     */
    public int passDestination(int i, int j, char direct, Cell[][] cells) {
        int coordinate = j;
        if (direct == 'l') {
            for (int k = j - 1; k >= 0; k--) {
                if (cells[i][k].getNumber() != 0) {
                    coordinate = k + 1;
                    break;
                } else if (k == 0) {
                    coordinate = 0;
                }
            }
            return coordinate;
        }
        if (direct == 'r') {
            for (int k = j + 1; k <= GameScene.n - 1; k++) {
                if (cells[i][k].getNumber() != 0) {
                    coordinate = k - 1;
                    break;
                } else if (k == GameScene.n - 1) {
                    coordinate = GameScene.n - 1;
                }
            }
            return coordinate;
        }
        coordinate = i;
        if (direct == 'd') {
            for (int k = i + 1; k <= GameScene.n - 1; k++) {
                if (cells[k][j].getNumber() != 0) {
                    coordinate = k - 1;
                    break;

                } else if (k == GameScene.n - 1) {
                    coordinate = GameScene.n - 1;
                }
            }
            return coordinate;
        }
        if (direct == 'u') {
            for (int k = i - 1; k >= 0; k--) {
                if (cells[k][j].getNumber() != 0) {
                    coordinate = k + 1;
                    break;
                } else if (k == 0) {
                    coordinate = 0;
                }
            }
            return coordinate;
        }
        return -1;
    }

    /**
     * Takes in the color chosen by the user at the start of the game and converts it to grayscale.
     * Then compares the grayscale value to a threshold value to determine if it is a dark color.
     * @param c Color chosen by user at menu page.
     * @return Boolean variable if color is a dark color.
     */
    public static Boolean darkColor(Color c){
        double red = c.getRed() * 255;
        double green = c.getGreen() * 255;
        double blue = c.getBlue() * 255;
        double gray = 0.21 * red + 0.71 * green + 0.07 * blue;
        return gray < 128.0;
    }
}
