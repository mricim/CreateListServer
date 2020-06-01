package main.java.Menu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.java.CustomTags.CustomAlert;
import main.java.CustomTags.HBoxCustom;
import main.java.CustomTags.VBoxCustom;
import main.java.Rutas;
import main.java.SelectPath.SelectPath;
import main.java.xml.XML;
import main.java.xml.XMLCaller;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.ArrayList;

import static main.java.Dates.getCurrentTimeStamp;
import static main.java.Main.*;
import static main.java.Menu.Menu.checkVersion;
import static main.java.Menu.Menu.getButtonCancel;
import static main.java.xml.XML.writeXML;

public class MenuJar {
    public static void menuAdd(Stage stage1, BorderPane borderPane) {
        stage1.setTitle("Add Jar");
        VBoxCustom vBox = new VBoxCustom();
        vBox.setSpacing(30);
        //
        //
        //
        Button btnDone = new Button("Upload Jar");
        btnDone.setDisable(true);
//
        Label label = new Label("Version:");
        TextField version = new TextField();
        version.setPromptText("ej: 1.2.3.4." + getCurrentTimeStamp());
        version.setFocusTraversable(false);
        version.setOnMouseClicked(event -> {
            btnDone.setDisable(true);
        });
        Label fecha = new Label(".?");
        Button check = new Button("Check");
        HBoxCustom hBox1 = new HBoxCustom(label, version, fecha, check);
        vBox.getChildren().add(hBox1);


        Label exe = new Label( "Add Jar:");
        TextField textAreaExe = new TextField();
        textAreaExe.setPrefWidth(200);
        textAreaExe.setEditable(false);
        Button btnExe = new Button("Select .jar" );
        btnExe.setDisable(true);

        HBoxCustom hBox2 = new HBoxCustom(exe, textAreaExe, btnExe);
        vBox.getChildren().add(hBox2);

        check.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                //checkVersion(stage1, version.getText(), btnExe, btnZip);
                checkVersion(stage1, version.getText(), btnExe);
            }
        });
        btnExe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                SelectPath.selectFile(stage1, textAreaExe, "jar");
                textAreaExe.setPrefWidth(textAreaExe.getText().length() * 7);
                btnDone.setDisable(false);
            }
        });
        btnDone.setOnAction(e -> {
            XMLCaller.generatorJar(stage1, borderPane, version.getText(), textAreaExe.getText());
        });
        //
        //
        HBoxCustom toMenu = new HBoxCustom(getButtonCancel(stage1, borderPane), btnDone);
        vBox.getChildren().add(toMenu);
        borderPane.setCenter(vBox);
    }

    public static void menuRemove(Stage stage1, BorderPane borderPane) {
        try {
            stage1.setTitle("Jar-> Remove");
            VBoxCustom vBox = new VBoxCustom();
            vBox.setSpacing(30);
            //
            //
            String lista = RUTE + CORE + LISTA;

            ArrayList<Rutas> rutas = XML.extractorXML(XML.getList(XML.getDocument(lista)));
            ObservableList<String> observableList = FXCollections.observableArrayList();
            for (Rutas ruta : rutas) {
                observableList.add(ruta.getVersion());
            }

            ComboBox<String> comboBox = new ComboBox<>(observableList);
            Button btn = new Button("Delete");
            btn.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {
                    String seleccionado = comboBox.getSelectionModel().getSelectedItem();
                    System.out.println(seleccionado);
                    rutas.removeIf(ruta -> ruta.getVersion().equals(seleccionado));

                    try {
                        writeXML(lista, "file", rutas);
                    } catch (ParserConfigurationException | TransformerException a) {
                        a.printStackTrace();
                        CustomAlert alert = new CustomAlert(stage1, Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Se ha detectado un error");
                        alert.setContentText(a.getMessage());
                        alert.showAndWait();
                    }
                    CustomAlert alert = new CustomAlert(stage1, Alert.AlertType.INFORMATION);
                    alert.setTitle("Eliminado correctamente");
                    alert.setHeaderText("Borrado");
                    alert.setContentText(seleccionado+" deletec");
                    alert.showAndWait();
                    Menu.menu(stage1, borderPane);
                }
            });
            HBoxCustom hBox3 = new HBoxCustom(comboBox, btn);
            vBox.getChildren().add(hBox3);
            //
            //
            HBoxCustom toMenu = new HBoxCustom(getButtonCancel(stage1, borderPane));
            vBox.getChildren().add(toMenu);
            borderPane.setCenter(vBox);
        } catch (Exception e) {
            e.printStackTrace();
            CustomAlert alert = new CustomAlert(stage1, Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Se ha detectado un error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}
