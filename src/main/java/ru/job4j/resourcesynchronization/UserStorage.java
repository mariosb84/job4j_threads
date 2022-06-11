package ru.job4j.resourcesynchronization;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.*;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final Map<Integer, User> userStorages = new HashMap<>();

    public synchronized boolean add(User user) {
        return userStorages.putIfAbsent(user.getId(), user) == null;
    }

    public synchronized boolean update(User user) {
        return userStorages.replace(user.getId(),
                userStorages.get(user.getId()), user);
    }

    public synchronized boolean delete(User user) {
        return userStorages.remove(user.getId(), user);
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User userFrom = userStorages.get(fromId);
        User userTo = userStorages.get(toId);
        if (userFrom != null
                && userTo != null
                && userFrom.getAmount() >= amount) {
         userFrom.setAmount(userFrom.getAmount() - amount);
         userTo.setAmount(userTo.getAmount() + amount);
         return true;
       }
     return false;
    }

}
