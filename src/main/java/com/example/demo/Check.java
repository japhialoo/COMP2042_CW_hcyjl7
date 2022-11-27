package com.example.demo;

/**
 * Check class for all methods in charge of validating moves.
 */
public class Check{
    private final static int n = 4;
    public static boolean moved = false;

    public boolean moved() {
        return moved;
    }


    /**
     *
     * @param i Row index of the cell
     * @param j Column index of the cell
     * @param des Destination of the cell
     * @param sign Determines direction. 1 and -1
     * @param cells Individual cells in the grid
     * @return Returns a Boolean value of whether the Horizontal destination is valid.
     */
    public boolean isValidDesH(int i, int j, int des, int sign, Cell[][] cells) {
        if (des + sign < n && des + sign >= 0) {
            // if destination cell is the same as current cell ( adding them) and
            if (cells[i][des + sign].getNumber() == cells[i][j].getNumber() && !cells[i][des + sign].getModify()
                    && cells[i][des + sign].getNumber() != 0) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param i Row index of the cell
     * @param j Column index of the cell
     * @param des Destination of the cell
     * @param sign Determines direction. 1 and -1
     * @param cells Individual cells in the grid
     * @return Returns a Boolean value of whether the Vertical destination is valid.
     */
    public boolean isValidDesV(int i, int j, int des, int sign, Cell[][] cells) {
        if (des + sign < n && des + sign >= 0)
            if (cells[des + sign][j].getNumber() == cells[i][j].getNumber() && !cells[des + sign][j].getModify()
                    && cells[des + sign][j].getNumber() != 0) {
                return true;
            }
        return false;
    }

    /**
     *
     * @param i Row index for cells in the grid
     * @param j Column index for cells in the grid
     * @param direct Direction of the cell in the grid
     * @param cells Individual cells in the grid
     * @return Returns Coordinate value of the cells destination in the grid
     */
    public int passDestination(int i, int j, char direct, Cell[][] cells) {
        int coordinate = j;
        if (direct == 'l') {
            //System.out.println("Direction is left for row " + i + " column " + j);
            for (int k = j - 1; k >= 0; k--) {
                if (cells[i][k].getNumber() != 0) {
                    coordinate = k + 1;
                    break;
                } else if (k == 0) {
                    coordinate = 0;
                }
            }
            //System.out.println("coordinate is " + coordinate);
            return coordinate;
        }
        coordinate = j;
        if (direct == 'r') {
            //System.out.println("Direction is right for row " + i + " column " + j);
            for (int k = j + 1; k <= n - 1; k++) {
                if (cells[i][k].getNumber() != 0) {
                    coordinate = k - 1;
                    break;
                } else if (k == n - 1) {
                    coordinate = n - 1;
                }
            }
            //System.out.println("coordinate is " + coordinate);
            return coordinate;
        }
        coordinate = i;
        if (direct == 'd') {
            //System.out.println("Direction is down for row " + i + " column " + j);
            for (int k = i + 1; k <= n - 1; k++) {
                if (cells[k][j].getNumber() != 0) {
                    coordinate = k - 1;
                    break;

                } else if (k == n - 1) {
                    coordinate = n - 1;
                }
            }
            //System.out.println("coordinate is " + coordinate);
            return coordinate;
        }
        coordinate = i;
        if (direct == 'u') {
            //System.out.println("Direction is up for row " + i + " column " + j);
            for (int k = i - 1; k >= 0; k--) {
                if (cells[k][j].getNumber() != 0) {
                    coordinate = k + 1;
                    break;
                } else if (k == 0) {
                    coordinate = 0;
                }
            }
            //System.out.println("coordinate is " + coordinate);
            return coordinate;
        }
        return -1;
    }
}
