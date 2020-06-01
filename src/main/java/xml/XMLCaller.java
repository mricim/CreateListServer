package main.java.xml;

import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.java.CustomTags.CustomAlert;
import main.java.Hash;
import main.java.Menu.Menu;
import main.java.ReadFiles;
import main.java.Rutas;
import main.java.SelectPath.CustomDirOrFile;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static main.java.Hash.toHex;
import static main.java.Main.*;
import static main.java.xml.XML.writeXML;

public class XMLCaller {
    public static void generatorInstaller(Stage stage1, BorderPane borderPane, String so, String version, String exe, String zip) {
        try {
            ArrayList<Rutas> rutas = new ArrayList<>();
            ArrayList<Rutas> rutasOld = new ArrayList<>();

            String ruteXX = RUTE + INSTALLER + so.toLowerCase();
            File lalista = new File(ruteXX + LISTA);
            if (!lalista.exists()) {
                if (!lalista.createNewFile()) {
                    throw new FileNotFoundException("No se pudo crear la lista");
                }
            } else {
                NodeList nodeList = XML.getList(XML.getDocument(lalista.getAbsolutePath()));
                rutasOld.addAll(XML.extractorXML(nodeList));
            }
            //System.out.println(exe);
            //System.out.println(name);
            int num = 0;
            for (Rutas ruta : rutasOld) {
                if (ruta.getVersion().split("\\.")[4].equals(version.substring(version.lastIndexOf(".") + 1))) {
                    num = Integer.parseInt(ruta.getVersion().split("\\.")[5]) + 1;
                    break;
                }
            }
            String name = exe.substring(exe.lastIndexOf("\\") + 1);
            version += "." + num;
            String versionpath = version.replaceAll("\\.", "/") + "/" + name;
            rutas.add(new Rutas(null, "file", version, versionpath, name, "tmp", toHex(Hash.MD5.checksum((new File(exe)))), null));
            rutas.addAll(rutasOld);

            writeXML(lalista.getAbsolutePath(), "file", rutas);

            String newFilePath = ruteXX + "\\" + versionpath.replaceAll("/", "\\\\");
            //System.out.println("DD " + newFilePath);
            File dir = new File(newFilePath.substring(0, newFilePath.lastIndexOf("\\")));
            //System.out.println(dir.getAbsolutePath());
            if (!dir.exists()) {
                if (!dir.mkdirs()) {
                    throw new FileNotFoundException("carpeta no creada: " + dir.getAbsolutePath());
                }
            }
            if (!new File(exe).renameTo(new File(newFilePath))) {
                throw new FileNotFoundException("exe not move: " + newFilePath);
            }
            //System.out.println(zip);
            //System.out.println(newFilePath.substring(0, newFilePath.lastIndexOf(".")) + ".zip");
            String pathZip = newFilePath.substring(0, newFilePath.lastIndexOf(".")) + ".zip";
            if (!new File(zip).renameTo(new File(pathZip))) {
                throw new FileNotFoundException("zip not move" + pathZip);

            }
            //
            CustomAlert alert = new CustomAlert(stage1, Alert.AlertType.CONFIRMATION);
            alert.setTitle("Update");
            alert.setHeaderText("Correct Update");
            alert.setContentText("Recuerda hacer \"git push\" para actualizar la WEB");
            alert.showAndWait();
        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            e.printStackTrace();
            //
            CustomAlert alert = new CustomAlert(stage1, Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Se ha detectado un error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        //
        Menu.menu(stage1, borderPane);
    }

    public static void generatorJar(Stage stage1, BorderPane borderPane, String version, String jar) {
        try {
            ArrayList<Rutas> rutas = new ArrayList<>();
            ArrayList<Rutas> rutasOld = new ArrayList<>();
            String ruteXX = RUTE + CORE;
            File lalista = new File(ruteXX + LISTA);
            if (!lalista.exists()) {
                if (!lalista.createNewFile()) {
                    throw new FileNotFoundException("No se pudo crear la lista");
                }
            } else {
                NodeList nodeList = XML.getList(XML.getDocument(lalista.getAbsolutePath()));
                rutasOld.addAll(XML.extractorXML(nodeList));
            }
            String name = jar.substring(jar.lastIndexOf("\\") + 1);
            int num = 0;
            for (Rutas ruta : rutasOld) {
                if (ruta.getVersion().split("\\.")[4].equals(version.substring(version.lastIndexOf(".") + 1))) {
                    num = Integer.parseInt(ruta.getVersion().split("\\.")[5]) + 1;
                    break;
                }
            }
            version += "." + num;
            String versionpath = version.replaceAll("\\.", "/") + "/" + name;
            rutas.add(new Rutas(null, "file", version, versionpath, name, "tmp", toHex(Hash.MD5.checksum((new File(jar)))), null));
            rutas.addAll(rutasOld);

            writeXML(lalista.getAbsolutePath(), "file", rutas);

            String newFilePath = ruteXX + "\\" + versionpath.replaceAll("/", "\\\\");
            //System.out.println("DD " + newFilePath);
            File dir = new File(newFilePath.substring(0, newFilePath.lastIndexOf("\\")));
            //System.out.println(dir.getAbsolutePath());
            if (!dir.exists()) {
                if (!dir.mkdirs()) {
                    throw new FileNotFoundException("carpeta no creada: " + dir.getAbsolutePath());
                }
            }
            if (!new File(jar).renameTo(new File(newFilePath))) {
                throw new FileNotFoundException("jar not move: " + newFilePath);
            }

            //
            CustomAlert alert = new CustomAlert(stage1, Alert.AlertType.CONFIRMATION);
            alert.setTitle("Update");
            alert.setHeaderText("Correct Update");
            alert.setContentText("Recuerda hacer \"git push\" para actualizar la WEB");
            alert.showAndWait();
        } catch (ParserConfigurationException | SAXException | IOException | TransformerException e) {
            e.printStackTrace();
            //
            CustomAlert alert = new CustomAlert(stage1, Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Se ha detectado un error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
        //
        Menu.menu(stage1, borderPane);
    }

    public static void generatorFiles(Stage stage1, BorderPane borderPane) {
        String ruteXX = RUTE + FILES;
        List<CustomDirOrFile> a = ReadFiles.walk(ruteXX + "res");

        ArrayList<Rutas> rutas = new ArrayList<>();
        for (CustomDirOrFile customDirOrFile : a) {
            if (!customDirOrFile.isDirTRUEfileFALSE()) {
//                System.out.println(customDirOrFile);
                String href = customDirOrFile.getPath().replace(ruteXX, "");
//                System.out.println(href);
                String name = href.substring(href.lastIndexOf("\\") + 1);
//                System.out.println(name);
                String path = href.replace(name, "");
                if (path.endsWith("\\")) {
                    path = path.substring(0, path.length() - 1);
                }
//                System.out.println(path);
                rutas.add(new Rutas(null, "file", null, href, name, path, customDirOrFile.getHash(), null));
            }
        }
        try {
            writeXML(ruteXX + LISTA, "file", rutas);
            CustomAlert alert = new CustomAlert(stage1, Alert.AlertType.CONFIRMATION);
            alert.setTitle("Update");
            alert.setHeaderText("Correct Update");
            alert.setContentText("Recuerda hacer \"git push\" para actualizar la WEB");
            alert.showAndWait();
        } catch (ParserConfigurationException | TransformerException e) {
            CustomAlert alert = new CustomAlert(stage1, Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Se ha detectado un error");
            alert.setContentText(e.getMessage());
            alert.showAndWait();
        }
    }
}
