package org.example;

import java.util.HashMap;

public class ATM {
    private FileWork file = new FileWork("C:\\Users\\Admin\\Desktop\\ATM.txt");
    HashMap<String, User> userBox = file.loadFile();

    public User selectOne(String name) {
        return userBox.get(name);
    }

    public void update(User user) {
        userBox.put(user.getName(), user);
        file.update(userBox);
    }

    public void delete(User user) {
        userBox.remove(user.getName(), user);
        file.update(userBox);
    }
}