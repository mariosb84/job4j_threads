package ru.job4j.sharedresources;

public final class Cache {

    private static Cache cache;

    public synchronized static Cache instOf() {
        if (cache == null) {
            cache = new Cache();
        }
        return cache;
    }

    public static void main(String[] args) throws InterruptedException {
        Thread first = new Thread(
                () -> {
                    try {
                        Thread.sleep(1000);
                        System.out.println(instOf());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
        Thread second = new Thread(
                () -> System.out.println(instOf()));
        first.start();
        second.start();
        first.join();
        second.join();
    }
}