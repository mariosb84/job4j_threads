package ru.job4j.resourcesynchronization;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import  java.lang.Cloneable;

import java.util.Iterator;
import java.util.List;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {
    @GuardedBy("this")
    private final List<T> list;

    public SingleLockList(List<T> list) {
        this.list = (List) list.clone();
    }

    public synchronized void add(T value) {
        list.add(value);
    }

    public synchronized T get(int index) {
        return list.get(index);
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return (Iterator<T>) copy(this.list).iterator();
    }

    private Iterable<Object> copy(List<T> list) {
        return (Iterable<Object>) list.iterator();
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new SingleLockList((List) this);
    }

}
