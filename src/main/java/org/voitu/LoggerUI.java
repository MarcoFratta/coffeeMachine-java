package org.voitu;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class LoggerUI implements View{

    private final VBox content;


    public LoggerUI(final Logger logger, final VBox content) {
        this.content = content;
        logger.registerListener(c ->{
            Platform.runLater(()->{
                while (c.next()) {
                    if (c.wasAdded()) {
                        //System.out.println("logging");
                        this.content.getChildren().addAll(
                                this.createView(c.getAddedSubList()));
                    }
                }

            });
        });

    }

    private List<Node> createView(final List<? extends Pair<String, String>> list) {
        final List<Node> l = new ArrayList<>();
        for (final Pair<String,String> element:list) {
            l.add(this.createBox(element));
        }
        return l;
    }

    private Node createBox(final Pair<String, String> element) {
        return new Text("("+element.getKey()+ ") > " + element.getValue());
    }
}
