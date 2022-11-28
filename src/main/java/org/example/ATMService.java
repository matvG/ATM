package org.example;

public class ATMService {
    private ATM session = new ATM();

    public Boolean login(String name, String password) {
        User user = session.selectOne(name);
        return password.equals(user.getPassword());
    }

    public Float balanceChecking(String aName) {
        User user = session.selectOne(aName);
        return user.getBalance();
    }

    public void addMoney(String name, Float addMoney) {
        if (addMoney > 1000000.0 || addMoney <= 0.0) {
            System.out.println("Операция отклонена\nСумма пополнения должна быть положительной и не должна превышать 1 000 000 BYN");
        } else {
            User user = session.selectOne(name);
            user.setBalance(user.getBalance() + addMoney);
            session.update(user);
            System.out.println("Операция прошла успешно\nПополнение карты " + name + " на " + addMoney + " BYN");
        }
    }

    public void getMoney(String name, Float getMoney) {
        User user = session.selectOne(name);
        if (getMoney > 0.0 && getMoney <= user.getBalance()) {
            user.setBalance(user.getBalance() - getMoney);
            session.update(user);
            System.out.println("Операция прошла успешно\nСписание с карты " + name + " " + getMoney + " BYN");
        } else {
            System.out.println("Операция отклонена\n Недостаточно средств или неверно введена желаемая сумма");
        }
    }

    public void transferMoney(String outName, String inName, Float transferMoney) {
        User outUser = session.selectOne(outName);
        User inUser = session.selectOne(inName);
        if (inUser != null) {
            if (outUser != inUser) {
                if (transferMoney <= outUser.getBalance()) {
                    outUser.setBalance(outUser.getBalance() - transferMoney);
                    inUser.setBalance(inUser.getBalance() + transferMoney);
                    session.update(outUser);
                    session.update(inUser);
                    System.out.println("Операция прошла успешно\n");
                } else {
                    System.out.println("Операция отклонена\n Недостаточно средств на карте отправителя " + outName + "\n");
                }
            } else {
                System.out.println("Операция отклонена\nВы не можете переводить деньги сами себе\n");
            }
        } else {
            System.out.println("Операция отклонена\nВведен неверный номер карты получателя\n");
        }
    }

    public User createCard(String name, String password, Float balance) {
        User user = new User(name, password, balance);
        session.update(user);
        System.out.println("Карта " + name + ", успешно создана");
        return user;
    }

    public void deleteCard(String name) {
        User user = session.selectOne(name);
        if (user != null) {
            session.delete(user);
            System.out.println("Карта " + name + ", успешно заблокирована");
        } else {
            System.out.println("Введен неверный номер карты");
        }
    }
}