package ru.job4j.resourcesynchronization;

import com.google.gson.Gson;
import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@ThreadSafe
public class SingleLockList<T> implements Iterable<T> {
    @GuardedBy("this")
    private final List<T> list = new ArrayList<>();

    public  SingleLockList() {
    }

    /*public SingleLockList(List<T> list) throws CloneNotSupportedException {
        this.list = (List) list.clone();
        this.list = (List<T>) super.clone();
    }*/

    public synchronized void add(T value) {
        list.add(value);
    }

    public synchronized T get(int index) {
        return list.get(index);
    }

    @Override
    public synchronized Iterator<T> iterator() {
        return copy(this.list).iterator();
    }

    private List<T> copy(List<T> list) {
        List<T> listFinal = new ArrayList<>();
        for (T value : list) {
            Gson gson = new Gson();
            T deepCopiedT = gson.fromJson(gson.toJson(value), (Type) value.getClass());
            listFinal.add(deepCopiedT);
        }
        return listFinal;
    }

}
