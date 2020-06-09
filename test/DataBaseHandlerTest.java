import sample.database.DataBaseHandler;

import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;

public class DataBaseHandlerTest {

    //нет доступа к бд, отправка запроса
    @org.junit.Test(expected =  SQLNonTransientConnectionException.class)
    public void signUpUser() throws SQLException, ClassNotFoundException {
        DataBaseHandler dataBaseHandler = new DataBaseHandler();
        dataBaseHandler.getDbConnection();

        dataBaseHandler.signUpUser("email", "password");
    }
}