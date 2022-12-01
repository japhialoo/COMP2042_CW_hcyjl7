package com.example.demo;

import java.util.ArrayList;

public class User {
    Controller controller = new Controller();
    String userName = controller.getName();

    Account accountExists = Account.accountHaveBeenExist(userName);

}
