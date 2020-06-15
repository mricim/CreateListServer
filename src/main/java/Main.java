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
}
