package ru.com.sev.sbulygin.sqlcmd.model;

import java.util.Arrays;

/**
 * Class   InMemoryDatabaseManager
 * Created 24/04/2020 - 16:19
 * Project SQLCmd
 * Author  Sergey Bulygin
 */
public class InMemoryDatabaseManager implements DatabaseManager{

    public static final String TABLE_NAME = "users";
    private DataSet[] data = new DataSet[1000];
    private int position = 0;

    @Override
    public DataSet[] getTableData(String tableName) {
        return Arrays.copyOf(data, position);
    }

    @Override
    public String[] getTableNames() {
        return new String[]{TABLE_NAME};
    }

    @Override
    public void connect(String database, String user, String password) {

    }

    @Override
    public void clear(String tableName) {
        data = new DataSet[1000];
        position = 0;
    }

    @Override
    public void create(DataSet input) {
        data[position] = input;
        position++;
    }

    @Override
    public void update(String tableName, int id, DataSet newValue) {
        for (int index = 0; index < position ; index++) {
            if (data[index].get("id").equals(id)) {
                data[index].updateFrom(newValue);
            }
        }
    }

    @Override
    public String[] getTableColumns(String tableName) {
        return new String[0];
    }
}
