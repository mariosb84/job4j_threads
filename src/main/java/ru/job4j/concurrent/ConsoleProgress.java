package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        int index = 0;
        char[] process = new char[]{
                '\\', '/', '|'
        };
        while (!Thread.currentThread().isInterrupted()) {
            System.out.print("\rLoading ..." + process[index++]);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                }
            if (index == process.length) {
                index = 0;
               }
             }
          }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(1000);
        progress.interrupt();
    }
}
