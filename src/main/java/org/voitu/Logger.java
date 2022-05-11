package org.voitu;

import javafx.collections.ListChangeListener;
import javafx.util.Pair;

public interface Logger {

    void log(String type, String msg);
    void registerListener(ListChangeListener<? super Pair<String, String>> listener);
}
