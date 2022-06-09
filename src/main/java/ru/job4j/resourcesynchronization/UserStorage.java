package ru.job4j.resourcesynchronization;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import java.util.*;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final Map<Integer, User> userStorages = new HashMap<>();

    public synchronized boolean add(User user) {
        if (!userStorages.containsKey(user.getId())) {
            userStorages.putIfAbsent(user.getId(), user);
            return true;
        }
        return false;
    }

    public synchronized boolean update(User user) {
        if (userStorages.containsKey(user.getId())) {
            userStorages.replace(user.getId(), user);
            return true;
        }
        return false;
    }

    public synchronized boolean delete(User user) {
        if (userStorages.containsKey(user.getId())) {
            userStorages.remove(user.getId());
            return true;
        }
        return false;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User userFrom = userStorages.get(fromId);
        User userTo = userStorages.get(toId);
        if (userFrom.getAmount() >= amount) {
         userFrom.setAmount(userFrom.getAmount() - amount);
         userTo.setAmount(userTo.getAmount() + amount);
         return true;
       }
     return false;
    }

}
