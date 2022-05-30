package ru.job4j.thread;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;

public class Wget implements Runnable {
    private final String url;
    private final float speed;
    private final String fileName;

    public Wget(String url, float speed, String fileName) {
        this.url = url;
        this.speed = speed;
        this.fileName = fileName;
    }

    @Override
    public void run() {
        /* Задержка, исходя из скорости  в МБайт/сек*/
        long delay;
        long downloadData = 0;
        try (BufferedInputStream in = new BufferedInputStream(new URL(this.url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(this.fileName)) {
            byte[] dataBuffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
                Date date = new Date();
                downloadData += bytesRead;
                if (downloadData >= ((this.speed * 1048576L))) {
                    Date dateDelay = new Date();
                    delay = (dateDelay.getTime() - date.getTime());
                    Thread.sleep(delay < 1000 ? 1000 - delay : delay);
                    downloadData = 0;
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        if (args.length != 3) {
            throw new IllegalArgumentException(
                    "url is null, speed is null, fileName is null.");
        }
        String url = args[0];
        float speed = Float.parseFloat(args[1]);
        String fileName = args[2];
        Thread wget = new Thread(new Wget(url, speed, fileName));
        wget.start();
        wget.join();
    }
}
