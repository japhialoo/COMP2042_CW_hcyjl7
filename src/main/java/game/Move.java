package game;

/**
 * Move class for all methods related to moving a cell
 *
 * @author japhialoo
 */
public class Move{
    /**
     * Calls checking methods for certain conditions
     */
    Check check = new Check();

    /**
     * Assigns boolean value to move
     * @param move Boolean value for if a cell has moved
     */
    public void hasMoved(boolean move) {
        Check.moved = move;
    }

    /**
     * Moving all the cells on the grid to the left and resets their modify value to false after every move.
     * @param cells 2D array of cells displayed on the game
     * @param account Current account of user playing
     */
    public void left(Cell[][] cells, Account account) {
        for (int i = 0; i < GameScene.n; i++) {
            for (int j = 1; j < GameScene.n; j++) {
                horizontally(i, j, check.passDestination(i, j, 'l', cells), -1, cells, account);
            }
            for (int j = 0; j < GameScene.n; j++) {
                cells[i][j].setModify(false);
            }
        }
    }

    /**
     * Moving all the cells to the right and resets their modify value to false after every move.
     * @param cells 2D array of cells displayed on the game
     * @param account Current account of user playing
     */
    public void right(Cell[][] cells, Account account) {
        for (int i = 0; i < GameScene.n; i++) {
            for (int j = GameScene.n - 1; j >= 0; j--) {
                horizontally(i, j, check.passDestination(i, j, 'r', cells), 1, cells, account);
            }
            for (int j = 0; j < GameScene.n; j++) {
                cells[i][j].setModify(false);
            }
        }
    }

    /**
     * Moving all the cells up and resets their modify value to false after every move.
     * @param cells 2D array of cells displayed on the game
     * @param account Current account of user playing
     */
    public void up(Cell[][] cells, Account account) {
        for (int j = 0; j < GameScene.n; j++) {
            for (int i = 1; i < GameScene.n; i++) {
                vertically(i, j, check.passDestination(i, j, 'u', cells), -1, cells, account);
            }
            for (int i = 0; i < GameScene.n; i++) {
                cells[i][j].setModify(false);
            }
        }

    }

    /**
     * Moving all the cells down and resets their modify value to false after every move.
     * @param cells 2D array of cells displayed on the game
     * @param account Current account of user playing
     */
    public void down(Cell[][] cells, Account account) {
        for (int j = 0; j < GameScene.n; j++) {
            for (int i = GameScene.n - 1; i >= 0; i--) {
                vertically(i, j, check.passDestination(i, j, 'd', cells), 1, cells, account);
            }
            for (int i = 0; i < GameScene.n; i++) {
                cells[i][j].setModify(false);
            }
        }

    }

    /**
     * Moves and merges cells horizontally based on the direction pressed by the user.
     * @param i Row index
     * @param j Column index
     * @param des Destination of cell
     * @param sign Direction of movement
     * @param cells 2D array of cells
     * @param account Current account of user playing
     */
    public void horizontally(int i, int j, int des, int sign, Cell[][] cells,Account account) {
        if (check.isValidDesH(i, j, des, sign, cells)) {
            cells[i][j].adder(cells[i][des + sign]);
            cells[i][des+sign].setModify(true);
            hasMoved(true);
            if (GameScene.n == 5) {
                account.addToScore((cells[i][des + sign].getNumber())/2);
            } else if (GameScene.n == 3){
                account.addToScore((cells[i][des + sign].getNumber()) * 2L);
            } else {
                account.addToScore(cells[i][des + sign].getNumber());
            }
        } else if (des != j) {
            cells[i][j].changeCell(cells[i][des]);
            if (cells[i][des].getNumber() != 0) {
                hasMoved(true);
            }
        }
    }

    /**
     * Moves and merges cells vertically based on the direction pressed by the user
     * @param i Row index
     * @param j Column index
     * @param des Destination of cell
     * @param sign Direction of movement
     * @param cells 2D array of cells
     * @param account Current account of user playing
     */
    public void vertically(int i, int j, int des, int sign, Cell[][] cells, Account account) {
        if (check.isValidDesV(i, j, des, sign, cells)) {
            cells[i][j].adder(cells[des + sign][j]);
            cells[des+sign][j].setModify(true);
            hasMoved(true);
            if (GameScene.n == 5) {
                account.addToScore((cells[des + sign][j].getNumber())/2);
            } else if (GameScene.n == 3){
                account.addToScore((cells[des + sign][j].getNumber()) * 2L);
            } else {
                account.addToScore(cells[des + sign][j].getNumber());
            }
        } else if (des != i) {
            cells[i][j].changeCell(cells[des][j]);
            if (cells[des][j].getNumber() != 0) {
                hasMoved(true);
            }
        }
    }

}
