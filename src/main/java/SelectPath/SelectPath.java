package main.java.SelectPath;

import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


import main.java.Main;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SelectPath {
    public static void openFile(File file) {
        try {
            Main.desktop.open(file);
        } catch (IOException ex) {
            Logger.getLogger(
                    Main.class.getName()).log(
                    Level.SEVERE, null, ex
            );
        }
    }

    public static void selectFile(Stage stage1, TextField textAreaExe, String format) {
        FileChooser fileChooser = new FileChooser();
        configureFileChooser(fileChooser, format);
        File file = fileChooser.showOpenDialog(stage1);
        if (file != null) {
            //openFile(file);
            textAreaExe.setText(file.getAbsolutePath());
        }
    }

    private static void configureFileChooser(FileChooser fileChooser, String format) {
        fileChooser.setTitle("View Pictures");
        fileChooser.setInitialDirectory(
                new File(System.getProperty("user.home"))
        );
        fileChooser.getExtensionFilters().addAll(
                /*
                new FileChooser.ExtensionFilter("All Images", "*.*"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
*/
                new FileChooser.ExtensionFilter(format, "*." + format)
        );
    }
}
