package ru.job4j.sharedresources;

import net.jcip.annotations.NotThreadSafe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@NotThreadSafe
public class UserCache {
    private final ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();
    private final AtomicInteger id = new AtomicInteger();

    public void add(User user) {
        users.put(id.incrementAndGet(), User.of(user.getName()));
    }

    public User findById(int id) {
        return User.of(users.get(id).getName());
    }

    public List<User> findAll() {
        /*return new ArrayList<>(users.values());*/
        /*Ухдим от использовани "users.values()" напрямую,
        так как она позволяет вносить изменения в обоих направлениях
        **/
        return users.values().stream().
                map(user -> User.of(user.getName())).collect(Collectors.toList());
    }

    public static void main(String[] args) throws InterruptedException {
        UserCache cache = new UserCache();
        User user = User.of("name");
        cache.add(user);
        Thread first = new Thread(
                () -> user.setName("name2")
        );
        first.start();
        first.join();
        System.out.println(cache.findById(1).getName());
        System.out.println(user.getName());
        System.out.println(cache.findAll().get(0).getName());
        cache.findAll().get(0).setName("name3");
        System.out.println(cache.findAll().get(0).getName());
        System.out.println(user.getName());
        System.out.println(cache.users.get(1).getName());
    }
}
