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
        StringBuilder output = new StringBuilder();
        try (BufferedInputStream in =
                     new BufferedInputStream(new FileInputStream(file))) {
            int data;
            while ((data = in.read()) != -1) {
                if (filter.test((char) 0x80)) {
                    output.append((char) data);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

}
