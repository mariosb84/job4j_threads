package ru.job4j;

import org.junit.Test;
import ru.job4j.resourcesynchronization.User;
import ru.job4j.resourcesynchronization.UserStorage;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class UserStorageTest {

    private static class ThreadStorage extends Thread {
        private final UserStorage userStorage;

        private ThreadStorage(final UserStorage userStorage) {
            this.userStorage = userStorage;
        }

        @Override
        public void run() {
            this.userStorage.add(new User(1, 100));
            this.userStorage.add(new User(2, 200));
            this.userStorage.add(new User(3, 300));
            this.userStorage.add(new User(4, 400));
        }
    }

    @Test
    public void whenTransferFrom2To1ThenTrue() throws InterruptedException {
        final UserStorage userStorage = new UserStorage();
        Thread first = new ThreadStorage(userStorage);
        Thread second = new ThreadStorage(userStorage);
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(userStorage.transfer(2, 1, 50), is(true));
    }

    @Test
    public void whenUpdate3ThenTrue() throws InterruptedException {
        final UserStorage userStorage = new UserStorage();
        Thread first = new ThreadStorage(userStorage);
        Thread second = new ThreadStorage(userStorage);
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(userStorage.update(new User(3, 1000)), is(true));
    }

    @Test
    public void whenDelete4ThenTrue() throws InterruptedException {
        final UserStorage userStorage = new UserStorage();
        Thread first = new ThreadStorage(userStorage);
        Thread second = new ThreadStorage(userStorage);
        first.start();
        second.start();
        first.join();
        second.join();
        assertThat(userStorage.delete(new User(4, 400)), is(true));
    }
}
