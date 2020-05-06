package ru.com.sev.sbulygin.sqlcmd.controller;

import ru.com.sev.sbulygin.sqlcmd.model.DatabaseManager;
import ru.com.sev.sbulygin.sqlcmd.model.JDBCDatabaseManager;
import ru.com.sev.sbulygin.sqlcmd.view.Consol;
import ru.com.sev.sbulygin.sqlcmd.view.View;

/**
 * Class   Main
 * Created 02/04/2020 - 22:24
 * Project SQLCmd
 * Author  Sergey Bulygin
 */
public class Main {
    public static void main(String[] args) {
        View view = new Consol();
        DatabaseManager manager= new JDBCDatabaseManager();
        MainController controller = new MainController(view, manager);
        controller.run();
    }
}
