package ru.job4j.pools;


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelIndexSearch<T> extends RecursiveTask<Integer> {
    private final T[] array;
    private final T subject;
    private final int from;
    private final int to;

    public ParallelIndexSearch(T[] array, T subject, int from, int to) {
        this.array = array;
        this.subject = subject;
        this.from = from;
        this.to = to;
    }

    @Override
    protected Integer compute() {
        if (to - from <= 10) {
            return linearSearch();
        }
        int mid = (from + to) / 2;
        ParallelIndexSearch<T> leftParallelSearch = new ParallelIndexSearch<>(array, subject, from, mid);
        ParallelIndexSearch<T> rightParallelSearch = new ParallelIndexSearch<>(array, subject, mid + 1, to);
        leftParallelSearch.fork();
        rightParallelSearch.fork();
        int leftResult = leftParallelSearch.join();
        int rightResult = rightParallelSearch.join();
        return Math.max(leftResult, rightResult);
    }

    private int linearSearch() {
        int result = -1;
        for (int i = from; i <= to; i++) {
            if (array[i].equals(subject)) {
                result = i;
                break;
            }
        }
        return result;
    }

    public  Integer search(T[] array, T subject) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelIndexSearch<T>(array, subject, 0, array.length - 1));
    }
}
