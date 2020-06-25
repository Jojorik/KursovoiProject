package sample.program;

import sample.exception.NegativeValueException;
import sample.exception.OutOfBalanceValue;

public class Account {

    private long balance;

    public Account() {
        this(0);
    }

    public Account(long balance) {
        this.balance = balance;
    }

    public long getBalance() {
        return balance;
    }

    public synchronized void deposit(long money) throws NegativeValueException {
        checkMoney(money);
        balance = money;
        notify();
    }

    public synchronized void withdraw(long money) throws NegativeValueException, OutOfBalanceValue, InterruptedException {

        if (balance < money) {
            wait(1000);
        }

        checkMoney(money);

        if (money > balance) {
            throw new OutOfBalanceValue();
        }
        balance -= money;
    }


    private void checkMoney(long money) throws NegativeValueException {
        if (money < 0) {
            throw new NegativeValueException();
        }
    }

}
