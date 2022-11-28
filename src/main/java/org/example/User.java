package org.example;

public class User {

    private String name;
    private String password;
    private Float balance;

    public User() {
    }

    public User(String name, String password, Float balance) {
        this.name = name;
        this.password = password;
        this.balance = balance;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public Float getBalance() {
        return this.balance;
    }

    public void setBalance(Float newBalance) {
        this.balance = newBalance;
    }
}