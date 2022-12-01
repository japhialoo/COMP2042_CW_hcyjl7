package com.example.demo;

/**
 * Move class for all methods related to moving a cell.
 */
public class Move{
    public final static int n = 4;
    Check check = new Check();
    Account account = new Account();
    public void hasMoved(boolean move) {
        check.moved = move;
    }

    // Moving all the cells according to the direction. filters through all cells row by row
    public void left(Cell[][] cells) {
        System.out.println("Moving Left");
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                horizontally(i, j, check.passDestination(i, j, 'l', cells), -1, cells);
            }
            for (int j = 0; j < n; j++) {
                cells[i][j].setModify(false);
            }
        }
    }

    public void right(Cell[][] cells) {
        System.out.println("Moving Right");
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= 0; j--) {
                horizontally(i, j, check.passDestination(i, j, 'r', cells), 1, cells);
            }
            for (int j = 0; j < n; j++) {
                cells[i][j].setModify(false);
            }
        }
    }

    public void up(Cell[][] cells) {
        System.out.println("Moving Up");
        for (int j = 0; j < n; j++) {
            for (int i = 1; i < n; i++) {
                vertically(i, j, check.passDestination(i, j, 'u', cells), -1, cells);
            }
            for (int i = 0; i < n; i++) {
                cells[i][j].setModify(false);
            }
        }

    }
    public void down(Cell[][] cells) {
        System.out.println("Moving Down");
        for (int j = 0; j < n; j++) {
            for (int i = n - 1; i >= 0; i--) {
                vertically(i, j, check.passDestination(i, j, 'd', cells), 1, cells);
            }
            for (int i = 0; i < n; i++) {
                cells[i][j].setModify(false);
            }
        }

    }

    public void horizontally(int i, int j, int des, int sign, Cell[][] cells) {
        if (check.isValidDesH(i, j, des, sign, cells)) {
            cells[i][j].adder(cells[i][des + sign]);
            cells[i][des+sign].setModify(true);
            hasMoved(true);
            account.addToScore(cells[i][des+sign].getNumber());
        } else if (des != j) {
            cells[i][j].changeCell(cells[i][des]);
            if (cells[i][des].getNumber() != 0) {
                hasMoved(true);
            }
        }
    }

    public void vertically(int i, int j, int des, int sign, Cell[][] cells) {
        if (check.isValidDesV(i, j, des, sign, cells)) {
            cells[i][j].adder(cells[des + sign][j]);
            cells[des+sign][j].setModify(true);
            hasMoved(true);
            account.addToScore(cells[des+sign][j].getNumber());
        } else if (des != i) {
            cells[i][j].changeCell(cells[des][j]);
            if (cells[des][j].getNumber() != 0) {
                hasMoved(true);
            }
        }
    }


}
