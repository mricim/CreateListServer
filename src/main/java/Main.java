package main.java;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import main.java.CustomTags.HBoxCustom;
import main.java.Menu.Menu;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import static main.java.SelectPath.SelectPath.openFile;

public class Main extends Application {

    public static String RUTE;
    public static final String INSTALLER="application\\installers\\";
    public static final String CORE="application\\update\\core\\";
    public static final String FILES="application\\update\\files\\";
    public static final String LISTA="\\list.xml";


    public static Desktop desktop = Desktop.getDesktop();
    BorderPane borderPane = new BorderPane();

    public static void main(String[] args) {
        if (Main.class.getResource("Main.class").toString().contains("jar:")){
            RUTE=System.getProperty("user.dir")+"\\";
        }else{
            RUTE=System.getProperty("user.dir")+"\\src\\downloads\\";
        }

        launch(args);
    }

    @Override
    public void start(final Stage stage) {
        System.out.println(RUTE);
        Scene scene = new Scene(borderPane, 600, 300);
        // New window (Stage)
        Stage stage1 = new Stage();
        stage1.setScene(scene);

        stage1.show();

        gestorDeMenus(stage1,borderPane);

    }

    private void gestorDeMenus(Stage stage1,BorderPane borderPane) {
        Menu.menu(stage1,borderPane);
    }















    private void cosa(Stage stage1) {

        Button installer = new Button("Add Installer");
        Button jar = new Button("Add Jar");
        Button files = new Button("Add Files");

        installer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(final ActionEvent e) {
                FileChooser fileChooser = new FileChooser();
                File file = fileChooser.showOpenDialog(stage1);
                if (file != null) {
                    openFile(file);
                }
            }
        });

        jar.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        FileChooser fileChooser = new FileChooser();
                        List<File> list = fileChooser.showOpenMultipleDialog(new Stage());
                        if (list != null) {
                            for (File file : list) {
                                openFile(file);
                            }
                        }
                    }
                });
        files.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(final ActionEvent e) {
                        final DirectoryChooser directoryChooser = new DirectoryChooser();
                        final File selectedDirectory = directoryChooser.showDialog(stage1);
                        if (selectedDirectory != null) {
                            selectedDirectory.getAbsolutePath();
                        }
                    }
                }
        );

        HBoxCustom hBox = new HBoxCustom(installer, jar, files);
        hBox.setSpacing(10);
        borderPane.setCenter(hBox);
    }
}
