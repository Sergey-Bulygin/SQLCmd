package ru.com.sev.sbulygin.sqlcmd.controller;

import ru.com.sev.sbulygin.sqlcmd.model.DatabaseManager;
import ru.com.sev.sbulygin.sqlcmd.view.View;

import java.util.Arrays;

/**
 * Class   MainControler
 * Created 29/04/2020 - 12:45
 * Project SQLCmd
 * Author  Sergey Bulygin
 */
public class MainController {

    private final View view;
    private final DatabaseManager manager;

    public MainController(View view, DatabaseManager manager) {
        this.view = view;
        this.manager = manager;
    }

    public void run() {
        connectToDb();
        boolean menu = true;
        while (menu) {
            view.write("Enter a command (or help for reference): ");
            String command = view.read();
            switch (command) {
                case "list":
                    doList();
                    break;
                case "help":
                    doHelp();
                    break;
                case "exit":
                    view.write("Good luck.");
                    menu = false;
                    break;
                default:
                    view.write("Nonexistent command." + command);
                    break;
            }
        }
    }

    private void doHelp() {
        view.write("Existing teams: ");
        view.write("\tlist");
        view.write("\t\tGet a list of all database tables.");
        view.write("\texit");
        view.write("\t\tExit from the program.");
        view.write("\thelp");
        view.write("\t\tTo list help commands.");
    }

    private void doList() {
        String[] tableNames = manager.getTableNames();
        String massage = Arrays.toString(tableNames);
        view.write(massage);
    }

    private void connectToDb() {
        view.write("Hello user!");
        view.write("Please enter the database name, username and password, in the format:");
        view.write("database|username|password");
        while (true) {
            try {
                String report = view.read();
                String[] data = report.split("\\|");
                if (data.length < 3) {
                    throw new IllegalArgumentException("Incorrect number of parameters separated by '|', expected 3, but there are: " + data.length);
                }
                String database = data[0];
                String user = data[1];
                String password = data[2];
                manager.connect(database, user, password);
                break;
            } catch (Exception e) {
                printError(e);
            }
        }
        view.write("Complete!");
    }

    private void printError(Exception e) {
        String massage = e.getMessage();
        Throwable cause = e.getCause();
        if (cause != null) {
            massage += " " + e.getMessage();
        }
        view.write("Error! Because of: " + massage);
        view.write("Try again!");
    }
}
