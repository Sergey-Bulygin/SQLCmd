package ru.com.sev.sbulygin.sqlcmd.controller;

import java.sql.*;

/**
 * Class   Main
 * Created 02/04/2020 - 22:24
 * Project SQLCmd
 * Author  Sergey Bulygin
 */
public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/sqlcmd", "postgres", "bbfd50ago"); {
        }
        //insert
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("INSERT INTO public.users (name, password)" +
                "VALUES ('Ivan', 'Budko')");
        stmt.close();

        //select
        stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM public.users WHERE id > 5");
        while (rs.next()) {
            System.out.println("id:" + rs.getString("id"));
            System.out.println("name:" + rs.getString("name"));
            System.out.println("password:" + rs.getString("password"));
            System.out.println("----------");

        }
        rs.close();
        stmt.close();
        connection.close();
    }
}
