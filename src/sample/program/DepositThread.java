package sample.program;

import sample.exception.NegativeValueException;

public class DepositThread extends Thread {

    private Account account;
    private long money;

    public DepositThread(Account account, long money) {
        this.account = account;
        this.money = money;
    }

    /**
     * Метод запуска потока
     */
    @Override
    public void run() {

        for (int i = 0; i < 10; i++) {

            try {
                account.deposit(money);
            } catch (NegativeValueException e) {
                e.printStackTrace();
            }
        }
    }
}