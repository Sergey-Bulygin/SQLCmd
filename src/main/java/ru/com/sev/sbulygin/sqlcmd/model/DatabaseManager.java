package ru.com.sev.sbulygin.sqlcmd.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Class   DatabaseManager
 * Created 24/04/2020 - 15:44
 * Project SQLCmd
 * Author  Sergey Bulygin
 */
public interface DatabaseManager {
    DataSet[] getTableData(String tableName);

    String[] getTableNames();

    void connect(String database, String user, String password);

    void clear(String tableName);

    void create(DataSet input);

    void update(String tableName, int id, DataSet newValue);

    String[] getTableColumns(String tableName);
}
