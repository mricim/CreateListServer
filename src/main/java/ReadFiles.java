package main.java;

import main.java.SelectPath.CustomDirOrFile;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ReadFiles {

    public static List<CustomDirOrFile> walk(String path) throws Exception {
        List<CustomDirOrFile> lista = new ArrayList<>();
        File root = new File(path);
        File[] list = root.listFiles();

        if (list == null) return lista;

        for (File f : list) {
            if (f.isDirectory()) {
                lista.addAll(walk(f.getAbsolutePath()));
                String pathA = f.getAbsolutePath();
                String name = pathA.substring(pathA.lastIndexOf("\\"));
                lista.add(new CustomDirOrFile(pathA, name, true));
                //System.out.println("Dir:" + f.getAbsoluteFile());
            } else {
                String pathA = f.getAbsolutePath();
                String name = pathA.substring(pathA.lastIndexOf("\\"));
                lista.add(new CustomDirOrFile(pathA, name, false));
                //System.out.println("File:" + f.getAbsoluteFile());
            }
        }
        return lista;
    }
}
