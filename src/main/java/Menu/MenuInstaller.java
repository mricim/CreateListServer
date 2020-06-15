package main.java.Menu;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import main.java.CustomTags.CustomAlert;
import main.java.Rutas;
import main.java.xml.*;
import main.java.CustomTags.HBoxCustom;
import main.java.CustomTags.VBoxCustom;
import main.java.SelectPath.SelectPath;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.w3c.dom.NodeList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.ArrayList;

import static main.java.Dates.getCurrentTimeStamp;
import static main.java.Main.*;
import static main.java.Menu.Menu.*;
import static main.java.xml.XML.writeXML;

public class MenuInstaller {

    static void menuInstaller(Stage stage1, BorderPane borderPane, boolean addTRUEremoveFALSE) {
        if (addTRUEremoveFALSE){
            stage1.setTitle("ADD Installer");
        }else {
            stage1.setTitle("REMOVE Installer");
        }
        VBoxCustom vBox = new VBoxCustom();
        //
        //
        String windowsString = "Windows";
        Button windows = new Button(windowsString);
        windows.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                if (addTRUEremoveFALSE) {
                    addInstaller(stage1, borderPane, windowsString);
                } else {
                    removeInstaller(stage1, borderPane, windowsString);
                }
            }
        });
        String linuxString = "Linux";
        Button linux = new Button(linuxString);
        linux.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                if (addTRUEremoveFALSE) {
                    addInstaller(stage1, borderPane, linuxString);
                } else {
                    removeInstaller(stage1, borderPane, linuxString);
                }
            }
        });
        String macString = "Mac";
        Button mac = new Button(macString);
        mac.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                if (addTRUEremoveFALSE) {

                    addInstaller(stage1, borderPane, macString);
                } else {
                    removeInstaller(stage1, borderPane, macString);
                }
            }
        });
        HBoxCustom hBox1 = new HBoxCustom(windows, linux, mac);
        //
        //
        HBoxCustom toMenu = new HBoxCustom(getButtonCancel(stage1, borderPane));
        vBox.setSpacing(30);
        vBox.getChildren().

                addAll(hBox1, toMenu);
        borderPane.setCenter(vBox);
    }


    private static void addInstaller(Stage stage1, BorderPane borderPane, String so) {
        stage1.setTitle("ADD Installer->" + so);
        VBoxCustom vBox = new VBoxCustom();
        vBox.setSpacing(30);
        //
        //
//
        Button btnDone = new Button("Upload to " + so);
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

        String format1 = "";
        String format2 = "";
        switch (so) {
            case "Windows":
                format1 = "exe";
                format2 = "zip";
                break;
            case "Linux":
                format1 = "tar.gz";
                format2 = "zip";
                break;
            case "Mac":
                format1 = "rar";
                format2 = "zip";
                break;
        }

        Label label1 = new Label(format1 + ":");
        TextField textArea1 = new TextField();
        textArea1.setPrefWidth(200);
        textArea1.setEditable(false);
        Button btn1 = new Button("Select " + format1);
        btn1.setDisable(true);

        HBoxCustom hBox2 = new HBoxCustom(label1, textArea1, btn1);
        vBox.getChildren().add(hBox2);

        Label label2 = new Label(format2 + ":");
        TextField textArea2 = new TextField();
        textArea2.setPrefWidth(200);
        textArea2.setEditable(false);
        Button btn2 = new Button("Select " + format2);
        btn2.setDisable(true);
        HBoxCustom hBox3 = new HBoxCustom(label2, textArea2, btn2);
        vBox.getChildren().add(hBox3);

        String finalFormat1 = format1;
        String finalFormat2 = format2;

        check.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                //checkVersion(stage1, version.getText(), btn1, btn2);
                checkUpload(stage1, btnDone, version.getText(), btn1, textArea1.getText(), btn2, textArea2.getText());
            }
        });
        btn1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                SelectPath.selectFile(stage1, textArea1, finalFormat1);
                textArea1.setPrefWidth(textArea1.getText().length() * 7);
                checkUpload(stage1, btnDone, version.getText(), btn1, textArea1.getText(), btn2, textArea2.getText());
            }
        });
        btn2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                SelectPath.selectFile(stage1, textArea2, finalFormat2);
                textArea2.setPrefWidth(textArea2.getText().length() * 7);
                checkUpload(stage1, btnDone, version.getText(), btn1, textArea1.getText(), btn2, textArea2.getText());
            }
        });
        btnDone.setOnAction(e -> {
            try {
                XMLCaller.generatorInstaller(stage1, borderPane, so, version.getText(), finalFormat1, textArea1.getText(), finalFormat2, textArea2.getText());
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        });
        //
        //
        HBoxCustom toMenu = new HBoxCustom(getButtonCancel(stage1, borderPane), btnDone);
        vBox.getChildren().add(toMenu);
        borderPane.setCenter(vBox);
    }

    private static void checkUpload(Stage stage1, Button done, String version, Button btn1, String path1, Button btn2, String path2) {
        done.setDisable(!checkVersion(stage1, version, btn1, btn2) || path1.length() <= 4 || path2.length() <= 4);
    }


    public static void removeInstaller(Stage stage1, BorderPane borderPane, String os) {
        try {
            stage1.setTitle("REMOVE Installer->" + os + " Remove");
            VBoxCustom vBox = new VBoxCustom();
            vBox.setSpacing(30);
            //
            //
            String lista = RUTE + INSTALLER + os.toLowerCase() + LISTA;

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
                    alert.setContentText(seleccionado + " deletec");
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
