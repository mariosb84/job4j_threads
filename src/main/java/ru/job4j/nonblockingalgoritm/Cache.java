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
        return memory.computeIfPresent(stored.getId(), (key, value) -> {
            if (stored.getVersion() != model.getVersion()) {
                throw new OptimisticException("versions are not equal");
            }
            return new Base(stored.getId(), stored.
                    getVersion() + 1);
        }) != null;
    }

    public void delete(Base model) {
        this.memory.remove(model.getId());
    }

}
