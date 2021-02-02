package sample.database;

import java.sql.*;
import java.util.ArrayList;

/**
 * Статический класс соединения с базой данных
 *
 * @author Ponomarev G.I.
 */
public class Database {
    //Соединение с базой данных
    private static Connection connection;

    /**
     * Создание соединения с базой данных
     *
     * @throws SQLException - ошибки подключения к базе данных
     */
    public static void createDatabaseConnection() throws SQLException {

        //Переменные базы данных
        String user = "root";
        String pass = "909090m";
        String schema = "cars_service";
        String url = "jdbc:mysql://localhost/" + schema + "?serverTimezone=Europe/Moscow&useSSL=false";

        connection = DriverManager.getConnection(url, user, pass);
    }

    /**
     * Закрытие соединения с базой данных
     *
     * @throws SQLException - ошибка в подключении к базе данных
     */

    public static void closeDatabaseConnection() throws SQLException {
        connection.close();
    }

    /**
     * Создание запроса
     *
     * @return запрос
     * @throws SQLException - ошибки соединения
     */

    private static Statement getRequest() throws SQLException {
        return connection.createStatement();
    }

    private static ArrayList<String> getTableColumns(String table) throws SQLException {
        ArrayList<String> result = new ArrayList<String>();

        Statement statement = getRequest();
        ResultSetMetaData md = statement.executeQuery("SELECT * FROM " + table).getMetaData();
        //Устарновка значений в массив
        for (int i = 1; i <= md.getColumnCount(); i++) {
            result.add(md.getColumnName(i));
        }
        return result;
    }

    /**
     * Вывод всех данных из таблицы
     *
     * @param table - название таблицы
     * @return - ResultSet переменных
     * @throws SQLException - ошибки связанных с базой данных
     */
    public static ResultSet select(String table) throws SQLException {
        return getRequest().executeQuery("select * from " + table);
    }

    /**
     * Поиск строки по условию
     *
     * @param table     - название таблицы
     * @param condition - условие для поиска
     * @return - ResultSet строки
     * @throws SQLException - ошибки связанные с базой данных
     */
    public static ResultSet select(String table, String condition) throws SQLException {
        return getRequest().executeQuery("select * from " + table + " where " + condition);
    }


    /**
     * Добавление значений в базу данных с учетом существующего поля id, которое может быть Auto Increment
     *
     * @param table  - название таблицы в базе данных
     * @param values - массив значений для внесения в базу данных
     * @param ai     - boolean от которого зависит значние id в запросе
     * @throws SQLException - ошибки связанные с базой данных
     */
    public static void insert(String table, ArrayList<String> values, boolean ai) throws SQLException {
        Statement statement = getRequest();
        StringBuilder request = new StringBuilder("insert into " + table + " values(");
        //Установка значения в зависимости от значения Auto Increment колонки в таблице
        if (ai) {
            request.append("default,");
        }
        //Установка значений
        for (int i = 0; i < values.size(); i++) {
            request.append("'");
            request.append(values.get(i));
            if (i < values.size() - 1) request.append("',");
            else request.append("'");
        }
        request.append(")");

        System.out.println(request);
        //Отправление запроса в базу данных
        statement.executeUpdate(request.toString());
    }

    /**
     * Метод обновления строки в базе данных
     *
     * @param table  - название таблицы для обновления
     * @param values - массив значения
     * @throws SQLException - ошибки связанные с базой данных
     */
    public static void update(String table, ArrayList<String> values) throws SQLException {
        Statement statement = getRequest();
        //Создание массива с названиями колонок
        ArrayList<String> columnsNames = getTableColumns(table);
        StringBuilder request = new StringBuilder("UPDATE " + table + " SET ");
        //Установка значений
        for (int i = 1; i < values.size(); i++) {
            request.append(columnsNames.get(i)).append(" = '").append(values.get(i)).append("',");
        }
        request.append(" WHERE ").append(columnsNames.get(0)).append(" = '").append(values.get(0)).append("'");
        statement.executeUpdate(request.toString());
    }

    /**
     * Взятие Id строки из базы данных
     *
     * @param table     - название таблицы
     * @param column    - название колонки
     * @param condition - условие поиска
     * @return - id в базе данных
     * @throws SQLException - ошибки связанные с базой данных
     */
    public static int getId(String table, String column, String condition) throws SQLException {
        ResultSet rs = getRequest().executeQuery("select " + column + " from " + table + " where " + condition);
        rs.next();
        return rs.getInt(1);
    }

    /**
     * Удаление строки из базы данных
     *
     * @param id     - id строки
     * @param table  - таблица
     * @param column - название колонки с id
     * @throws SQLException - ошибки связанные с базой данных
     */
    public static void delete(int id, String table, String column) throws SQLException {
        Statement statement = getRequest();
        statement.executeUpdate("delete from " + table + " where " + column + " = " + id);
    }

    /**
     * Использование подготовленных запросов
     *
     * @param prSt - название запроса
     * @return - ResultSet из базы данных
     * @throws SQLException - ошибки связанные с базой данных
     */
    public static ResultSet usePreparedStatement(String prSt) throws SQLException {
        return connection.prepareCall("{call " + prSt + "()}").executeQuery();
    }

    /**
     * Использование подготовленных запросов для внесение в базу данных
     *
     * @param prSt       - название запроса
     * @param userValues - значения
     * @return - ResultSet из базы данных
     * @throws SQLException - ошибки связанные с базой данных
     */
    public static ResultSet usePreparedStatement(String prSt, String userValues) throws SQLException {
        return connection.prepareCall("{call " + prSt + userValues + "}").executeQuery();
    }
}

