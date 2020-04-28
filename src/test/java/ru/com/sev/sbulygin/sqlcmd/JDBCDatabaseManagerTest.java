package ru.com.sev.sbulygin.sqlcmd;

import ru.com.sev.sbulygin.sqlcmd.model.DatabaseManager;
import ru.com.sev.sbulygin.sqlcmd.model.JDBCDatabaseManager;

/**
 * Class   JDBCDatabaseManagerTest
 * Created 28/04/2020 - 10:22
 * Project SQLCmd
 * Author  Sergey Bulygin
 */
public class JDBCDatabaseManagerTest extends DatabaseManagerTest {

    @Override
    protected DatabaseManager getDataBaseManager() {
        return new JDBCDatabaseManager();
    }
}
