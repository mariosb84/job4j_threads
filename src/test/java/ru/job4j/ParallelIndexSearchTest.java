package ru.job4j;

import org.junit.Test;
import ru.job4j.pools.ParallelIndexSearch;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ParallelIndexSearchTest {

    @Test
    public void whenLengthSmallThenTen() {
        Integer subject = 7;
        Integer[] array = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7};
        ParallelIndexSearch<Integer> parallelIndexSearch =
                new ParallelIndexSearch<>(array, subject, 0, 7);
        assertThat(parallelIndexSearch.search(array, subject), is(subject));
    }

    @Test
    public void whenLengthMoreThenTen() {
        Integer subject = 0;
        Integer[] array = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
                11, 12, 13, 14, 15};
        ParallelIndexSearch<Integer> parallelIndexSearch =
                new ParallelIndexSearch<>(array, subject, 0, 15);
        assertThat(parallelIndexSearch.search(array, subject), is(subject));
    }

}
