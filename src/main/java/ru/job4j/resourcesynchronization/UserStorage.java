package ru.job4j.resourcesynchronization;

import net.jcip.annotations.ThreadSafe;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ThreadSafe
public class UserStorage {

    private final List<User> userStorages = new ArrayList<>();


    public boolean add(User user) {
        if (findById(user.getId()).isEmpty()) {
            userStorages.add(user);
            return true;
        }
        return false;
    }

    public boolean update(User user) {
        if (findById(user.getId()).isPresent()) {
            userStorages.set(user.getId(), user);
            return true;
        }
        return false;
    }

    public boolean delete(User user) {
        if (findById(user.getId()).isPresent()) {
            userStorages.remove(user.getId());
            return true;
        }
        return false;
    }

    public boolean transfer(int fromId, int toId, int amount) {
        User userFrom = userStorages.get(findById(fromId).
             get().getId());
        User userTo = userStorages.get(findById(toId).
             get().getId());
     if (userFrom.getAmount() >= userTo.getAmount()) {
         userFrom.setAmount(userFrom.getAmount() - amount);
         userTo.setAmount(userTo.getAmount() + amount);
         return true;
       }
     return false;
    }

    private Optional<User> findById(int id) {
        return userStorages
                .stream()
                .filter(u -> u.getId() == (id))
                .findFirst();
    }

    public static void main(String[] args) {
        UserStorage storage = new UserStorage();

        System.out.println(storage.add(new User(1, 100)));
        System.out.println(storage.add(new User(2, 200)));
        System.out.println(storage.userStorages.get(0).getAmount());
        System.out.println(storage.userStorages.get(1).getAmount());
        System.out.println();
        System.out.println(storage.transfer(1, 2, 50));
        System.out.println();
        System.out.println(storage.userStorages.get(0).getAmount());
        System.out.println(storage.userStorages.get(1).getAmount());
    }

}
