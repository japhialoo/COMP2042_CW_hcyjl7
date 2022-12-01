package com.example.demo;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Random;

public class GameScene {
    // Height used for the height of the box for the game I think will alter and find out.
    private static int HEIGHT = 700;
    // n is used for the number of boxes on the x-axis and y-axis. used in Cell[][] 2d array to create a grid
    private final static int n = 4;
    // Distance between cells duh
    private final static int distanceBetweenCells = 10;
    // Length is 700 minus 4 plus 1 times 10 divide 4 -> 700 - 12.5 -> 687.5
    private static double LENGTH = (HEIGHT - ((n + 1) * distanceBetweenCells)) / (double) n;
    // Unsure of the use of textMaker
    public TextMaker textMaker = TextMaker.getSingleInstance();
    private Cell[][] cells = new Cell[n][n];
    private Group root;
    //private long score = 0;
    Move move = new Move();
    Check check = new Check();
    Account account = new Account();
    private Stage stage;
    private Scene scene;



    static double getLENGTH() {
        return LENGTH;
    }

    private void randomFillNumber() {

        Cell[][] emptyCells = new Cell[n][n];
        int a = 0;
        int b = 0;
        int aForBound=0,bForBound=0;
        outer:
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (cells[i][j].getNumber() == 0) {
                    emptyCells[a][b] = cells[i][j];
                    if (b < n-1) {
                        bForBound=b;
                        b++;

                    } else {
                        aForBound=a;
                        a++;
                        b = 0;
                        if(a==n)
                            break outer;
                    }
                }
            }
        }



        Text text;
        Random random = new Random();
        boolean putTwo = true;
        if (random.nextInt() % 2 == 0)
            putTwo = false;
        int xCell, yCell;
        xCell = random.nextInt(aForBound+1);
        yCell = random.nextInt(bForBound+1);
        if (putTwo) {
            text = textMaker.madeText("2", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY(), root);
            emptyCells[xCell][yCell].setTextClass(text);
            root.getChildren().add(text);
            emptyCells[xCell][yCell].setColorByNumber(2);
        } else {
            text = textMaker.madeText("4", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY(), root);
            emptyCells[xCell][yCell].setTextClass(text);
            root.getChildren().add(text);
            emptyCells[xCell][yCell].setColorByNumber(4);
        }
    }


    void game(Scene gameScene, Group root) {
        this.root = root;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                cells[i][j] = new Cell((j) * LENGTH + (j + 1) * distanceBetweenCells,
                        (i) * LENGTH + (i + 1) * distanceBetweenCells, LENGTH, root);
            }

        }

        Text text = new Text();
        root.getChildren().add(text);
        text.setText("SCORE :");
        text.setFont(Font.font(30));
        text.relocate(750, 100);
        Text scoreText = new Text();
        root.getChildren().add(scoreText);
        scoreText.relocate(750, 150);
        scoreText.setFont(Font.font(20));
        scoreText.setText("0");

        randomFillNumber();
        randomFillNumber();

        gameScene.addEventHandler(KeyEvent.KEY_PRESSED, key ->{
            Platform.runLater(() -> {
                int haveEmptyCell;
                if (key.getCode() == KeyCode.DOWN) {
                    move.down(cells);
                } else if (key.getCode() == KeyCode.UP) {
                    move.up(cells);
                } else if (key.getCode() == KeyCode.LEFT) {
                    move.left(cells);
                } else if (key.getCode() == KeyCode.RIGHT) {
                    move.right(cells);
                }
                //sumCellNumbersToScore();
                scoreText.setText(account.getScore() + "");
                haveEmptyCell = check.haveEmptyCell(cells);
                if (haveEmptyCell == -1) {
                    if (check.canNotMove(cells)) {
//                        primaryStage.setScene(endGameScene);
//
//                        EndGame.getInstance().endGameShow(endGameScene, endGameRoot, primaryStage, account.getScore());
//                        root.getChildren().clear();
                        account.setScore(0);
                    }
                } else if(haveEmptyCell == 1 && check.moved()) {
                    randomFillNumber();
                    check.moved=false;
                }
            });
        });
    }
}
