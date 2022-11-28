package org.example;

import java.io.*;
import java.util.HashMap;

//Работа с файлом
public class FileWork {

    private String pathName;

    public FileWork(String pathName) {
        this.pathName = pathName;
    }

    public HashMap<String, User> loadFile() {
        HashMap<String, User> userBox = new HashMap<>();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(pathName);
            bufferedReader = new BufferedReader(fileReader);
            String code = bufferedReader.readLine();
            while (code != null) {
                String[] value = code.split(" ");
                User user = new User(value[0], value[1], Float.parseFloat(value[2]));
                userBox.put(user.getName(), user);
                code = bufferedReader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fileReader != null) {
                    fileReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return userBox;
    }

    public void update(HashMap<String, User> userBox) {
        FileWriter fileWriter = null;
        BufferedWriter bufferedWriter = null;
        try {
            fileWriter = new FileWriter(pathName);
            bufferedWriter = new BufferedWriter(fileWriter);
            for (String name : userBox.keySet()) {
                User user = userBox.get(name);

                String builder = user.getName() +
                        " " +
                        user.getPassword() +
                        " " +
                        user.getBalance();
                bufferedWriter.write(builder);//Write a line of data to the file
                bufferedWriter.flush();//Refresh
                bufferedWriter.newLine();//New line
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedWriter != null) {
                    bufferedWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

