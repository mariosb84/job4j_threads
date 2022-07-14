package ru.job4j;

import org.junit.Test;
import ru.job4j.pools.RolColSum;

import java.util.concurrent.ExecutionException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RolColSumTest {
    @Test
    public void whenSubsequentSum() {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        RolColSum.Sums[] sums = RolColSum.sum(matrix);
        assertThat(sums[0].getRowSum(), is(6));
        assertThat(sums[0].getColSum(), is(12));
        assertThat(sums[1].getRowSum(), is(15));
        assertThat(sums[1].getColSum(), is(15));
        assertThat(sums[2].getRowSum(), is(24));
        assertThat(sums[2].getColSum(), is(18));
    }

    @Test
    public void whenAsyncSum() throws InterruptedException, ExecutionException {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        RolColSum.Sums[] sums = RolColSum.asyncSum(matrix);
        assertThat(sums[0].getRowSum(), is(6));
        assertThat(sums[0].getColSum(), is(12));
        assertThat(sums[1].getRowSum(), is(15));
        assertThat(sums[1].getColSum(), is(15));
        assertThat(sums[2].getRowSum(), is(24));
        assertThat(sums[2].getColSum(), is(18));
    }
}
