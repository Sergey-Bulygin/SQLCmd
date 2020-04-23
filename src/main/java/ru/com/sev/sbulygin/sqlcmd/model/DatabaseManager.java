package ru.com.sev.sbulygin.sqlcmd.model;

import java.sql.*;
import java.util.Arrays;

/**
 * Class   Main
 * Created 02/04/2020 - 22:24
 * Project SQLCmd
 * Author  Sergey Bulygin
 */
public class DatabaseManager {

    private Connection connection;

    public String[] getTableNames() {
        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("SELECT table_name FROM information_schema.tables " +
                                                           "WHERE table_schema='public' AND table_type='BASE TABLE'"));
            String[] tables = new String[100];
            int index = 0;
            while (rs.next()) {
                tables[index++] = rs.getString("table_name");
            }
            tables = Arrays.copyOf(tables, index, String[].class);
            rs.close();
            stmt.close();
            return tables;
        } catch (SQLException e) {
            e.printStackTrace();
            return new String[0];
        }
    }

    private Connection getConnection() {
        return connection;
    }

    public void connect(String database,
                        String user,
                        String password) {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Please add jdbc jar to project.");
            e.printStackTrace();
        }
        try {
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://127.0.0.1:5432/" + database, user, password);
        } catch (SQLException e) {
            System.out.println(String.format("Can't get connection for database:%s user:%s.", database, user));
            e.printStackTrace();
            connection = null;
        }
    }

    public DataSet[] getTableData(String tableName) {
        try {
            int size = getSize(tableName);
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(String.format("SELECT * FROM %s", tableName));
            ResultSetMetaData rsmt = rs.getMetaData();
            DataSet[] result = new DataSet[size];
            int index = 0;
            while (rs.next()) {
                DataSet dataSet = new DataSet();
                result[index++] = dataSet;
                for (int i = 1; i <= rsmt.getColumnCount(); i++) {
                    dataSet.put(rsmt.getColumnName(i), rs.getObject(i));
                }
            }
            rs.close();
            stmt.close();
            return result;
        }catch (SQLException e) {
            e.printStackTrace();
            return new DataSet[0];
        }
    }

    private int getSize(String tableName) throws SQLException {
        Statement stmt = connection.createStatement();
        ResultSet rsCount = stmt.executeQuery(String.format("SELECT COUNT(*) FROM %s", tableName));
        rsCount.next();
        int size = rsCount.getInt(1);
        rsCount.close();
        return size;
    }

    public void clear(String tableName) {
        try{
            Statement stmt = connection.createStatement();
            stmt.executeUpdate(String.format("DELETE FROM " + tableName));
            stmt.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void create(DataSet input) {
        try{
            Statement stmt = connection.createStatement();
            String tableNames = getNamesFormated(input, "%s,");
            String values = getValuesFormated(input, "'%s' ,");

            stmt.executeUpdate(String.format("INSERT INTO users (%s)VALUES (%s)", tableNames, values));
            stmt.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getValuesFormated(DataSet input, String format) {
        StringBuilder values = new StringBuilder();
        for (Object value : input.getValues()) {
            values.append(String.format(format, value));
        }
        values = new StringBuilder(values.substring(0, values.length() - 1));
        return values.toString();
    }

    public void update(String tableName, int id, DataSet newValue) {
        try{
            String tableNames = getNamesFormated(newValue, "%s = ?,");
            PreparedStatement ps = connection.prepareStatement(
                    String.format("UPDATE %s SET %s WHERE id = ?", tableName, tableNames));
            int index = 1;
            for( Object value : newValue.getValues()) {
            ps.setObject(index, value);
            index++;
            }
            ps.setInt(index, id);
            ps.executeUpdate();
            ps.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String getNamesFormated(DataSet newValue, String format) {
        StringBuilder string = new StringBuilder();
        for (String name : newValue.getNames()) {
            string.append(String.format(format, name));
        }
        string = new StringBuilder(string.substring(0, string.length() - 1));
        return string.toString();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        String database = "sqlcmd";
        String user = "postgres";
        String password = "bbfd50ago";
        DatabaseManager manager = new DatabaseManager();
        manager.connect(database, user, password);
        Connection connection = manager.getConnection();

        //delete
        manager.clear("users");

        //insert
        DataSet data = new DataSet();
        data.put("id", 13);
        data.put("name", "Ivan");
        data.put("password", "pass");
        manager.create(data);

        //select
        String[] tables = manager.getTableNames();
        System.out.println(Arrays.toString(tables));
        String tableName = "users";
        DataSet[] result = manager.getTableData(tableName);
        System.out.println(Arrays.toString(result));


        //update
        connection.close();
    }
}
