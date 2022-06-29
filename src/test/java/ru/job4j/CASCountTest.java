package ru.job4j;

import org.junit.Test;
import ru.job4j.nonblockingalgoritm.CASCount;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CASCountTest {

    @Test
    public void whenAddEightThenGetEight() throws InterruptedException {

        final CASCount casCount = new CASCount();

        Thread countOne = new Thread(
                () -> {
                    casCount.increment();
                    casCount.increment();
                    casCount.increment();
                    casCount.increment();
                    casCount.increment();
                }
        );
        Thread countTwo = new Thread(
                () -> {
                    casCount.increment();
                    casCount.increment();
                    casCount.increment();
                }
        );

        countOne.start();
        countTwo.start();
        countOne.join();
        countTwo.join();
        assertThat(casCount.get(), is(8));
    }

}
