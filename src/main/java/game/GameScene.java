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
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;

/**
 * In charge of the game scene for the program
 *
 * @author japhialoo-modified
 */
public class GameScene {
    /**
     * Number of cells in the grid. This value will change with different difficulty levels
     */
    public static int n = StartGame.difficulty;
    /**
     * Distance between cells in the grid
     */
    private static final int distanceBetweenCells = 10;
    /**
     * Height of the Game grid
     */
    private static final int HEIGHT = 700;
    /**
     * Length of the cell
     */
    public static double LENGTH = (HEIGHT - ((n + 1) * distanceBetweenCells)) / (double) n;
    /**
     * Instance of TextMaker class
     */
    TextMaker textMaker = TextMaker.getSingleInstance();
    /**
     * 2D array of cells to be displayed on the grid
     */
    private static Cell[][] cells;
    /**
     * Checks if user has won the game by obtaining cell "2048"
     */
    private Boolean win = false;
    /**
     * Root node to inherit elements to be displayed
     */
    private Group root;
    /**
     * Calling Move class to utilise methods
     */
    Move move = new Move();
    /**
     * Calling Check class to utilise methods
     */
    Check check = new Check();

    /**
     * Set value of N according to the Difficulty chosen
     * @param number Difficulty chosen in game
     */
    public static void setN(int number){
        n = number;
        LENGTH = (HEIGHT - ((n + 1) * distanceBetweenCells)) / (double) n;
    }

    /**
     * Method to generate a random cell with a random number of either 2 or 4 in the game
     */
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

    /**
     * Method to call the game
     * @param gameScene Scene in which the game will be displayed in
     * @param root The root node that will inherit the elements to be rendered
     * @param primaryStage The stage that contains all the objects in the game
     * @param endGameScene Scene to display the end game window
     * @param endGameRoot Root node to inherit elements in the end game file to be rendered
     * @param account Current account of user playing the game
     * @param c Color of the scene chosen by the user. else will be a default color
     */
    void game(Scene gameScene, Group root,  Stage primaryStage, Scene endGameScene, Group endGameRoot, Account account, Color c) {
        cells = new Cell[n][n];
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
        Text userText = new Text();
        root.getChildren().add(userText);
        userText.setText("USER :");
        userText.setFont(Font.font(30));
        userText.relocate(750, 300);
        Text nameText = new Text();
        root.getChildren().add(nameText);
        nameText.setText(account.getUserName());
        nameText.relocate(750, 350);
        nameText.setFont(Font.font(20));


        if (Check.darkColor(c)) {
            text.setFill(Color.WHITE);
            scoreText.setFill(Color.WHITE);
            userText.setFill(Color.WHITE);
            nameText.setFill(Color.WHITE);
        }

        randomFillNumber();
        randomFillNumber();

        gameScene.addEventHandler(KeyEvent.KEY_PRESSED, key -> Platform.runLater(() -> {
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
            if (!check.haveEmptyCell(cells) && check.canNotMove(cells)) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Game Over");
                alert.setHeaderText("You ran out of moves :(");
                alert.setContentText("Score : " + account.getScore());

                Optional<ButtonType> result = alert.showAndWait();
                if (result.orElse(null) == ButtonType.OK){
                    primaryStage.setScene(endGameScene);
                }

                if (account.getScore() > highScore.get()) {
                    highScore.set(account.getScore());}

                EndGame.getInstance().endGameShow(endGameRoot, account, highScore.get(), c);

                if (account.getScore() < highScore.get()) {account.setScore(highScore.get());}
                User.writeAllToFile();
                root.getChildren().clear();
            } else if (check.haveEmptyCell(cells) && check.moved()){
                randomFillNumber();
                Check.moved =false;
            }

            if(check.have2048(cells) && !win) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("You Win!!");
                alert.setHeaderText("Your Score is " + account.getScore()+"");
                alert.setContentText("Congrats! You can continue playing :>");
                alert.showAndWait();
                win = true;
            }
        }));
    }
}
