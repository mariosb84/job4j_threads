package ru.job4j.pools;

import ru.job4j.waitnotifynotifyall.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

public class ThreadPool implements Runnable {

    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(3);

    public ThreadPool() {
    }

    public void work(Runnable job) throws InterruptedException {
    tasks.offer(job);
    }

    public void shutdown() {
        for (Thread thread : threads) {
            thread.interrupt();
        }
    }

    @Override
    public void run() {
        int size = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < size; i++) {
            Thread thread = new Thread(
                    () -> {
                        while (!Thread.currentThread().isInterrupted()) {
                            try {
                                tasks.poll();
                            } catch (InterruptedException e) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    }
            );
            thread.start();
            threads.add(thread);
        }
    }
}
