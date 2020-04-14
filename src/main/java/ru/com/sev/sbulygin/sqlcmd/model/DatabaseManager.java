package ru.com.sev.sbulygin.sqlcmd.model;

import java.sql.*;
import java.util.Arrays;
import java.util.Random;

/**
 * Class   Main
 * Created 02/04/2020 - 22:24
 * Project SQLCmd
 * Author  Sergey Bulygin
 */
public class DatabaseManager {

    private Connection connection;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String database = "sqlcmd";
        String user = "postgres";
        String password = "bbfd50ago";
        DatabaseManager manager = new DatabaseManager();
        manager.connect(database, user, password);
        Connection connection = manager.getConnection();

        //insert
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO users (name, password)" +
                "VALUES ('Ivan', 'Budko')");
        stmt.close();

        //select
        stmt = connection.createStatement();
        String tableName = "users";
        ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
        while (rs.next()) {
            System.out.println("id:" + rs.getString("id"));
            System.out.println("name:" + rs.getString("name"));
            System.out.println("password:" + rs.getString("password"));
            System.out.println("----------");
        }
        rs.close();
        stmt.close();

        // table names
        String[] tables = manager.getTableNames();
        System.out.println(Arrays.toString(tables));

        //delete
        stmt = connection.createStatement();
        stmt.executeUpdate("DELETE FROM users " +
                "WHERE id > 5 AND ID < 100");
        stmt.close();

        //update
        PreparedStatement ps = connection.prepareStatement(
                "UPDATE users SET password = ? WHERE id > 3");
        String pass = "password_" + new Random().nextInt();
        ps.setString(1, pass);
        ps.executeUpdate();
        ps.close();

        rs.close();
        stmt.close();

        connection.close();
    }

    public String[] getTableNames() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema='public' AND table_type='BASE TABLE'");
            String[] tables = new String[100];
            int index = 0;
            while (rs.next()) {
                tables[index++] = rs.getString("table_name");
            }
            tables = Arrays.copyOf(tables, index, String[].class);
            rs.close();
            stmt.close();
            return tables;
        } catch (SQLException e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    private Connection getConnection() {
        return connection;
    }

    public void connect(String database,
                                            String user,
                                            String password) {
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("Please add jdbc jar to project.");
                e.printStackTrace();
            }
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/" + database, user, password);
        } catch (SQLException e) {
            System.out.println(String.format("Can't get connection for database:%s user:%s.", database, user));
            e.printStackTrace();
            connection = null;
        }
    }
}
