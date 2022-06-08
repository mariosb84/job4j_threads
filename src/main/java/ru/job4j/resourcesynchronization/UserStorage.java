package ru.job4j.resourcesynchronization;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ThreadSafe
public class UserStorage {
    @GuardedBy("this")
    private final List<User> userStorages = new ArrayList<>();


    public synchronized boolean add(User user) {
        if (findById(user.getId()).isEmpty()) {
            userStorages.add(user);
            return true;
        }
        return false;
    }

    public synchronized boolean update(User user, int id, int amount) {
        if (findById(user.getId()).isPresent()) {
            User userUpdate = findById(user.getId()).get();
            userUpdate.setId(id);
            userUpdate.setAmount(amount);
            return true;
        }
        return false;
    }

    public synchronized boolean delete(User user) {
        if (findById(user.getId()).isPresent()) {
            userStorages.remove(user);
            return true;
        }
        return false;
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        User userFrom = findById(fromId).orElse(null);
        User userTo = findById(toId).orElse(null);
        assert userFrom != null;
        assert userTo != null;
        if (userFrom.getAmount() >= userTo.getAmount()) {
         userFrom.setAmount(userFrom.getAmount() - amount);
         userTo.setAmount(userTo.getAmount() + amount);
         return true;
       }
     return false;
    }

    private synchronized Optional<User> findById(int id) {
        return userStorages
                .stream()
                .filter(u -> u.getId() == (id))
                .findFirst();
    }

    public static void main(String[] args) throws InterruptedException {
        UserStorage storage = new UserStorage();
        Thread first = new Thread(
                () -> {
                    try {
                        Thread.sleep(1000);
                        System.out.println(storage.add(new User(1, 100)));
                        System.out.println(storage.add(new User(2, 200)));
                        System.out.println();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
        Thread second = new Thread(
                () -> {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(storage.transfer(2, 1, 50));
                    System.out.println();
                });
        first.start();
        second.start();
        first.join();
        second.join();
    }

}
