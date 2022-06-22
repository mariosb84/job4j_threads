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

    public synchronized void offer(T value) throws InterruptedException {
            while (this.queue.size() == this.limit) {
                monitor.wait();
            }
            queue.add(value);
            monitor.notifyAll();
    }

    public synchronized T poll() throws InterruptedException {
            while (queue.isEmpty()) {
                    monitor.wait();
                }
                  T rsl = queue.poll();
                  monitor.notifyAll();
                  return rsl;
         }

}
