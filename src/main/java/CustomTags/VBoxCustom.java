package main.java.CustomTags;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;

public class VBoxCustom extends VBox {
    public VBoxCustom(Node... children) {
        super();
        getChildren().addAll(children);
        setAlignment(Pos.CENTER);
        setSpacing(20);
    }
}
