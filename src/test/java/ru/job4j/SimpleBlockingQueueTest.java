package ru.job4j;

import org.junit.Test;
import ru.job4j.waitnotifynotifyall.SimpleBlockingQueue;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleBlockingQueueTest {
    /**
     * Класс описывает нити с блокированной очередью.
     */

    @Test
    public void whenAddFiveThenReturnFive() throws InterruptedException {
        /* Создаем объект монитор. */
        final SimpleBlockingQueue<Integer> simpleBlockingQueue = new SimpleBlockingQueue<>(3);
        /* Создаем нити. */
        Thread first = new Thread(
                () -> {
                    try {
                        simpleBlockingQueue.offer(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        simpleBlockingQueue.offer(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        simpleBlockingQueue.offer(3);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        simpleBlockingQueue.offer(4);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        simpleBlockingQueue.offer(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        Thread second = new Thread(
                () -> {
                    simpleBlockingQueue.poll();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    simpleBlockingQueue.poll();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    simpleBlockingQueue.poll();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    simpleBlockingQueue.poll();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    simpleBlockingQueue.poll();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        /* Запускаем нити. */
        first.start();
        second.start();
        /* Заставляем главную нить дождаться выполнения наших нитей. */
        first.join();
        second.join();
        /* Проверяем результат. */
        assertThat(first.getState(), is(Thread.State.TERMINATED));
        assertThat(second.getState(), is(Thread.State.TERMINATED));
    }

}
