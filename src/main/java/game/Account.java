package game;

import java.util.ArrayList;


/**
 * Class for managing methods associated to player accounts.
 */
public class Account implements Comparable<Account>{
    public long score;
    public String userName ;
    public static ArrayList<Account> accounts = new ArrayList<>();
    public Account(String name) {
        userName = name;
        score = 0;
    }
    @Override
    public int compareTo(Account o) {
        return Long.compare(o.getScore(), score);
    }

    /**
     * Adds values to user's score in game.
     * @param score Score to be added to the total score
     */
    public void addToScore(long score) {
        this.score += score;
    }

    /**
     *
     * @return Score of the account associated to it.
     */
    public long getScore() {
        return score;
    }

    /**
     * Sets score value to the input specified.
     * @param num Number to set the score as.
     */
    public void setScore(long num) {score = num;}

    /**
     *
     * @return Username associated to the account being used.
     */
    public String getUserName() {
        return userName;
    }
    static void printAccounts() {
        for (Account account : accounts) {
            System.out.println("name: " + account.getUserName());
            System.out.println(("score: " + account.getScore()));
        }
    }

    /**
     * Checks if an account under userName exists in our file.
     * @param userName Username of current player playing the game
     * @return Account of the user if it exists within our file if not will return null.
     */
    static Account accountHaveBeenExist(String userName){
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
     * Creates a new account and stores it in an Array List of Accounts.
     * @param userName Username of current New Player
     * @return The new user account that was created.
     */
    static Account makeNewAccount(String userName){
        Account account = new Account(userName);
        accounts.add(account);
        return account;
    }
}
