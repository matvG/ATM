package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ATMService atmService = new ATMService();
        Scanner input = new Scanner(System.in);
        System.out.println("Вас приветствует банкомат");
        boolean run = true;
        while (run) {
            System.out.println("""
                    Выберите операцию:
                    1. Вставить карточку
                    2. Создать карточку
                    3. Выход""");
            String choice = input.next();
            if (choice.equals("1")) {
                String name, password;
                do {
                    System.out.println("Введите номер карты (xxxx-xxxx-xxxx-xxxx)");
                    name = input.next();
                    System.out.println("Введите пароль (xxxx)");
                    password = input.next();
                } while (!atmService.login(name, password));
                System.out.println("Вход выполнен успешно\n");
                boolean round = true;
                while (round) {
                    System.out.println("Выберите нужный пункт:");
                    System.out.println("""
                            1. Просмотр баланса
                            2. Пополнение
                            3. Вывод средств
                            4. Перевод
                            5. Закрытие карточки
                            6. Выход""");
                    String option = input.next();
                    switch (option) {
                        case "1" -> { //Проверка баланса
                            Float money = atmService.balanceChecking(name);
                            System.out.println("На карте " + money + " BYN\n");
                        }
                        case "2" -> { //Сумма пополнения
                            System.out.println("Пожалуйта введите желаемую сумму пополнения (<= 1 000 000)");
                            Float addMoney = input.nextFloat();
                            atmService.addMoney(name, addMoney);
                        }
                        case "3" -> { //Вывод
                            System.out.println("Пожалуйста введите желаемую сумму вывода");
                            Float getMoney = input.nextFloat();
                            atmService.getMoney(name, getMoney);
                        }
                        case "4" -> { //Перевод
                            System.out.println("Пожалуйста введите номер карточки для перевода");
                            String id = input.next();
                            System.out.println("Введите сумму перевода");
                            Float transferMoney = input.nextFloat();
                            atmService.transferMoney(name, id, transferMoney);
                        }
                        case "5" -> { //Удаление карточки
                            System.out.println("Как жаль, что вы закрываете карту в нашем банке. \nЕсли на карточке есть деньги, банкомат выдаст их вам!");
                            if (atmService.balanceChecking(name) > 0) {
                                System.out.println("Возьмите деньги в количестве " + atmService.balanceChecking(name) + " BYN");
                                atmService.getMoney(name, atmService.balanceChecking(name));
                            }
                            atmService.deleteCard(name);
                        }
                        default -> {
                            System.out.println("Заберите карточку, спасибо за пользование нашей системой обслуживания!!!");
                            round = false;
                        }
                    }
                }
            } else if (choice.equals("2")) {
                boolean flag = true;
                do {
                    System.out.println("Введите номер карты (xxxx-xxxx-xxxx-xxxx)");
                    String newUserName = input.next();
                    System.out.println("Введите пароль (xxxx)");
                    String newPassword = input.next();
                    if (newUserName.length() == 19 && newPassword.length() == 4) {
                        flag = false;
                        atmService.createCard(newUserName, newPassword, 0.0F);
                    } else {
                        System.out.println("Неверно введен номер карты или пароль");
                    }
                } while (flag);
                System.out.println("Возьмите вашу карточку, спасибо за пользование нашей системой!!!");
            } else {
                System.out.println("Спасибо за пользование нашей системой!!!");
                run = false;
            }
        }
    }
}