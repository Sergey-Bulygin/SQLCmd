package ru.com.sev.sbulygin.sqlcmd;

import org.junit.Before;
import org.junit.Test;
import ru.com.sev.sbulygin.sqlcmd.model.DatabaseManager;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

/**
 * Class   DataBaseManagerTest
 * Created 13/04/2020 - 17:33
 * Project SQLCmd
 * Author  Sergey Bulygin
 */
public class DataBaseManagerTest {

    private DatabaseManager manager;

    @Before
    public void setup() {
         manager = new DatabaseManager();
        manager.connect("sqlcmd", "postgres", "bbfd50ago");
    }

    @Test
    public void testGetAllTableNames() {
        String[] tableNames = manager.getTableNames();
        assertEquals("[users]", Arrays.toString(tableNames));
     }
}
