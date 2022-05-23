package ru.job4j.concurrent;

public class ThreadState {
    public static void main(String[] args) {
        Thread first = new Thread(
                () -> {
                });
        Thread second = new Thread(
                () -> {
                });
        System.out.println("First:" + first.getState());
        System.out.println("Second:" + second.getState());
        System.out.println();
        first.start();
        second.start();
        while (!(first.getState() == Thread.State.TERMINATED
        && second.getState() == Thread.State.TERMINATED)) {
            System.out.println("First:" + first.getState());
            System.out.println();
            System.out.println("Second:" + second.getState());
            System.out.println();
        }
        System.out.println("First:" + first.getState());
        System.out.println("Second:" + second.getState());
        System.out.println();
        System.out.println("The Threads are terminated");
    }
}
