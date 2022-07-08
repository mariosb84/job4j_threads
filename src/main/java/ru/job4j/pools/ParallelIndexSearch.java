package ru.job4j.pools;


import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ParallelIndexSearch extends RecursiveTask<Integer> {
    private final Object[] array;
    private final Object subject;
    private final int from;
    private final int to;

    public ParallelIndexSearch(Object[] array, Object subject, int from, int to) {
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
        ParallelIndexSearch leftParallelSearch = new ParallelIndexSearch(array, subject, from, mid);
        ParallelIndexSearch rightParallelSearch = new ParallelIndexSearch(array, subject, mid + 1, to);
        leftParallelSearch.fork();
        rightParallelSearch.fork();
        int leftResult = leftParallelSearch.join();
        int rightResult = rightParallelSearch.join();
        return (leftResult == -1) ? rightResult : leftResult;
    }

    private int linearSearch() {
        int result = -1;
        for (int i = from; i <= to; i++) {
            if (array[i].equals(subject)) {
                result = i;
            }
        }
        return result;
    }

    public static Integer search(Object[] array, Object subject) {
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        return forkJoinPool.invoke(new ParallelIndexSearch(array, subject, 0, array.length - 1));
    }
}
