package ru.job4j.nonblockingalgoritm;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cache {

    private final Map<Integer, Base> memory = new ConcurrentHashMap<>();

    public Cache() {
    }

    public boolean add(Base model) {
        return memory.putIfAbsent(model.getId(), model) == null;
    }

    public boolean update(Base model) {
        Base stored = memory.get(model.getId());
        if (stored.getVersion() != model.getVersion()) {
            throw new OptimisticException("Versions are not equal");
        }
        memory.computeIfPresent(model.getId(),
                (key, value) ->  value = new Base(key, value.getVersion() + 1));
        return true;
    }

    public void delete(Base model) {
        this.memory.remove(model.getId());
    }

}
