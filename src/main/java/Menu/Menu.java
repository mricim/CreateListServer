package main.java.Menu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import main.java.CustomTags.CustomAlert;
import main.java.CustomTags.HBoxCustom;
import main.java.Dates;
import main.java.Main;
import main.java.xml.XMLCaller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static main.java.Dates.isValidFormat;

public class Menu {
    public static String getCurrentTimeStamp() {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy/MM/dd");//dd/MM/yyyy
        Date now = new Date();
        return sdfDate.format(now);
    }

    public static void menu(Stage stage1, BorderPane borderPane, boolean ruteIsSelected) {


        stage1.setTitle("Home");
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(10);
        //
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setInitialDirectory(new File("../"));
        Label label = new Label("..\\downloads\\");
        TextField selectRuteTextField;
        if (ruteIsSelected) {
            selectRuteTextField = new TextField(Main.RUTE);
        } else {
            selectRuteTextField = new TextField("downloads");
        }


        Button selectRuteButton = new Button("Select Folder");

        HBoxCustom hBox0 = new HBoxCustom(label, selectRuteTextField, selectRuteButton);
        hBox0.setSpacing(10);
        vBox.getChildren().add(hBox0);

        //
        Button addInstaller = new Button("Add Installer");
        addInstaller.setDisable(true);
        addInstaller.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                MenuInstaller.menuInstaller(stage1, borderPane, true);
            }
        });
        Button addJar = new Button("Add Jar");
        addJar.setDisable(true);
        addJar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                MenuJar.menuJar(stage1, borderPane);
            }
        });
        HBoxCustom hBox1 = new HBoxCustom(addInstaller, addJar);
        hBox1.setSpacing(10);
        vBox.getChildren().add(hBox1);
        //
        Button updateFiles = new Button("Update Files");
        updateFiles.setDisable(true);
        updateFiles.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                try {
                    XMLCaller.generatorFiles(stage1, borderPane);
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
        });
        HBoxCustom hBox2 = new HBoxCustom(updateFiles);
        hBox2.setSpacing(10);
        vBox.getChildren().add(hBox2);
        //
        Button removeInstaller = new Button("Remove Installer");
        removeInstaller.setDisable(true);
        removeInstaller.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                MenuInstaller.menuInstaller(stage1, borderPane, false);
            }
        });
        Button removeJar = new Button("Remove Jar");
        removeJar.setDisable(true);
        removeJar.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                MenuJar.menuRemove(stage1, borderPane);
            }
        });
        HBoxCustom hBox3 = new HBoxCustom(removeInstaller, removeJar);
        hBox3.setSpacing(10);
        vBox.getChildren().add(hBox3);
        //
        borderPane.setCenter(vBox);

        if (ruteIsSelected) {
            addInstaller.setDisable(false);
            addJar.setDisable(false);
            updateFiles.setDisable(false);
            removeInstaller.setDisable(false);
            removeJar.setDisable(false);
        }
        selectRuteButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                File selectedDirectory = directoryChooser.showDialog(stage1);
                String rute = selectedDirectory.getAbsolutePath() + "\\";
                if (rute.contains("\\downloads\\")) {
                    selectRuteTextField.setText(rute);
                    //
                    addInstaller.setDisable(false);
                    addJar.setDisable(false);
                    updateFiles.setDisable(false);
                    removeInstaller.setDisable(false);
                    removeJar.setDisable(false);
                    //
                    Main.RUTE = rute;
                }
            }
        });
    }

    static Button getButtonCancel(Stage stage1, BorderPane borderPane) {
        Button btnToMenu = new Button("Cancel");
        btnToMenu.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                menu(stage1, borderPane, true);
            }
        });
        return btnToMenu;
    }

    static boolean checkVersion(Stage stage, String chart, Button... buttons) {
        boolean salida = false;
        if (chart.length() - chart.replaceAll("\\.", "").length() == 4) {
            String[] a = chart.split("\\.");
            boolean cumpleMinimoDeTexto = true;
            for (int i = 0; i < a.length; i++) {
                int leter = 1;
//                System.out.println(i + " " + a[i]);
                if (i == 4) {
//                    System.out.println("X" + i + " " + a[i] + " " + a[i].length());
                    leter = 8;
                    if (!isValidFormat("yyyyMMdd", a[i], Locale.ENGLISH)) {//mirar si la fecha es correcta
                        cumpleMinimoDeTexto = false;
                        CustomAlert alert = new CustomAlert(stage, Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText("Date format = yyyyMMdd");
                        alert.setContentText("Error in date or date format.\nExample (now): " + Dates.getCurrentTimeStamp());
                        alert.showAndWait();
                        break;
                    }
                }
                if (a[i].length() < leter) {
                    cumpleMinimoDeTexto = false;
                    CustomAlert alert = new CustomAlert(stage, Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Version not correct formated.\nUse for example: 1.2.3.4." + Dates.getCurrentTimeStamp());
                    alert.setContentText("Error in format version");
                    alert.showAndWait();
                    break;
                }
            }
            if (cumpleMinimoDeTexto) {
                for (Button button : buttons) {
                    button.setDisable(false);
                }
                salida = true;
            } else {
                for (Button button : buttons) {
                    button.setDisable(true);
                }
            }
        } else {
            for (Button button : buttons) {
                button.setDisable(true);
            }
        }
        return salida;
    }
}
