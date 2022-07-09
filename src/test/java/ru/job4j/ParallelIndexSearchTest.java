package ru.job4j;

import org.junit.Test;
import ru.job4j.pools.ParallelIndexSearch;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ParallelIndexSearchTest {
    Integer subject = 7;
    Integer[] array = new Integer[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10,
            11, 12, 13, 14, 15};
    ParallelIndexSearch<Integer> parallelIndexSearch =
            new ParallelIndexSearch<>(array, subject, 0, 10);
    ParallelIndexSearch<Integer> parallelIndexSearch2 =
            new ParallelIndexSearch<>(array, subject, 0, 15);

    @Test
    public void whenLengthSmallThenTen() {
        assertThat(parallelIndexSearch.search(array, subject), is(subject));
    }

    @Test
    public void whenLengthMoreThenTen() {
        assertThat(parallelIndexSearch2.search(array, subject), is(subject));
    }

}
