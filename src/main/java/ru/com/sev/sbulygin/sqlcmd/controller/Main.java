package ru.com.sev.sbulygin.sqlcmd.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class   Main
 * Created 02/04/2020 - 22:24
 * Project SQLCmd
 * Author  Sergey Bulygin
 */
public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/sqlcmd", "postgres", "bbfd50ago"); {
        }
        //insert
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("INSERT INTO public.users (name, password)" + "VALUES ('Ivan', 'Budko')");
    }
}
