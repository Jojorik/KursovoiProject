package sample.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * Класс, отвечающий за работу с БД
 * @author Ponomarev G.I. 17IT18
 */

public class DataBaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?verifyServerCertificate=false&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }
    public void signUpUsers(String firstName, String lastName, String userName, String password, String location, String gender){
        String insert ="INSERT INTO "+ Const.USER_TABLE + "(" + Const.USER_FIRSTNAME + "," + Const.USER_LASTNAME + ","+ Const.USER_USERNAME + ","+ Const.USER_PASSWORD + "," + Const.USER_LOCATION + "," +Const.USER_GENDER + ")" +
                "VALUES(?,?,?,?,?,?)";



        try {
            PreparedStatement prSt = getDbConnection().prepareStatement(insert);
            prSt.setString(1, firstName);
            prSt.setString(2, lastName);
            prSt.setString(3, userName);
            prSt.setString(4, password);
            prSt.setString(5, location);
            prSt.setString(6, gender);

            prSt.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


}
