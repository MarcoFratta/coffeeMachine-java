package org.voitu;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.util.Pair;

import java.util.ArrayList;

public class LoggerImpl implements Logger{

    public static LoggerImpl logger;

    public static Logger getLogger(){
        if (logger==null){
            logger = new LoggerImpl();
        }
        return logger;
    }

    private final ObservableList<Pair<String,String>> messages;

    public LoggerImpl() {
        this.messages = FXCollections.observableList(new ArrayList<>());
    }

    @Override
    public void log(final String type, final String msg) {
        this.messages.add(new Pair<>(type,msg));
    }
    @Override
    public void registerListener(final ListChangeListener<? super Pair<String, String>> listener){
        this.messages.addListener(listener);
    }
}
