package ru.com.sev.sbulygin.sqlcmd.view;

import java.util.Scanner;

/**
 * Class   Consol
 * Created 29/04/2020 - 12:12
 * Project SQLCmd
 * Author  Sergey Bulygin
 */
public class Consol implements View{
    @Override
    public void write(String massage) {
        System.out.println(massage);
    }

    @Override
    public String read() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
