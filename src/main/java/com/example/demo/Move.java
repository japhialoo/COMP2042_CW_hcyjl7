package com.example.demo;

/**
 * Move class for all methods related to moving a cell.
 */
public class Move{
    public final static int n = 4;
    //public Cell[][] cells = new Cell[n][n];
    Check check = new Check();

    public void hasMoved(boolean move) {
        check.moved = move;
    }

    // Moving all the cells according to the direction. filters through all cells row by row
    public void left(Cell[][] cells) {
        System.out.println("Moving Left");
        System.out.println("hasMoved is now " + check.moved());
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n; j++) {
                horizontally(i, j, check.passDestination(i, j, 'l', cells), -1, cells);
                //System.out.println("this is i: " + i + " this is j " + j);
            }
            for (int j = 0; j < n; j++) {
                cells[i][j].setModify(false);
                //System.out.println("the cell is " + cells[i][j].getNumber());
            }
        }
    }

    public void right(Cell[][] cells) {
        System.out.println("Moving Right");
        System.out.println("hasMoved is now " + check.moved());
        for (int i = 0; i < n; i++) {
            for (int j = n - 1; j >= 0; j--) {
                horizontally(i, j, check.passDestination(i, j, 'r', cells), 1, cells);
                //System.out.println("this is i: " + i + " this is j " + j);
            }
            for (int j = 0; j < n; j++) {
                cells[i][j].setModify(false);
                //System.out.println("the cell is " + cells[i][j].getNumber());
            }
        }
    }

    public void up(Cell[][] cells) {
        System.out.println("Moving Up");
        System.out.println("hasMoved is now " + check.moved());
        for (int j = 0; j < n; j++) {
            for (int i = 1; i < n; i++) {
                vertically(i, j, check.passDestination(i, j, 'u', cells), -1, cells);
                //System.out.println("this is i: " + i + " this is j " + j);
            }
            for (int i = 0; i < n; i++) {
                cells[i][j].setModify(false);
                //System.out.println("the cell is " + cells[i][j].getNumber());
            }
        }

    }
    public void down(Cell[][] cells) {
        System.out.println("Moving Down");
        System.out.println("hasMoved is now " + check.moved());
        for (int j = 0; j < n; j++) {
            for (int i = n - 1; i >= 0; i--) {
                vertically(i, j, check.passDestination(i, j, 'd', cells), 1, cells);
                //System.out.println("this is i: " + i + " this is j " + j);
            }
            for (int i = 0; i < n; i++) {
                cells[i][j].setModify(false);
                //System.out.println("the cell is " + cells[i][j].getNumber());
            }
        }

    }

    public void horizontally(int i, int j, int des, int sign, Cell[][] cells) {
        System.out.println("Horizontally for cell " + i + "," +j);
        if (check.isValidDesH(i, j, des, sign, cells)) {
            //System.out.println("Move is valid for " + cells[i][j].getNumber());
            cells[i][j].adder(cells[i][des + sign]);
            cells[i][des].setModify(true);
                hasMoved(true);
            //System.out.println("current cell value is " + cells[i][j].getNumber() + " and i: " + i + " and j is: " + j);
        } else if (des != j) {
            //System.out.println("Before changeCell is " + cells[i][j].getNumber());
            cells[i][j].changeCell(cells[i][des]);
            if (cells[i][des].getNumber() != 0) {
                hasMoved(true);
            }
            //System.out.println("after changeCell cells[i][j] is " + cells[i][j].getNumber() + " and cell[i][des] is " + cells[i][des].getNumber());
        }
        System.out.println("Cell value is " + cells[i][des].getNumber() + " hasMoved is now " + check.moved());
    }

    public void vertically(int i, int j, int des, int sign, Cell[][] cells) {
        System.out.println("Horizontally for cell " + i + "," +j);
        //System.out.println("Currently in moveVertically");
        if (check.isValidDesV(i, j, des, sign, cells)) {
            //System.out.println("Move is valid for " + cells[i][j].getNumber());
            cells[i][j].adder(cells[des + sign][j]);
            cells[des][j].setModify(true);
            hasMoved(true);

            //System.out.println("current cell value is " + cells[i][j].getNumber() + " and i: " + i + " and j is: " + j);
        } else if (des != i) {
            //System.out.println("Before changeCell is " + cells[i][j].getNumber());
            cells[i][j].changeCell(cells[des][j]);
            if (cells[des][j].getNumber() != 0) {
                hasMoved(true);
            }
            //System.out.println("after changeCell cells[i][j] is " + cells[i][j].getNumber() + " and cell[i][des] is " + cells[i][des].getNumber());
        }
        System.out.println("Cell value is " + cells[des][j].getNumber() + " hasMoved is now " + check.moved());
    }
}
