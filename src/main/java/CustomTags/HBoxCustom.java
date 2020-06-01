package main.java.CustomTags;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

public class HBoxCustom extends HBox {
    public HBoxCustom(Node... children) {
        super();
        getChildren().addAll(children);
        setAlignment(Pos.CENTER);
        setSpacing(20);
    }
}
