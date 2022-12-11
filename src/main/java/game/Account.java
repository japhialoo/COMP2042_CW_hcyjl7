package game;

import java.util.ArrayList;

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
    public void addToScore(long score) {
        this.score += score;
    }
    public long getScore() {
        return score;
    }
    public void setScore(long num) {score = num;}
    public String getUserName() {
        return userName;
    }
    static void printAccounts() {
        for (Account account : accounts) {
            System.out.println("name: " + account.getUserName());
            System.out.println(("score: " + account.getScore()));
        }
    }
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
    static Account makeNewAccount(String userName){
        Account account = new Account(userName);
        accounts.add(account);
        return account;
    }
}
