package ru.com.sev.sbulygin.sqlcmd;

import org.junit.Before;
import ru.com.sev.sbulygin.sqlcmd.model.DatabaseManager;
import ru.com.sev.sbulygin.sqlcmd.model.InMemoryDatabaseManager;
import ru.com.sev.sbulygin.sqlcmd.model.JDBCDatabaseManager;

/**
 * Class   InMemoryDatabaseManagerTest
 * Created 28/04/2020 - 10:08
 * Project SQLCmd
 * Author  Sergey Bulygin
 */
public class InMemoryDatabaseManagerTest extends DatabaseManagerTest {


    @Override
    public DatabaseManager getDataBaseManager() {
        return  new InMemoryDatabaseManager();
    }
}
