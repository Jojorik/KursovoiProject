package sample.contollers;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
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
    private TextField withdraw_field;

    @FXML
    private TextField deposit_field;


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
            Long deposit_field_value = Long.parseLong(deposit_field.getText());



            Thread depositThread = new DepositThread(account, deposit_field_value);
            depositThread.start();

            try {
                depositThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            infoField.setText("Ваш баланс пополнен: на " + deposit_field_value + " рублей");


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
            Long withdraw_field_value = Long.parseLong(withdraw_field.getText());

            try {
                account.withdraw(withdraw_field_value);
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
