package ru.job4j.resourcesynchronization;

import java.io.File;

public class Parse {

    public static void main(String[] args) throws InterruptedException {
        ParseFile parseFile = new ParseFile(new File("pom_tmp.xml"));
        Thread first = new Thread(
                () -> System.out.println(parseFile.getContent(x -> true)));
        /*Thread second = new Thread(
                () -> System.out.println(parseFile.getContent(x -> x > 0)));*/
        first.start();
        //second.start();
        first.join();
        //second.join();
    }
}
