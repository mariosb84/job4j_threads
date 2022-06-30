package ru.job4j;

import org.junit.Test;
import ru.job4j.nonblockingalgoritm.Base;
import ru.job4j.nonblockingalgoritm.Cache;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CacheTest {

    Base base = new Base(1, 1);
    Base base2 = new Base(2, 1);
    Base base3 = new Base(3, 1);
    Cache cache = new Cache();

    @Test
    public void whenAdd() {
        assertThat(cache.add(base), is(true));
    }

    @Test
    public void whenDeleted() {
        cache.add(base2);
        cache.delete(base2);
        assertThat(cache.add(base2), is(true));
    }
    /*
    @Test
    public void whenUpdated() {
        cache.add(base3);
        cache.update(base3);
        assertThat(base3.getVersion(), is(2));
    }*/

}
