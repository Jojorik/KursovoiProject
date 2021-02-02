package sample.database;


import sample.objects.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DatabaseService extends Database {

    public static ArrayList<Service> getServices() {

        ArrayList<Service> services = new ArrayList<>();
        try {
            ResultSet rs = Database.select("service");
            while (rs.next()) {
                Service service = new Service();

                service.setID(rs.getInt(1));
                service.setTitle(rs.getString(2));
                service.setCost(rs.getFloat(3));
                service.setDurationInSeconds(rs.getInt(4));
                service.setDescription(rs.getString(5));
                service.setSale(rs.getDouble(6));
                service.setImagePath(rs.getString(7));

                services.add(service);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return services;
    }

    public static void delete(Service service) {
        try {
            Database.delete(service.getID(), "service", "ID");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
