package sample.contollers;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import sample.exception.NegativeValueException;
import sample.exception.OutOfBalanceValue;
import sample.program.Account;
import sample.program.DepositThread;

/**
 * Класс, отвечающий за отображение эллементов окна операций приложения
 * @author Ponomarev G.I.
 */

public class ControllerTwo {

    Account account = new Account();

    public Text infoField;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button withDrawButton;

    @FXML
    private Button getBalanceButton;

    @FXML
    private Button deposite_button;

    @FXML
    private Button backButton;

    @FXML
    void initialize() {
        deposite();
        balance();
        withdraw();

    }

    /**
     * Метод выполняет пополнение баланса счета
     */
    private void deposite() {


        deposite_button.setOnAction(event -> {


            Thread depositThread = new DepositThread(account, 10);
            depositThread.start();

            try {
                depositThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            infoField.setText("Ваш баланс пополнен: на " + account.getBalance() + " рублей");


        });

    }

    /**
     * Метод выполняет вывод текущего баланса
     */
    private void balance() {
        getBalanceButton.setOnAction(event -> {

            infoField.setText("Ваш баланс составляет: " + account.getBalance() + " рублей");
        });
    }

    /**
     * Метод выполняет снятие денежных средств
     */
    private void withdraw() {
        withDrawButton.setOnAction(event -> {

            try {
                account.withdraw(500);
            } catch (OutOfBalanceValue outOfBalanceValue) {
                infoField.setText("Not enough money for withdraw");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (NegativeValueException e) {
                e.printStackTrace();
            }
        });
    }


}
