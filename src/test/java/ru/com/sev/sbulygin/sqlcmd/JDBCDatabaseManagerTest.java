package ru.com.sev.sbulygin.sqlcmd;

import org.junit.Before;
import org.junit.Test;
import ru.com.sev.sbulygin.sqlcmd.model.DataSet;
import ru.com.sev.sbulygin.sqlcmd.model.DatabaseManager;
import ru.com.sev.sbulygin.sqlcmd.model.InMemoryDatabaseManager;
import ru.com.sev.sbulygin.sqlcmd.model.JDBCDatabaseManager;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Class   DataBaseManagerTest
 * Created 13/04/2020 - 17:33
 * Project SQLCmd
 * Author  Sergey Bulygin
 */
public class JDBCDatabaseManagerTest {

    private DatabaseManager manager;

    @Before
    public void setup() {
        manager = new JDBCDatabaseManager();
        manager.connect("sqlcmd", "postgres", "bbfd50ago");
    }

    @Test
    public void testGetAllTableNames() {
        String[] tableNames = manager.getTableNames();
        assertEquals("[users]", Arrays.toString(tableNames));
     }

    @Test
    public void testGetTableData() {
        manager.clear("users");
        DataSet input = new DataSet();
        input.put("name", "Ivan");
        input.put("password", "pass");
        input.put("id", 13);
        manager.create("users", input);
        DataSet[] user = manager.getTableData("users");
        assertEquals(1, user.length);
        DataSet users = user[0];
        assertEquals("[name, password, id]", Arrays.toString(users.getNames()));
        assertEquals("[Ivan, pass, 13]", Arrays.toString(users.getValues()));
    }

    @Test
    public void testUpdateTableData() {
        manager.clear("users");
        DataSet input = new DataSet();
        input.put("name", "Ivan");
        input.put("password", "pass");
        input.put("id", 13);
        manager.create("users", input);
        DataSet newValue = new DataSet();
        newValue.put("password", "passTwo");
        manager.update("users",13, newValue);
        DataSet[] user = manager.getTableData("users");
        assertEquals(1, user.length);
        DataSet users = user[0];
        assertEquals("[name, password, id]", Arrays.toString(users.getNames()));
        assertEquals("[Ivan, passTwo, 13]", Arrays.toString(users.getValues()));
    }
}
