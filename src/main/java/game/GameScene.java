package game;

import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

public class GameScene {
    public static int n = StartGame.difficulty;
    private static final int distanceBetweenCells = 10;
    // Height used for the height of the box for the game I think will alter and find out.
    private static final int HEIGHT = 700;
    // Length is 700 minus 4 plus 1 times 10 divide 4 -> 700 - 12.5 -> 687.5
    public static double LENGTH = (HEIGHT - ((n + 1) * distanceBetweenCells)) / (double) n;
    // Unsure of the use of textMaker
    TextMaker textMaker = TextMaker.getSingleInstance();
    private static Cell[][] cells;
    private int count = 0;
    private Group root;
    Move move = new Move();
    Check check = new Check();


    static double getLENGTH() {
        return LENGTH;
    }
    static int getN() {
        return n;
    }

    public static void setN(int number){
        n = number;
        LENGTH = (HEIGHT - ((n + 1) * distanceBetweenCells)) / (double) n;
        System.out.println(n);
        System.out.println(LENGTH);
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
        boolean putTwo = random.nextInt() % 2 != 0;
        int xCell, yCell;
        xCell = random.nextInt(aForBound+1);
        yCell = random.nextInt(bForBound+1);
        if (putTwo) {
            text = textMaker.madeText("2", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY());
            emptyCells[xCell][yCell].setTextClass(text);
            root.getChildren().add(text);
            emptyCells[xCell][yCell].setColorByNumber(2);
        } else {
            text = textMaker.madeText("4", emptyCells[xCell][yCell].getX(), emptyCells[xCell][yCell].getY());
            emptyCells[xCell][yCell].setTextClass(text);
            root.getChildren().add(text);
            emptyCells[xCell][yCell].setColorByNumber(4);
        }
    }


    void game(Scene gameScene, Group root,  Stage primaryStage, Scene endGameScene, Group endGameRoot, Account account, Color c) {
        cells = new Cell[n][n];
        System.out.println(n + " is the n value in game method");
        AtomicLong highScore = new AtomicLong(account.getScore());
        account.setScore(0);
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

        if (Objects.equals(c, Color.BLACK)) {
            text.setFill(Color.WHITE);
            scoreText.setFill(Color.WHITE);
        }

        randomFillNumber();
        randomFillNumber();

        gameScene.addEventHandler(KeyEvent.KEY_PRESSED, key -> Platform.runLater(() -> {
            int haveEmptyCell;
            if (key.getCode() == KeyCode.DOWN) {
                move.down(cells, account);
            } else if (key.getCode() == KeyCode.UP) {
                move.up(cells, account);
            } else if (key.getCode() == KeyCode.LEFT) {
                move.left(cells, account);
            } else if (key.getCode() == KeyCode.RIGHT) {
                move.right(cells, account);
            }
            scoreText.setText(account.getScore() + "");
            haveEmptyCell = check.haveEmptyCell(cells);
            if (haveEmptyCell == -1) {
                if (check.canNotMove(cells)) {
                    primaryStage.setScene(endGameScene);
                    if (account.getScore() > highScore.get()) {
                        highScore.set(account.getScore());}
                    EndGame.getInstance().endGameShow(endGameRoot, account, highScore.get(), c);
                    if (account.getScore() < highScore.get()) {account.setScore(highScore.get());}
                    User.writeAllToFile();
                    Account.printAccounts();
                    root.getChildren().clear();
                }
            } else if(haveEmptyCell == 1 && check.moved()) {
                randomFillNumber();
                Check.moved =false;
            }

            if(check.have2048(cells) && count == 0) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("You Win!!");
                alert.setHeaderText("Your Score is " + account.getScore()+"");
                alert.setContentText("Congrats! You can continue playing :>");
                alert.showAndWait();
                count += 1;
            }
        }));
    }
}
