package ru.com.sev.sbulygin.sqlcmd.model;

import java.util.Arrays;

/**
 * Class   DataSet
 * Created 17/04/2020 - 12:11
 * Project SQLCmd
 * Author  Sergey Bulygin
 */
public class DataSet {



    static class Data {
        private String name;
        private Object value;

        public Data(String name, Object value) {
            this.name = name;
            this.value = value;
        }

        public String getName() {
            return name;
        }

        public Object getValue() {
            return value;
        }
    }

    public Data[] data = new Data[100]; //TODO magic number 100.
    public int position = 0;

    public void put(String name, Object value) {
        for (int index = 0; index < position; index++) {
            if (data[index].getName().equals(name)) {
            data[index].value = value;
            return;
            }
        }
        data[position++] = new Data(name, value);
    }

    public Object[] getValues(){
        Object[] result = new Object[position];
        for (int i = 0; i < position; i++) {
            result[i] = data[i].getValue();
        }
        return result;
    }

    public String[] getNames(){
        String[] result = new String[position];
        for (int i = 0; i < position; i++) {
            result[i] = data[i].getName();
        }
        return result;
    }

    public Object get(String name) {
        for (int i = 0; i < position; i++) {
            if (data[i].getName().equals(name)) {
                return data[i].getValue();
            }
        }
        return null;
    }

    public void updateFrom(DataSet newValue) {
        for (int index = 0; index < newValue.position; index++) {
            Data data = newValue.data[index];
            this.put(data.name, data.value);
        }
    }

    @Override
    public String toString() {
        return "DataSet{\n" +
                "names:" + Arrays.toString(getNames()) + "\n" +
                "values:" + Arrays.toString(getValues()) + "\n" +
                "}" + "\n";
    }
}
