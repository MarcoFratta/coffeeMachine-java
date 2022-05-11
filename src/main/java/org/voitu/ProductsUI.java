package org.voitu;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ProductsUI {

    private final VBox content;
    private final Map<String,Label> labels;
    public ProductsUI(final VBox productBox) {
        this.content = productBox;
        this.labels = new HashMap<>();
    }

    public void update(final String name, final int val){
        if(this.labels.containsKey(name)) {
            this.labels.get(name).setText(name + ":" + val);
        } else {
            this.labels.put(name, new Label(name + ":" + val));
        }
    }
}
