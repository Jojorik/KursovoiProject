import sample.exception.NegativeValueException;
import sample.exception.OutOfBalanceValue;
import sample.program.Account;

/**
 * Класс, с unit - тестами класса Account
 */
public class AccountTest {

    @org.junit.Test(expected = NegativeValueException.class)
    public void accountMinusNegativeTest() throws OutOfBalanceValue, InterruptedException, NegativeValueException {
        Account account = new Account(-25000);
        account.withdraw(-250);
    }

    @org.junit.Test(expected = NegativeValueException.class)
    public void accountWithdrawNegative() throws OutOfBalanceValue, InterruptedException, NegativeValueException {
        Account account = new Account(250);
        account.deposit(-300);
    }
}