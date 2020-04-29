package ru.com.sev.sbulygin.sqlcmd.view;

/**
 * Class   Console
 * Created 29/04/2020 - 12:10
 * Project SQLCmd
 * Author  Sergey Bulygin
 */
public interface View {
    void write (String massage);
    String read();
}
