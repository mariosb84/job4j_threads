package ru.job4j.resourcesynchronization;

import java.util.function.Predicate;

public interface Content {

    public String getContent(Predicate<Character> filter);
}
