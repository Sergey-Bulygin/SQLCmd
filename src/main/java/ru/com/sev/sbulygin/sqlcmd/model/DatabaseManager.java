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
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/sqlcmd", "postgres", "bbfd50ago"); {
        }
        //insert
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO users (name, password)" +
                "VALUES ('Ivan', 'Budko')");
        stmt.close();

        //select
        stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM users WHERE id > 5");
        while (rs.next()) {
            System.out.println("id:" + rs.getString("id"));
            System.out.println("name:" + rs.getString("name"));
            System.out.println("password:" + rs.getString("password"));
            System.out.println("----------");
        }
        rs.close();
        stmt.close();

        // table names
        stmt = connection.createStatement();
        rs = stmt.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema='public' " +
                "AND table_type='BASE TABLE'");
        String[] table = new String[100];
        int index = 0;
        while (rs.next()) {
            table[index++] = rs.getString("table_name");
        }
        table = Arrays.copyOf(table, index + 1, String[].class);

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
}
