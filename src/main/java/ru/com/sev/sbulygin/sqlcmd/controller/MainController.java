package ru.com.sev.sbulygin.sqlcmd.controller;

import ru.com.sev.sbulygin.sqlcmd.model.DatabaseManager;
import ru.com.sev.sbulygin.sqlcmd.model.InMemoryDatabaseManager;
import ru.com.sev.sbulygin.sqlcmd.view.Consol;
import ru.com.sev.sbulygin.sqlcmd.view.View;

/**
 * Class   MainControler
 * Created 29/04/2020 - 12:45
 * Project SQLCmd
 * Author  Sergey Bulygin
 */
public class MainController {
    public static void main(String[] args) {
        View view = new Consol();
        DatabaseManager manager = new InMemoryDatabaseManager();

        view.write("Hello user!");
        view.write("Please enter the database name, username and password, in the format:");
        view.write("database|username|password");

        String report = view.read();
        String[] data = report.split("\\|");
        String database = data[0];
        String user = data[1];
        String password = data[2];
        while (true) {
            try {
                manager.connect(database, user, password);
                break;
            } catch (Exception e) {
                String massage = e.getMessage();
                if (e.getCause() != null) {
                    massage += " " + e.getCause() + e.getMessage();
                }
                view.write("Error! Because of: " + massage);
                view.write("Try again!");
            }
        }
        view.write("Complete!");
    }
}
