package ru.job4j.resourcesynchronization;

import java.io.*;
import java.util.function.Predicate;

public final class ParseFile implements Content {

    private final File file;

    public ParseFile(File file) {
        this.file = file;
    }

    @Override
    public String getContent(Predicate<Character> filter) {
        String output = "";
        try (BufferedInputStream in =
                     new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = in.read()) > 0) {
                if (filter.test((char) 0x80)) {
                    output += (char) data;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

}
