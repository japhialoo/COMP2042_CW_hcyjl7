package game;

import java.io.*;
import java.util.Objects;

/**
 * Holds read and write to file methods
 *
 * @author japhialoo
 */
public class User implements Serializable{
    /**
     * Username of user
     */
    public String userName;
    /**
     * All time high-score of user
     */
    public Long highScore;

    @Serial
    private static final long serialVersionUID = 6150078625063920690L;
    /**
     * File that stores all Usernames and corresponding high scores
     */
    static final File file = new File("Users.txt");

    /**
     * Constructs User objects to be written into a local file.
     * @param name Name of user
     * @param highScore High Score of user
     */
    public User(String name, Long highScore) {
        this.userName = name;
        this.highScore = Objects.requireNonNullElse(highScore, 0L);
    }

    /**
     * Reads Users name and high-scores from the Users.txt file and stores values in an Array List of Accounts
     */
    public static void readFromFile() {
        try (FileInputStream fis = new FileInputStream(file);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            boolean cont = true;
            while (cont) {
                // read object from file
                User user = (User) ois.readObject();
                if (user != null) {
                    Account account = new Account(user.userName);
                    account.setScore(user.highScore);
                    Account.accounts.add(account);
                }
                else {
                    cont = false;
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("End Of File");
        }
    }

    /**
     * Retrieves all Accounts stored in an Array List of accounts from Account and creates User objects to be written into the Users.txt file.
     */
    public static void writeAllToFile() {
        try (FileOutputStream fileOS = new FileOutputStream(file);
             ObjectOutputStream objectOS = new ObjectOutputStream(fileOS)) {
            for (Account account : Account.accounts) {
                User user = new User(account.getUserName(), account.getScore());
                objectOS.writeObject(user);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}



