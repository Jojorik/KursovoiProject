package sample.database;

import java.sql.*;


/**
 * Класс для работы с таблицей БД, продукты
 */

public class DatabaseProduct extends Configs{
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException{
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName + "?verifyServerCertificate=false&useSSL=false&useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";

        Class.forName("com.mysql.cj.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);
        return dbConnection;
    }

    /**
     * Метод, отправляющий запрос на добавление в Бд холодных продуктов
     * @param name - название продукта в бд
     * @param coldProductsWarehouse - количество на складе
     * @param coldProductsShow - номер поставки
     */

    public void insertColdProductDatabase(String name, String coldProductsWarehouse, String coldProductsShow){
        String insertInColdProduct ="INSERT INTO cold_products(name, count_warehouse,count_showcase)"+
                "VALUES('"+ name + "'," + coldProductsWarehouse + "," +coldProductsShow+")";

        try {
            Statement statement = getDbConnection().createStatement();
            statement.executeUpdate(insertInColdProduct);
            System.out.println(insertInColdProduct);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }

    }

    /**
     * Метод, отправляющий запрос на добавление горячего продукта в бд
     * @param hotProductsName - название продукта в бд горячего цеха
     * @param hotProductsContWarehouse - количество на складе
     * @param hotProductsCountShowcase - номер поставки
     */

    public void insertHotProductDatabase(String hotProductsName, String hotProductsContWarehouse, String hotProductsCountShowcase){
        String insertInHotProduct ="INSERT INTO hot_products(name, count_warehouse,count_showcase)"+
                "VALUES('"+ hotProductsName + "'," + hotProductsContWarehouse + "," +hotProductsCountShowcase+")";

        try {
            Statement statement = getDbConnection().createStatement();
            statement.executeUpdate(insertInHotProduct);
            System.out.println(insertInHotProduct);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

    /**
     * Метод, отправляющий запрос на добавление напитка в базу данных
     * @param drinksProductsName - название напитка
     * @param drinksProductsContWarehouse - количество на складе
     * @param drinksProductsCountShowcase - номер поставки
     */

    public void insertDrinkProductDatabase(String drinksProductsName, String drinksProductsContWarehouse, String drinksProductsCountShowcase){
        String insertInDrinksProduct ="INSERT INTO drinks_products(name, count_warehouse,count_showcase)"+
                "VALUES('"+ drinksProductsName + "'," + drinksProductsContWarehouse + "," +drinksProductsCountShowcase+")";

        try {
            Statement statement = getDbConnection().createStatement();
            statement.executeUpdate(insertInDrinksProduct);
            System.out.println(insertInDrinksProduct);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
    }

}
