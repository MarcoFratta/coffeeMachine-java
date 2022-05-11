package org.voitu;

public interface Controller<T> {

    void registerListener(T listener);
}
