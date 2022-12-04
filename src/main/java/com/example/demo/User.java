package com.example.demo;

import java.io.*;

public class User implements Serializable{

    public String userName;
    public Long highScore;
    private static final long serialVersionUID = 6150078625063920690L;
    static final File file = new File("Users.txt");

    public User() {
    }
    public User(String name, Long highScore) {
        this.userName = name;
        if (highScore == null) {
            this.highScore = Long.valueOf(0);
        }
        else {
            this.highScore = highScore;
        }
    }


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
                    System.out.println("Name : " + user.userName);
                    System.out.println("Score : " + user.highScore);
                }
                else {
                    cont = false;
                }
            }


        } catch (IOException | ClassNotFoundException ex) {
            System.out.println("End Of File");

        }
    }

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



