package com.example.demo;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.Collections;

public class Account implements Comparable<Account> {
    public static long score = 0;
    private String userName ;
    private static ArrayList<Account> accounts = new ArrayList<>();


    //public Account(String userName){
        //this.userName=userName;
    //}

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

    public void setScore(int num) {score = num;}

    public String getUserName() {
        return userName;
    }

    static Account accountHaveBeenExist(String userName){
        for(Account account : accounts){
            if(account.getUserName().equals(userName)){
                return account;
            }
        }
        return null;

    }

//    static Account makeNewAccount(String userName){
//        Account account = new Account(userName);
//        accounts.add(account);
//        return account;
//    }

    public void sumCellNumbersToScore(int num) {
        score += num;

    }

}
