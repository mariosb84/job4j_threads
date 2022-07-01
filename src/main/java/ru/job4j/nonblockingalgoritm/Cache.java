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
        return memory.computeIfPresent(model.getId(), (key, value) -> {
            if (value.getVersion() != model.getVersion()) {
                throw new OptimisticException("versions are not equal");
            }
            return new Base(model.getId(), model.
                    getVersion() + 1, model.getName());
        }) != null;
    }

    public void delete(Base model) {
        this.memory.remove(model.getId());
    }

}
