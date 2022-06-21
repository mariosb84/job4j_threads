package ru.job4j.waitnotifynotifyall;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    private final Object monitor = this;

    @GuardedBy("this")
    private final Queue<T> queue = new LinkedList<>();
    private final int  limit;

    public SimpleBlockingQueue(int limit) {
        this.limit = limit;
    }

    public void offer(T value) throws InterruptedException {
        synchronized (monitor) {
            while (this.queue.size() == this.limit) {
                System.out.println("Overflow");
                monitor.wait();
            }
            queue.add(value);
            System.out.println("Added");
            monitor.notifyAll();
        }
    }

    public T poll() {
        synchronized (monitor) {
            while (queue.isEmpty()) {
                try {
                    System.out.println("Empty");
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("Ok");
            return queue.poll();

        }
    }

}
