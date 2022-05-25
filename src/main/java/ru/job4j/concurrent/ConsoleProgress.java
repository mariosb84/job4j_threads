package ru.job4j.concurrent;

public class ConsoleProgress implements Runnable {

    @Override
    public void run() {
        char[] process = new char[]{'\'', '|', '/'};
        for (int index = 0; !Thread.currentThread().isInterrupted(); index++) {
            System.out.print("\rLoading ...|" + process[index]);
            if (index == process.length - 1) {
                index = 0;
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
                }
             }
          }

    public static void main(String[] args) throws InterruptedException {
        Thread progress = new Thread(new ConsoleProgress());
        progress.start();
        Thread.sleep(1000); /* симулируем выполнение параллельной задачи в течение 1 секунды. */
        progress.interrupt();
    }
}
