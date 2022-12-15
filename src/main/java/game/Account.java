package game;

import java.util.ArrayList;

/**
 * Class for managing methods associated to player accounts
 *
 * @author japhialoo-modifed
 */
public class Account {
    /**
     * Keeps track of score in game
     */
    public long score;
    /**
     * Username of user playing
     */
    public String userName ;
    /**
     * Array List of accounts to store all users that have played the game
     */
    public static ArrayList<Account> accounts = new ArrayList<>();

    /**
     * Constructs a new account with name input.
     * Score in new account will be set to 0.
     * @param name Username of new player
     */
    public Account(String name) {
        userName = name;
        score = 0;
    }

    /**
     * When user is able to merge values on the board, the merged value is added to the score.
     * Merge value will vary with difficulty level chosen.
     * Value will be halved in easy mode, remains the same in normal mode, and doubled in hard mode.
     * @param score Score to be added to the total score
     */
    public void addToScore(long score) {
        this.score += score;
    }

    /**
     * Gets user's score
     * @return Score of the account associated to it
     */
    public long getScore() {
        return score;
    }

    /**
     * Sets score value to the input specified
     * @param num Number to set the score as
     */
    public void setScore(long num) {score = num;}

    /**
     * Gets user's name
     * @return Username associated to the account being used
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Checks if an account under userName exists in our file
     * @param userName Username of current player playing the game
     * @return Account of the user if it exists within our file if not will return null
     */
    static Account accountExists(String userName){
        for(Account account : accounts){
            if(account.getUserName().equals(userName)){
                return account;
            }
            else if (account.getUserName() == null){
                return null;
            }
        }
        return null;
    }

    /**
     * Creates a new account and stores it in an Array List of Accounts
     * @param userName Username of current New Player
     * @return The new user account that was created
     */
    static Account makeNewAccount(String userName){
        Account account = new Account(userName);
        accounts.add(account);
        return account;
    }
}
