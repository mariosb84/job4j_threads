package ru.job4j.waitnotifynotifyall;

public class CountBarrier {
    private final Object monitor = this;

    private final int total;

    private int count = 0;

    public CountBarrier(final int total) {
        this.total = total;
    }

    public void count() {
        synchronized (monitor) {
            count++;
            monitor.notifyAll();
        }
    }

    public void await() {
        synchronized (monitor) {
            while (!(count >= total)) {
                try {
                    monitor.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }

    public static void main(String[] args) {
        CountBarrier countBarrier = new CountBarrier(5);
        Thread countOne = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " countOne");
                    countBarrier.count();
                },
                "Count"
        );
        Thread countTwo = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " countTwo");
                    countBarrier.count();
                },
                "Count"
        );
        Thread countThree = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " countThree");
                    countBarrier.count();
                },
                "Count"
        );
        Thread countFour = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " countFour");
                    countBarrier.count();
                },
                "Count"
        );
        Thread countFive = new Thread(
                () -> {
                    System.out.println(Thread.currentThread().getName() + " countFive");
                    countBarrier.count();
                },
                "Count"
        );
        Thread await = new Thread(
                () -> {
                    countBarrier.await();
                    System.out.println(Thread.currentThread().getName() + " await ");
                },
                "Await "
        );
        countOne.start();
        countTwo.start();
        countThree.start();
        countFour.start();
        countFive.start();
        await.start();
    }

}
